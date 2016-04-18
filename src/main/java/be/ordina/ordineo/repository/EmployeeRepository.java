package be.ordina.ordineo.repository;

import be.ordina.ordineo.model.Employee;
import be.ordina.ordineo.model.projection.EmployeeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.Resources;

import java.util.List;


@RepositoryRestResource
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long>{

    @RestResource(path="employee",rel="employee")
    Employee findByUsernameIgnoreCase(@Param("username") String username);

    @RestResource(path="unit",rel="unit")
    Page<Employee> findByUnitName(@Param("unit")String unit,@Param("page")Pageable pageable);

    @RestResource(path = "employeeName",rel = "searchEmployee")
    @Query("select DISTINCT o from Employee o where lower(o.firstName) LIKE CONCAT('%',lower(:name),'%') or lower(o.lastName) LIKE CONCAT('%',lower(:name),'%') ")
    List<Employee> findByFirstNameOrLastName(@Param("name") String name);


}
