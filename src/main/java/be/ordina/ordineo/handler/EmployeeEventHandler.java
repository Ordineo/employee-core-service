package be.ordina.ordineo.handler;

import be.ordina.ordineo.model.Employee;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Employee.class)
public class EmployeeEventHandler {

    @HandleBeforeCreate
    public void handleBeforeCreate(Employee e){


    }
}
