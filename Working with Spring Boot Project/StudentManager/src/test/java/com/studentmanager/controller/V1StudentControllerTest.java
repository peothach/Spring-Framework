package com.studentmanager.controller;


import com.google.gson.*;
import com.studentmanager.configuration.SecurityConfiguration;
import com.studentmanager.configuration.YAMLConfig;
import com.studentmanager.entity.Student;
import com.studentmanager.reponsitoty.IStudentRepo;
import com.studentmanager.restcontroller.V1StudentController;
import com.studentmanager.service.IStudentService;
import com.studentmanager.service.impl.StudentServiceDev;
import com.studentmanager.service.impl.StudentServiceProd;
import com.studentmanager.valid.entity.CustomValidatorStudent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = V1StudentController.class)
@Import({StudentServiceDev.class,
        StudentServiceProd.class,
        CustomValidatorStudent.class,
        YAMLConfig.class,
        SecurityConfiguration.class})
public class V1StudentControllerTest {

    @MockBean
    IStudentRepo studentRepo;

    @Autowired
    IStudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1L, "John", "US", LocalDate.parse("1991-01-01"), "John@gmail.com", "+84866872164",
                    "F", "user1", LocalDate.parse("2021-06-10"), "user1", LocalDate.parse("2021-06-10")),
            new Student(2L, "Smith", "UK", LocalDate.parse("1992-01-01"), "James@gmail.com", "+84866872164",
                    "M", "user2", LocalDate.parse("2021-06-10"), "user1", LocalDate.parse("2021-06-10")),
            new Student(3L, "James", "Uw", LocalDate.parse("1993-01-01"), "David@gmail.com", "+84866872164",
                    "F", "user1", LocalDate.parse("2021-06-10"), "user1", LocalDate.parse("2021-06-10")),
            new Student(4L, "David", "UK", LocalDate.parse("1994-01-01"), "George@gmail.com", "+84866872164",
                    "M", "user2", LocalDate.parse("2021-06-10"), "user1", LocalDate.parse("2021-06-10"))
    );
    private static final Student STUDENT = new Student(1L, "John", "US", LocalDate.parse("1991-01-01"), "John@gmail.com", "+84866872164",
            "F", "user1", LocalDate.parse("2021-06-13"), "user1", LocalDate.parse("2021-06-13"));

    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }
    }).create();

    // Test Environment
    @Test
    public void given_devEnvironment_when_callEndpointEnvironment_then_returnDEVAndStatus200() throws Exception {
        //call GET "/api/test/hello-world", application_json
        RequestBuilder request = MockMvcRequestBuilders
                .get("/environment")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{environment:DEV}"))
                .andReturn();

        // verify "Hello world"
        // assertEquals("Hello World", mvcResult.getResponse().getContentAsString());
    }

    // End Point(/students), Method = showAll, Positive case
    @Test
    public void given_devEnvironmentAndListStudent_when_callEndpointFindAll_then_returnListStudentAndStatus200() throws Exception {

        when(studentRepo.findAll()).thenReturn(STUDENT_LIST);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/students")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(STUDENT_LIST),true))
                .andReturn();
    }

    // End Point(/students), Method = showAll, Edge case
    @Test
    public void given_devEnvironmentAndEmptyListStudent_when_callEndpointFindAll_then_returnEmptyListStudentAndStatus200() throws Exception {

        List<Student> studentList = new ArrayList<>();

        when(studentRepo.findAll()).thenReturn(studentList);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/students")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(studentList)))
                .andReturn();
    }

    // End Point(/students/paging), Method = showStudentByPage, Positive case
    @Test
    public void given_devEnvironmentAndListStudent_when_callEndPointShowStudentByPage_then_returnStatus200ListStudentWithByPageAndSize() throws Exception {
        Page<Student> studentPage = new PageImpl<>(STUDENT_LIST);
        when(studentRepo.findAll(any(PageRequest.class))).thenReturn(studentPage);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/students/paging")
                .accept(MediaType.APPLICATION_JSON)
                .param("page", "1 ")
                .param("size", "4");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(studentPage.getContent())))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    // End Point(/students/paging), Method = showStudentByPage, Negative case
    @Test
    public void given_devEnvironmentWithParamPageAndSizeIsNegativeInteger_when_callEndPointStudentByPage_then_returnStatus400AndMessageDetail() throws Exception {
        String url = "/students/paging";
        String jsonResult = "{\n" +
                "    \"message\": \"Valid param!\",\n" +
                "    \"status\": false,\n" +
                "    \"details\": {\n" +
                "        \"size\": \"must be greater than or equal to 0\",\n" +
                "        \"page\": \"must be greater than or equal to 1\"\n" +
                "    }\n" +
                "}";
        RequestBuilder request = MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .param("page", "-1")
                .param("size", "-1");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(jsonResult, true))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    // End Point(/student/sort), Method = showStudentAndSort, Positive case
    @Test
    public void given_devEnvironmentWithParamFieldAndSort_when_callEndPointShowStudentAndSort_then_returnStatus200AndSortListStudent() throws Exception {
        List<Student> studentListSort = STUDENT_LIST.stream().sorted((student1, student2) -> student1.getName().compareTo(student2.getName())).collect(Collectors.toList());

        Mockito.when(studentRepo.findAll(ArgumentMatchers.any(Sort.class))).thenReturn(studentListSort);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/students/sort")
                .accept(MediaType.APPLICATION_JSON)
                .param("field", "name")
                .param("sort", "ASC");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(studentListSort)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //End Point(/student/search), Method = showStudentByKeyWord, Positive case
    @Test
    public void given_devEnvironmentWithParamKeyword_when_callEndPointShowStudentByKeyWord_then_returnStatus200AndStudentList() throws Exception {
        when(studentRepo.search(any(String.class))).thenReturn(Collections.singletonList(STUDENT));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/students/search")
                .accept(MediaType.APPLICATION_JSON)
                .param("keyword", "John");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(Collections.singletonList(STUDENT)), true))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //End Point(/student/filter), Method = filterStudent, Positive case
    @Test
    public void given_devEnvironmentWithParamOfStudent_when_callEndPointFilterStudent_then_returnStatus200AndStudentList() throws Exception {
        when(studentRepo.filter(ArgumentMatchers.any(Student.class)))
                .thenReturn(Collections.singletonList(STUDENT));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/students/filter")
                .accept(MediaType.APPLICATION_JSON)
                .param("name", "John")
                .param("address", "US");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(Collections.singletonList(STUDENT)),true))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //End Point(/students/{id}), Method = findStudentByID, Positive case
    @Test
    public void given_devEnvironmentWithPathVariableId_when_callEndPointFindStudentByID_then_returnStatus200AndStudent() throws Exception {
        when(studentRepo.findById(ArgumentMatchers.any(Long.class)))
                .thenReturn(Optional.of(STUDENT));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/students/1")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(STUDENT), true))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //End Point(/students), POST, Method = createStudent, Positive case
    @Test
    @WithMockUser("user1")
    public void given_devEnvironmentWithStudent_when_callEndPointCreateStudent_then_returnStatus200AndStudent() throws Exception {
        String student = "{\n" +
                "    \"name\":\"John\",\n" +
                "    \"address\":\"US\",\n" +
                "    \"birthday\":\"1991-01-01\",\n" +
                "    \"email\":\"John@gmail.com\",\n" +
                "    \"phone\":\"+84866872164\",\n" +
                "    \"gender\":\"F\"\n" +
                "}";

        Mockito.when(studentRepo.save(ArgumentMatchers.any(Student.class)))
                .thenReturn(STUDENT);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/students")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(student);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(STUDENT), true))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    //End Point(/students), PUT, Method = updateStudent, Positive case
    @Test
    @WithMockUser("user1")
    public void given_devEnvironmentWithStudentAndPathVariableID_when_callEndPointUpdateStudent_then_returnStatus200AndStudent() throws Exception {
        Student oldStudent = new Student(1L, "Taylor", "UK", LocalDate.parse("1991-01-01"), "John@gmail.com", "+84866872164",
                "F", "user1", LocalDate.parse("2021-06-13"), "user1", LocalDate.parse("2021-06-13"));

        String studentJson = "{\n" +
                "    \"name\": \"John\",\n" +
                "    \"address\": \"US\",\n" +
                "    \"birthday\": \"2000-11-05\",\n" +
                "    \"email\": \"peothach@gmail.com\",\n" +
                "    \"phone\": \"+84866872164\",\n" +
                "    \"gender\": \"F\",\n" +
                "    \"createdBy\": \"user1\",\n" +
                "    \"createdDate\": \"2021-06-13\"\n" +
                "}";

        Mockito.when(studentRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(oldStudent));
        Mockito.when(studentRepo.saveAndFlush(ArgumentMatchers.any(Student.class)))
                .thenReturn(STUDENT);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/students/1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(studentJson);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(gson.toJson(STUDENT), true))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    //End Point(/students), HTTP DELETE, Method = updateStudent, Positive case
    @Test
    public void given_devEnvironmentWithPathVariable_when_callEndPointUpdateStudent_then_returnStatus200() throws Exception {

        Mockito.when(studentRepo.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(STUDENT));

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/students/1")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }


}

