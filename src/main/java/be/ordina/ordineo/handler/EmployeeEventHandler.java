package be.ordina.ordineo.handler;

import be.ordina.ordineo.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Created by shbe on 27/04/16
 */
@Component
@RepositoryEventHandler(Employee.class)
@PreAuthorize("#employee.username == authentication.name or hasRole('ROLE_ADMIN')")
public class EmployeeEventHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @HandleBeforeCreate
    public void handleBeforeCreate(@Param("employee") Employee e){

    }
    @HandleBeforeSave
    public void handleBeforeSave(@Param("employee") Employee employee){

    }
    @HandleBeforeDelete
    public void handleBeforeDelete(@Param("employee") Employee employee){

    }

}
