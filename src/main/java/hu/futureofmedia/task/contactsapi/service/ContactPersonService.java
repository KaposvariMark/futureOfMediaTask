package hu.futureofmedia.task.contactsapi.service;

import hu.futureofmedia.task.contactsapi.entities.ContactPerson;
import hu.futureofmedia.task.contactsapi.exceptions.ResourceNotFoundException;
import hu.futureofmedia.task.contactsapi.repositories.ContactPersonRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonService {

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    public ContactPerson createContactPerson(ContactPerson contactPerson) {
        return contactPersonRepository.save(contactPerson);
    }

    public List<ContactPerson> listContactPersons() {
        return contactPersonRepository.findAll();
    }

    public ContactPerson getContactById(Long id) {
        return contactPersonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact Person was not found with id: " + id));
    }

    public void deleteContactById(Long id) {
        contactPersonRepository.deleteById(id);
    }
}
