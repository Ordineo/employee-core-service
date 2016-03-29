package be.ordina.ordineo.employee.rest;

import be.ordina.ordineo.model.Employee;
import be.ordina.ordineo.model.Gender;
import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class EmployeeTest {



    private LocalValidatorFactoryBean localValidatorFactory;
    private Set<ConstraintViolation<Employee>> constraintViolations;
    private Employee employee;

    @Before
    public void setup() {
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
        employee =createEmployee();
    }

    private Employee createEmployee(){
        Employee employee = new Employee();

        employee.setLastName("vhe");
        employee.setUsername("gide");
        employee.setBirthDate(LocalDate.of(1992, Month.APRIL, 2));
        employee.setFunction("Java Developer");
        employee.setEmail("gide@gmail.com");
        employee.setGender(Gender.FEMALE);
        employee.setHireDate(LocalDate.now());
        employee.setStartDate(LocalDate.now());
        employee.setFirstName("Gina");

        return employee;

    }

    @Test
    public void validateEmployee() {
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().count() == 0);
    }
    @Test
    public void firstNameTooLong() {
        employee.setFirstName("1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "123456");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 2 and 30")).count() > 0);
    }
    @Test
    public void firstNameTooShort() {
        employee.setFirstName("1");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 2 and 30")).count() > 0);

    }
    @Test
    public void firstNameIsNull() {
        employee.setFirstName(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }

    @Test
    public void lastNameTooLong() {
        employee.setLastName("1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "1234567890" +
                "123456");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 2 and 30")).count() > 0);
    }
    @Test
    public void lastNameTooShort() {
        employee.setLastName("1");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 2 and 30")).count() > 0);
    }
    @Test
    public void lastNameIsNull() {
        employee.setLastName(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }

    @Test
    public void usernameTooLong() {
        employee.setUsername("1234567890" +
                "1234567890" +
                "1234567890");
       constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 2 and 25")).count() > 0);
    }
    @Test
    public void usernameTooShort() {
        employee.setUsername("1");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 2 and 25")).count() > 0);
    }
    @Test
    public void usernameIsNull() {
        employee.setUsername(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }
    @Test
    public void emailIsNull() {
        employee.setEmail(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }

    @Test
    public void phoneNumberTooLong() {
       employee.setPhoneNumber("047623678920392839");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 8 and 16")).count() > 0);
    }
    @Test
    public void phoneNumberTooShort() {
        employee.setPhoneNumber("124");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 8 and 16")).count() > 0);
    }

    @Test
    public void functionIsNull() {
        employee.setFunction(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }
    @Test
    public void functionIsTooLong() {
        employee.setFunction("Lorem Ipsum is slechts een proeftekst uit het drukkerij");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 2 and 52")).count() > 0);
    }
    @Test
    public void functionIsTooShort() {
        employee.setFunction("1");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 2 and 52")).count() > 0);
    }

    @Test
    public void descriptionTooLong() {
         employee.setDescription("Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters nam en ze door elkaar husselde om een font-catalogus te maken. Het heeft niet alleen vijf eeuwen overleefd maar is ook, vrijwel onveranderd, overgenomen in elektronische letterzetting. Het is in de jaren '60 populair geworden met de introductie van Letraset vellen met Lorem Ipsum passages en meer recentelijk door desktop publishing software zoals Aldus PageMaker die versies van Lorem Ipsum bevatten.\n" +
                "\n" +
                "Het is al geruime tijd een bekend gegeven dat een lezer, tijdens het bekijken van de layout van een pagina, afgeleid wordt door de tekstuele inhoud. Het belangrijke punt van het gebruik van Lorem Ipsum is dat het uit een min of meer normale verdeling van letters bestaat, in tegenstelling tot \"Hier uw tekst, hier uw tekst\" wat het tot min of meer leesbaar nederlands maakt. Veel desktop publishing pakketten en web pagina editors gebruiken tegenwoordig Lorem Ipsum als hun standaard model tekst, en een zoekopdracht naar \"lorem ipsum\" ontsluit veel websites die nog in aanbouw zijn. Verscheidene versies hebben zich ontwikkeld in de loop van de jaren, soms per ongeluk soms expres (ingevoegde humor en dergelijke).\n" +
                "\n" +
                " \n" +
                "In tegenstelling tot wat algemeen aangenomen wordt is Lorem Ipsum niet zomaar willekeurige tekst. het heeft zijn wortels in een stuk klassieke latijnse literatuur uit 45 v.Chr. en is dus meer dan 2000 jaar oud. Richard McClintock, een professor latijn aan de Hampden-Sydney College in Virginia, heeft één van de meer obscure latijnse woorden, consectetur, uit een Lorem Ipsum passage opgezocht, en heeft tijdens het zoeken naar het woord in de klassieke literatuur de onverdachte bron ontdekt. Lorem Ipsum komt uit de secties 1.10.32 en 1.10.33 van \"de Finibus Bonorum et Malorum\" (De uitersten van goed en kwaad) door Cicero, geschreven in 45 v.Chr. Dit boek is een verhandeling over de theorie der ethiek, erg populair tijdens de renaissance. De eerste regel van Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", komt uit een zin in sectie 1.10.32.\n" +
                "\n" +
                "Het standaard stuk van Lorum Ipsum wat sinds de 16e eeuw wordt gebruikt is hieronder, voor wie er interesse in heeft, weergegeven. Secties 1.10.32 en 1.10.33 van \"de Finibus Bonorum et Malorum\" door Cicero zijn ook weergegeven in hun exacte originele vorm, vergezeld van engelse versies van de 1914 vertaling door H. Rackham.\n" +
                "\n");
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("size must be between 0 and 2048")).count() > 0);
    }

    @Test
    public void genderIsNull() {
        employee.setGender(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }
    @Test
    public void birthDateIsNull() {
        employee.setBirthDate(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }
    @Test
    public void hireDateIsNull() {
        employee.setHireDate(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }
    @Test
    public void startDateIsNull() {
        employee.setStartDate(null);
        constraintViolations = localValidatorFactory
                .validate(employee);
        assertTrue(constraintViolations.stream().filter(m -> m.getMessage().equals("may not be null")).count() > 0);
    }


}
