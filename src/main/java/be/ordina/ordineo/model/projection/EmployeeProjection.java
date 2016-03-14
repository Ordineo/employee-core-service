package be.ordina.ordineo.model.projection;

import be.ordina.ordineo.model.Employee;
import be.ordina.ordineo.model.Gender;
import be.ordina.ordineo.model.Unit;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

@Projection(name="employeeProjection", types=Employee.class)
public interface EmployeeProjection {

    String getUsername();
    String getFirstName();
    String getLastName();
    String getLinkedin();
    String getEmail();
    String getPhoneNumber();
    String getFunction();
    Unit getUnit();
    String getDescription();
    String getProfilePicture();
    Gender getGender();
    LocalDate getBirthDate();
    LocalDate getHireDate();
    LocalDate getStartDate();
    LocalDate getResignationDate();

}
