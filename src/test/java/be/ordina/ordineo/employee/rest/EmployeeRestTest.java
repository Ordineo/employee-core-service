package be.ordina.ordineo.employee.rest;


import be.ordina.ordineo.EmployeeCoreApplication;
import be.ordina.ordineo.model.Employee;
import be.ordina.ordineo.model.Gender;
import be.ordina.ordineo.model.Unit;
import be.ordina.ordineo.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.Date;



import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.snippet.Attributes.key;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=EmployeeCoreApplication.class)
@ActiveProfiles("test")
public class EmployeeRestTest {


    @Autowired
    private EmployeeRepository employeeRepository;

    private MockMvc mockMvc;

    private ObjectWriter objectWriter;

    @Autowired
    private ObjectMapper objectMapper;

    @Rule
    public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");
    @Autowired
    private WebApplicationContext wac;
    private RestDocumentationResultHandler document;



    @Before
    public void setup() {
        this.document = document("{method-name}");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(documentationConfiguration(this.restDocumentation).uris().withScheme("https").withHost("employee-ordineo.cfapps.io/")).alwaysDo(this.document)
                .build();
        objectWriter = objectMapper.writer();

    }

    @Test
    public void getEmployee() throws Exception {
        this.document.snippets(
                links(
                        halLinks(), linkWithRel("self").description("The employee's resource"),
                        linkWithRel("employee").optional().description("The employee's projection")),
                        responseFields(
                                fieldWithPath("username").description("The employee unique database identifier"),
                                fieldWithPath("firstName").description("The employee's first name"),
                                fieldWithPath("lastName").description("The employee's last name"),
                                fieldWithPath("linkedin").description("The employee's linkedin"),
                                fieldWithPath("email").description("The employee's email"),
                                fieldWithPath("phoneNumber").description("The employee's phone number"),
                                fieldWithPath("function").description("The employee's function"),
                                fieldWithPath("unit").description("The employee's unit").type(Unit.class),
                                fieldWithPath("description").description("The employee's description"),
                                fieldWithPath("gender").description("The employee's gender").type(Gender.class),
                                fieldWithPath("birthDate").description("When the person was born").type(LocalDate.class),
                                fieldWithPath("hireDate").optional().description("When the person was hired").type(LocalDate.class),
                                fieldWithPath("startDate").description("The employee's start date").type(LocalDate.class),
                                fieldWithPath("resignationDate").description("The employee's resignation date").type(LocalDate.class),
                                 fieldWithPath("_links").description("links to other resources")
                        ));

        mockMvc.perform(
                get("/employees/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("Nivek")))
                .andExpect(jsonPath("$.firstName", is("Kevin")))
                .andExpect(jsonPath("$.lastName", is("Van Houtte")))
                .andExpect(jsonPath("$.email", is("kevin@gmail.com")))
                .andExpect(jsonPath("$.phoneNumber", is("047637287")))
                .andExpect(jsonPath("$.function", is("Software Developer Java")))
                .andExpect(jsonPath("$.description", is("Lorem Ipsum is slechts een proeftekst uit het drukkerij- en zetterijwezen. Lorem Ipsum is de standaard proeftekst in deze bedrijfstak sinds de 16e eeuw, toen een onbekende drukker een zethaak met letters")))
                .andExpect(jsonPath("$.gender", is("MALE")))
                .andExpect(jsonPath("$.birthDate", is("1992-07-25")))
                .andExpect(jsonPath("$.hireDate", is("2015-08-03")))
                .andExpect(jsonPath("$.startDate", is("2015-11-01")))
                .andExpect(jsonPath("$._links.self.href", endsWith("/employees/1")))
                .andExpect(jsonPath("$._links.employee.href", endsWith("/employees/1{?projection}")));
    }


    @Test
      public void updateEmployee() throws Exception {
        Employee employee = employeeRepository.findByUsernameIgnoreCase("Nivek");
        employee.setFirstName("Ken");
        String string = objectWriter.writeValueAsString(employee);

        mockMvc.perform(put("/employees/" +employee.getId()).content(string).contentType(APPLICATION_JSON)).andExpect(status().isNoContent());
    }
    @Test
    public void updateEmployeeWithNullValueShouldReturnBadRequest() throws Exception {
        Employee employee = employeeRepository.findByUsernameIgnoreCase("Nivek");
        employee.setFirstName(null);
        String string = objectWriter.writeValueAsString(employee);

        mockMvc.perform(put("/employees/" +employee.getId()).content(string).contentType(APPLICATION_JSON)).andExpect(status().isBadRequest());
    }
    @Test
    public void postEmployee() throws Exception{
        Employee employee = employeeRepository.findByUsernameIgnoreCase("Nivek");
        employee.setId(null);
        employee.setUsername("Keloggs");
        String string = objectWriter.writeValueAsString(employee);

        ConstrainedFields fields = new ConstrainedFields(Employee.class);

        this.document.snippets(
                requestFields(
                        fields.withPath("username").description("The employee unique database identifier"),
                        fields.withPath("firstName").description("The employee's first name"),
                        fields.withPath("lastName").description("The employee's last name"),
                        fields.withPath("linkedin").description("The employee's linkedin"),
                        fields.withPath("email").description("The employee's email"),
                        fields.withPath("phoneNumber").description("The employee's phone number"),
                        fields.withPath("function").description("The employee's function"),
                        fields.withPath("unit").description("The employee's unit").type(Unit.class),
                        fields.withPath("description").description("The employee's description"),
                        fields.withPath("gender").description("The employee's gender").type(Gender.class),
                        fields.withPath("birthDate").description("When the person was born").type(LocalDate.class),
                        fields.withPath("hireDate").optional().description("When the person was hired").type(LocalDate.class),
                        fields.withPath("startDate").description("The employee's start date").type(LocalDate.class),
                        fields.withPath("resignationDate").description("The employee's resignation date").type(LocalDate.class)
                       ));

        mockMvc.perform(post("/employees").content(string).contentType(MediaTypes.HAL_JSON)).andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");



    }
    @Test
    public void syncLinkedinWithProfile() throws Exception {

        Employee employee = employeeRepository.findByUsernameIgnoreCase("Nivek");
        employee.setId(null);
        String string = objectWriter.writeValueAsString(employee);
        mockMvc.perform(put("/linkedin").content(string).contentType(APPLICATION_JSON)).andExpect(status().isAccepted());
    }

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}
