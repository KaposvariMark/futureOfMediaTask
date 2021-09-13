package hu.futureofmedia.task.contactsapi.entities;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "contact_persons")
public class ContactPerson {

    @Id
    @GeneratedValue
    private long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    private String phoneNumber; //optional; only E-164 format
//    private String company; // chosable from data base
//    private String comment;
//    private Status status;
//
//    private LocalDate created;
//    private LocalDate modified;


}
