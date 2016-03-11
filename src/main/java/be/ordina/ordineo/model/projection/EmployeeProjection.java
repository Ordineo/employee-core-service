package be.ordina.ordineo.model.projection;

import be.ordina.ordineo.model.Employee;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="employeeProjection", types=Employee.class)
public interface EmployeeProjection {

    String getUsername();
}
