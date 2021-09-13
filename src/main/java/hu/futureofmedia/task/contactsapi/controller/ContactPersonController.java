package hu.futureofmedia.task.contactsapi.controller;

import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.exceptions.ResourceNotFoundException;
import hu.futureofmedia.task.contactsapi.repositories.ContactPersonRepository;
import hu.futureofmedia.task.contactsapi.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactPersonController {

    @Autowired
    private ContactPersonService contactPersonService;


    @PostMapping("/create")
    public ResponseEntity<ContactPerson> createContactPerson(@Valid @RequestBody ContactPerson contactPerson){
        return new ResponseEntity<>(contactPersonService.createContactPerson(contactPerson), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<ContactPerson> listContactPersons(){
        return contactPersonService.listContactPersons();
    }

    @GetMapping("/get/{id}")
    public ContactPerson getContactById(@PathVariable Long id){
        return contactPersonService.getContactById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContactById(@PathVariable Long id){
        contactPersonService.deleteContactById(id);
    }
}
