package be.ordina.ordineo.model.projection;


import be.ordina.ordineo.model.Employee;
import be.ordina.ordineo.model.Gender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

@Projection(name="aboutProjection", types=Employee.class)
public interface AboutProjection {

    String getFirstName();
    String getLastName();
    String getUsername();
    String getFunction();
    String getDescription();
    @Value("#{target.unit.getName()}")
    String getUnit();
    Gender getGender();
    LocalDate getStartDate();



}
