package hu.futureofmedia.task.contactsapi.controller;

import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.repositories.CompanyRepository;
import hu.futureofmedia.task.contactsapi.service.ContactPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactPersonController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContactPersonService contactPersonService;

    @GetMapping("/companies")
    public List<Company> listCompanies(){
        return companyRepository.findAll();
    }

    @GetMapping("/list")
    public List<ContactPerson> listContactPersons(){
        return contactPersonService.listContactPersons();
    }

    @GetMapping("/list/")
    public List<ContactPerson> listContactPersons(@RequestParam(name = "pageNo") int pageNo){
        return contactPersonService.listContactPersonsByPage(pageNo);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContactPerson> getContactById(@PathVariable Long id){
        return new ResponseEntity<>(contactPersonService.getContactById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ContactPerson> createContactPerson(@Valid @RequestBody ContactPerson contactPerson){
        return new ResponseEntity<>(contactPersonService.createContactPerson(contactPerson), HttpStatus.CREATED);
    }

    @PutMapping("/modify/{id}")
    public void modifyContactById(@PathVariable Long id, @RequestBody ContactPerson contactPerson){
        contactPersonService.modifyContactById(id, contactPerson);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContactById(@PathVariable Long id){
        contactPersonService.deleteContactById(id);
    }
}
