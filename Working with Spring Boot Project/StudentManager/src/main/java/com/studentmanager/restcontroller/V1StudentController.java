package com.studentmanager.restcontroller;

import com.studentmanager.configuration.YAMLConfig;
import com.studentmanager.entity.Student;
import com.studentmanager.exception.PageNotFoundException;
import com.studentmanager.exception.StudentAlreadyExistException;
import com.studentmanager.exception.StudentNameExistException;
import com.studentmanager.exception.StudentNotFoundException;
import com.studentmanager.service.IStudentService;
import com.studentmanager.valid.entity.CustomValidatorStudent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
@Api(value = "Student Resource Rest Endpoint")
public class V1StudentController {

    @Autowired
    IStudentService studentService;

    @Autowired
    CustomValidatorStudent customValidatorStudent;

    @Autowired
    YAMLConfig yamlConfig;

    // Init custom valid
    @InitBinder
    private void bindValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(customValidatorStudent);
    }

    // Show Environment
    @GetMapping("/environment")
    public ResponseEntity<?> getEnvironment() {
        Map<String, String> map = new HashMap<>();
        String environment = studentService.environment();
        map.put("environment", environment);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * API get all student (/students)
     * HTTP GET
     * Method = showAll
      * @return ResponseEntity<>(List<Student>, HttpStatus.OK)
     */
    @GetMapping("students")
    @ApiOperation(value = "Return list student")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful!")
            }
    )
    public ResponseEntity<?> showAll() {
        List<Student> studentList = studentService.findAll();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    /**
     * API get list student by page(/students/paging)
     * HTTP GET
     * Method = showStudentByPage
     * @RequestParam (required = false, defaultValue = "1") Integer page
     * @RequestParam (required = false, defaultValue = "3") Integer size
     * @return ResponseEntity<>(List<Student>, HttpStatus.OK)
     */
    @GetMapping("students/paging")
    @ApiOperation(value = "Return list student by paging")
    public ResponseEntity<?> showStudentByPage(@Valid @Min(1) @RequestParam(required = false, defaultValue = "1") Integer page,
                                               @Valid @Min(0) @RequestParam(required = false, defaultValue = "3") Integer size) {

        System.out.println("Go here");
        List<Student> studentList = studentService.findStudentByPage(page, size);
        if (studentList == null) {
            throw new PageNotFoundException("Page Not Found!");
        }
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    /**
     * API get all student and sort(/students/sort)
     * HTTP GET
     * Method = showStudentAndSort
     * @RequestParam (required = false, defaultValue = "name") String field
     * @RequestParam (required = false, defaultValue = "ASC") String sort
     * @return ResponseEntity<>(List<Student>, HttpStatus.OK)
     */
    @ApiOperation(value = "Return list student by sort")
    @GetMapping("students/sort")
    public ResponseEntity<?> showStudentAndSort(@RequestParam(required = false, defaultValue = "name") String field,
                                               @RequestParam(required = false, defaultValue = "ASC") String sort) {
        Sort sortable = Sort.by(field);
        sortable = sort.equals("ASC") ? sortable.ascending() : sortable.descending();

        return new ResponseEntity<>(studentService.findStudentAndSort(sortable), HttpStatus.OK);
    }

    /**
     * API get student by keyword(/students/search)
     * HTTP GET
     * Method = showStudentByKeyWord
     * @RequestParam (required = false) Optional<String> keyword
     * @return ResponseEntity<>(Student, HttpStatus.OK)
     */
    @ApiOperation(value = "Return list student by keyword")
    @GetMapping("students/search")
    public ResponseEntity<?> showStudentByKeyWord(@RequestParam(required = false) Optional<String> keyword) {
        return new ResponseEntity<>(studentService.search(keyword), HttpStatus.OK);
    }

    /**
     * API get list student and filter(/students/filter)
     * HTTP GET
     * Method = filterStudent
     * @ModelAttribute Student student
     * @return ResponseEntity<>(List<Student>, HttpStatus.OK)
     */
    @ApiOperation(value = "Return list student by filter")
    @GetMapping("students/filter")
    public ResponseEntity<?> filterStudent(@ModelAttribute Student student) {
        return new ResponseEntity<>(studentService.filter(student), HttpStatus.OK);
    }

    /**
     * API get student by ID(/students/{id})
     * HTTP GET
     * Method = findStudentByID
     * @PathVariable Optional<Long> id
     * @return ResponseEntity<>(Student, HttpStatus.OK)
     */
    @ApiOperation(value = "Return student by id")
    @GetMapping("students/{id}")
    public ResponseEntity<?> findStudentByID(@PathVariable Optional<Long> id) {
        if (studentService.findStudentById(id.get()).isEmpty()) {
            throw new StudentNotFoundException("Student with id " + id.get() + " not found!");
        }

        Optional<Student> student = studentService.findStudentById(id.get());
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    /**
     * API create student(/students)
     * HTTP POST
     * Method = createStudent
     * @RequestBody Student student
     * @return ResponseEntity<>(Student, HttpStatus.OK)
     */
    @ApiOperation(value = "Create student")
    @PostMapping("students")
    public ResponseEntity<?> createStudent(@RequestBody @Valid Student student) {
        // Valid
        int ageStudent = Period.between(LocalDate.now(), student.getBirthday()).getYears();

        if (studentService.findStudentByName(student.getName()).isPresent()) {
            throw new StudentAlreadyExistException("Student with name " + student.getName() + " already existed!");
        }

        if (student.getAddress() == null) {
            student.setAddress(yamlConfig.getAddress());
        }


        if (student.getGender() == null) {
            student.setGender(yamlConfig.getGender());
        }

        Student st = studentService.createStudent(student);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    /**
     * API update student(/students)
     * HTTP PUT
     * Method = updateStudent
     * @RequestBody Student student
     * @PathVariable Optional<Long> id
     * @return ResponseEntity<>(Student, HttpStatus.OK)
     */
    @ApiOperation(value = "Update student by id")
    @PutMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @PathVariable Optional<Long> id) {
        if (studentService.findStudentById(id.get()).isEmpty()) {
            throw new StudentNotFoundException("Student with id " + id.get() + " not found!");
        }

        if (studentService.findStudentByName(student.getName()).isPresent()) {
            throw new StudentNameExistException("Student with name + " + student.getName() + " already existed!");
        }

        if (student.getAddress() == null) {
            student.setAddress(yamlConfig.getAddress());
        }


        if (student.getGender() == null) {
            student.setGender(yamlConfig.getGender());
        }

        Student student1 = studentService.findStudentById(id.get()).get();

        student.setId(id.get());
        student.setCreatedBy(student1.getCreatedBy());
        student.setCreatedDate(student1.getCreatedDate());
        Student st = studentService.updateStudent(student);

        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    /**
     * API update student(/students)
     * HTTP DELETE
     * Method = deleteStudent
     * @PathVariable Optional<Long> id
     * @return ResponseEntity<>(HttpStatus.OK)
     */
    @ApiOperation(value = "Delete student by id")
    @DeleteMapping("students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Optional<Long> id) {
        if (studentService.findStudentById(id.get()).isEmpty()) {
            throw new StudentNotFoundException("Student not found!");
        }

        studentService.deleteStudent(id.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
