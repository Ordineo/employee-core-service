package be.ordina.ordineo.controller;

import be.ordina.ordineo.model.Employee;
import be.ordina.ordineo.repository.EmployeeRepository;
import com.netflix.governator.annotations.binding.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeRestController {

    @Autowired
    EmployeeRepository employeeRepository;


    @RequestMapping(value = "/linkedin/{username}",method = RequestMethod.PUT)
    public ResponseEntity getEmployeeLinkedin(@PathVariable String username,@RequestBody Employee employee){
        employee.setUsername(username);
        employeeRepository.save(employee);

        return new ResponseEntity(HttpStatus.ACCEPTED);




    }
}
