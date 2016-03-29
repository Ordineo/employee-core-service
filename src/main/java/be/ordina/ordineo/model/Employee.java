package be.ordina.ordineo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Identifiable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee  implements Identifiable<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2 ,max = 25)
    @Column(name="username",unique = true)
    private String username;
    @NotNull
    @Size(min=2 ,max = 30)
    private String firstName;
    @NotNull
    @Size(min=2 ,max = 30)
    private String lastName;
    private String linkedin;

    @NotNull
    private String email;

    @Size(min=8,max=16)
    private String phoneNumber;

    @NotNull
    @Size(min = 2, max = 52)
    private String function;

    @OneToOne(cascade = CascadeType.ALL)
    private Unit unit;

    @Column(length = 2048)
    @Size(max=2048)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate birthDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate hireDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate resignationDate;
}
