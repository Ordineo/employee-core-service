package be.ordina.ordineo.controller;

import be.ordina.ordineo.model.Employee;
import be.ordina.ordineo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;


    @RequestMapping(value = "/linkedin",method = RequestMethod.PUT)
    public ResponseEntity getEmployeeLinkedin(@RequestBody Employee employee){
       Employee user= employeeRepository.findByUsernameIgnoreCase(employee.getUsername());
        user.setFirstName(employee.getFirstName());
        user.setUsername(employee.getUsername());
        user.setLastName(employee.getLastName());
        user.setDescription(employee.getDescription());
        user.setProfilePicture(employee.getProfilePicture());
        user.setLinkedin(employee.getLinkedin());
        user.setFunction(employee.getFunction());

        employeeRepository.save(user);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
