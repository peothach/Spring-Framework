package com.studentmanager.reponsitoty;

import com.studentmanager.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

public interface IStudentRepo extends JpaRepository<Student, Long>,
        JpaSpecificationExecutor<Student> {

    // JpaSpecificationExecutor

    /**
     * Returns a single entity matching the given {@link Specification} or {@link Optional#empty()} if none found.
     *
     * @param spec can be {@literal null}.
     * @return never {@literal null}.
     * @throws org.springframework.dao.IncorrectResultSizeDataAccessException if more than one entity found.
     */
    Optional<Student> findOne(@Nullable Specification<Student> spec);

    /**
     * Returns all entities matching the given {@link Specification}.
     *
     * @param spec can be {@literal null}.
     * @return never {@literal null}.
     */
    List<Student> findAll(@Nullable Specification<Student> spec);

    /**
     * Returns a {@link Page} of entities matching the given {@link Specification}.
     *
     * @param spec     can be {@literal null}.
     * @param pageable must not be {@literal null}.
     * @return never {@literal null}.
     */
    Page<Student> findAll(@Nullable Specification<Student> spec, Pageable pageable);

    /**
     * Returns all entities matching the given {@link Specification} and {@link Sort}.
     *
     * @param spec can be {@literal null}.
     * @param sort must not be {@literal null}.
     * @return never {@literal null}.
     */
    List<Student> findAll(@Nullable Specification<Student> spec, Sort sort);

    /**
     * Returns the number of instances that the given {@link Specification} will return.
     *
     * @param spec the {@link Specification} to count instances for. Can be {@literal null}.
     * @return the number of instances.
     */
    long count(@Nullable Specification<Student> spec);


    // Spring Data Jpa Using JPQL
//    @Query("SELECT s FROM student s WHERE CONCAT(s.name, ' ', s.address) LIKE %:#{#keyword}%")
//    List<Student> search(String keyword);
//
//    @Query("SELECT s FROM student s"
//            + " WHERE s.name LIKE %:#{#student.name != null ? #student.name:''}%"
//            + " AND s.address LIKE %:#{#student.address != null ? #student.address:''}%")
//    List<Student> filter(@Param("student") Student student);

    Optional<Student> findByName(String name);
}