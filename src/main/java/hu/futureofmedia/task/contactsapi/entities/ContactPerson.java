package hu.futureofmedia.task.contactsapi.entities;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    private String firstName;
    private String lastName;
    private String email; //+validation
    private String phoneNumber; //optional; only E-164 format
//    private String company; // chosable from data base
//    private String comment;
//    private Status status;
//
//    private LocalDate created;
//    private LocalDate modified;


}
