package be.ordina.ordineo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee  implements Identifiable<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firsName;
    private String lastName;
    private String linkedin;
    private String email;
    private String phoneNumber;
    private String function;
    private Unit unit;
    private String description;
    private String profilePicture;
    private Gender gender;

    private LocalDate birthDate;
    private LocalDate hireDate;
    private LocalDate startDate;
    private LocalDate resignationDate;




}
