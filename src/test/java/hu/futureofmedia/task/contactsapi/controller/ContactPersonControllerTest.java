package hu.futureofmedia.task.contactsapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.repositories.ContactPersonRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(ContactPersonController.class)
class ContactPersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ContactPersonRepository contactPersonRepository;

    @Test
    void createContactPerson() throws Exception{
        ContactPerson testContactPerson = buildContactPerson(1L,"bird@person.com","Bird","Person","06301233211");

        Mockito.when(contactPersonRepository.save(testContactPerson)).thenReturn(testContactPerson);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/contacts/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(testContactPerson));

        mockMvc.perform(mockRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Bird")));
    }

    @Test
    void listContactPersons() throws Exception {
        List<ContactPerson> records = new ArrayList<>(Arrays.asList(
                buildContactPerson(1L,"test1@person.com","Test1","Person","06301111111"),
                buildContactPerson(1L,"test2@person.com","Test2","Person","06302222222"),
                buildContactPerson(1L,"test3@person.com","Test3","Person","06303333333")
        ));

        Mockito.when(contactPersonRepository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/contacts/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", Matchers.is("Test2")));
    }

    @Test
    void getById() throws Exception{
        ContactPerson testContactPerson = buildContactPerson(1L,"test1@person.com","Test1","Person","06301111111");

        Mockito.when(contactPersonRepository.findById(testContactPerson.getId())).thenReturn(java.util.Optional.of(testContactPerson));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/contacts/get/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Test1")));
    }

    @Test
    void deleteContactPerson_success() throws Exception{
        ContactPerson testContactPerson = buildContactPerson(1L,"test1@person.com","Test1","Person","06301111111");

        Mockito.when(contactPersonRepository.findById(testContactPerson.getId())).thenReturn(Optional.of(testContactPerson));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/contacts/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private ContactPerson buildContactPerson(Long id, String email, String firstName, String lastName, String phoneNumber) {
        return ContactPerson.builder()
                .id(id)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();
    }
}