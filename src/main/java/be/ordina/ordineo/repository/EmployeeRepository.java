package be.ordina.ordineo.repository;

import be.ordina.ordineo.model.Employee;
import be.ordina.ordineo.model.projection.EmployeeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


@RepositoryRestResource(excerptProjection = EmployeeProjection.class)
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long>{

    @RestResource(path="employee",rel="employee")
    Employee findByUsername(@Param("username") String username);

    @RestResource(path="unit",rel="unit")
    Page<Employee> findByUnitName(@Param("unit")String unit,@Param("page")Pageable pageable);


}
