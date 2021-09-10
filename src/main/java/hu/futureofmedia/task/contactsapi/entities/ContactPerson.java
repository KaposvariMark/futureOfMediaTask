package hu.futureofmedia.task.contactsapi.entities;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class ContactPerson {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;
    private String email; //+validation
    private String phoneNumber; //optional; only E-164 format
    private String company; // chosable from data base
    private String comment;
    private Status status;

    private LocalDate created;
    private LocalDate modified;
}
