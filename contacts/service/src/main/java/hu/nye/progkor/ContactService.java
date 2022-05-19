package hu.nye.progkor;

import java.util.List;
import java.util.Optional;

import hu.nye.progkor.exception.NotFoundException;
import hu.nye.progkor.model.Contact;
import hu.nye.progkor.model.ContactDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Main class of service layer for contacts.
 */
@Service
@Slf4j
public class ContactService {

  private final Repository repository;
  private final Converter<ContactDto, Contact> convertDataObjetToEntity;
  private final Converter<Contact, ContactDto> convertEntityToDataObject;

  @Autowired
  public ContactService(Repository repository,
                        Converter<ContactDto, Contact> convertDataObjetToEntity,
                        Converter<Contact, ContactDto> convertEntityToDataObject) {
    this.repository = repository;
    this.convertDataObjetToEntity = convertDataObjetToEntity;
    this.convertEntityToDataObject = convertEntityToDataObject;
  }

  /**
   * Get all contact from the database.
   *
   * @return service layer compatible list with data.
   */
  public List<ContactDto> getAllContacts() {
    log.info("Fetch all from database.");
    return repository.findAll().stream()
            .map(convertEntityToDataObject::convert)
            .toList();
  }

  /**
   * Get contact from the database.
   *
   * @return service layer compatible model with data.
   */
  public ContactDto getContact(final Long id) {
    log.info("Get contact from database by id: " + id + " .");
    return repository.findById(id)
            .map(convertEntityToDataObject::convert)
            .orElseThrow(() ->
                    new NotFoundException("Nincs ilyen azonosítóval elátott névjegy:" + id + "."));
  }

  /**
   * Create new record in the database.
   */
  public ContactDto createContact(final ContactDto contact) {
    log.info("Create new contact.");
    return Optional.ofNullable(contact)
            .map(convertDataObjetToEntity::convert)
            .map(repository::save)
            .map(convertEntityToDataObject::convert)
            .orElseThrow(() ->
                    new IllegalArgumentException("Nincs ilyen névjegy " + contact + "."));
  }

  /**
   * Update existing contact.
   *
   * @return with the new contact.
   */
  public ContactDto updateContact(final Long id, final ContactDto newData) {
    log.info("Update contact (id: " + id + " ) with new data values.");
    final Contact contact = repository.findById(id)
            .orElseThrow(() ->
                    new NotFoundException("Nincs ilyen azonosítóval elátott névjegy:" + id + "."));

    contact.setFirstName(newData.firstName());
    contact.setLastName(newData.lastName());
    contact.setBirthday(newData.birthday());
    contact.setPhoneNumber(newData.phoneNumber());
    contact.setEmailAddress(newData.emailAddress());
    contact.setAddress(newData.address());
    contact.setOther(newData.other());

    final Contact newContact = repository.save(contact);
    return convertEntityToDataObject.convert(newContact);
  }

  public void deleteContact(final Long id) {
    log.info("Delete contact id: " + id + " .");
    repository.deleteById(id);
  }
}
