package be.ordina.ordineo.model.projection;


import be.ordina.ordineo.model.Employee;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="searchProjection", types=Employee.class)
public interface SearchProjection {

    String getUsername();
    String getFirstName();
    String getLastName();
}
