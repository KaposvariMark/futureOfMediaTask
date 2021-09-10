package hu.futureofmedia.task.contactsapi.controller;

import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.exceptions.ResourceNotFoundException;
import hu.futureofmedia.task.contactsapi.repositories.ContactPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/contacts")
public class ContactPersonController {

    @Autowired
    private ContactPersonRepository contactPersonRepository;


    @PostMapping("/create")
    public ContactPerson createContactPerson(@Valid @RequestBody ContactPerson contactPerson){
        return contactPersonRepository.save(contactPerson);
    }

    @GetMapping("/list")
    public Page<ContactPerson> listContactPersons(Pageable pageable){
        return contactPersonRepository.findAll(pageable);
    }

//    @GetMapping("/get/{id}")
//    public ContactPerson getById(@PathVariable Long id){
//        ContactPerson cp = contactPersonRepository.getById(id);
//        return cp;
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteContactPerson(Long id){
//        return contactPersonRepository.findById(id)
//                .map(contactPerson -> {
//                    contactPersonRepository.delete(contactPerson);
//                    return ResponseEntity.ok().build();
//                }).orElseThrow(() -> new ResourceNotFoundException("Contact Person was not found with id: " + id));
//    }
}
