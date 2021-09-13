package hu.futureofmedia.task.contactsapi.service;

import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.exceptions.ResourceNotFoundException;
import hu.futureofmedia.task.contactsapi.repositories.ContactPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactPersonService {

    public static final int PAGE_SIZE = 10;

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    public List<ContactPerson> listContactPersons() {
        return contactPersonRepository.findAll();
    }

    public List<ContactPerson> listContactPersonsByPage(int pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE);
        Page<ContactPerson> pagedResult = contactPersonRepository.findAll(paging);
        return pagedResult.toList();
    }

    public ContactPerson getContactById(Long id) {
        return contactPersonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Person was not found with id: " + id));
    }

    public ContactPerson createContactPerson(ContactPerson contactPerson) {
        return contactPersonRepository.save(contactPerson);
    }

    public void modifyContactById(Long id, ContactPerson updatedContactPerson) {
        ContactPerson contactPerson = contactPersonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Person was not found with id: " + id));
        contactPerson.setFirstName(updatedContactPerson.getFirstName());
        contactPerson.setLastName(updatedContactPerson.getLastName());
        contactPerson.setEmail(updatedContactPerson.getEmail());
        contactPerson.setPhoneNumber(updatedContactPerson.getPhoneNumber());
        contactPersonRepository.save(contactPerson);
        System.out.println("-----------------> Successfully modified contact person id: " + contactPerson.getId());
    }

    public void deleteContactById(Long id) {
        contactPersonRepository.deleteById(id);
    }

}
