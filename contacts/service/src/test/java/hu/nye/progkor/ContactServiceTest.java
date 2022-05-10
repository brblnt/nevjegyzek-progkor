package hu.nye.progkor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import hu.nye.progkor.model.Contact;
import hu.nye.progkor.model.ContactDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;

/**
 * Unit test for {@link ContactService}
 */
public class ContactServiceTest {


    private ContactDTO dtoWithId, dtoWithId2, dtoWithoutId, dtoWithoutId2;
    private Contact contactWithId, contactWithId2, contactWithoutId, contactWithoutId2;
    private List<Contact> contactList;
    private List<ContactDTO> dtoList;

    @Mock
    private Repository repository;
    @Mock
    private Converter<ContactDTO, Contact> convertDataObjetToEntity;
    @Mock
    private Converter<Contact, ContactDTO> convertEntityToDataObject;

    private ContactService underTest;

    private final Long ID = 110L;
    private final Long ID2 = 111L;
    private final String FIRST_NAME = "Béla";
    private final String LAST_NAME = "Pepsi";
    private final String BIRTHDAY = "XXXX-XX-XX";
    private final String PHONE_NUMBER = "XX-XX-XXX-XXXX";
    private final String EMAIL_ADDRESS = "sample@email.example";
    private final String ADDRESS = "N.A.";
    private final String OTHER = "EMPTY";

    @Test
    public void getAllContatctShouldReturnAListOfContactsCheckFirstMatch() {
        //given
        dtoWithId = new ContactDTO(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithId2 = new ContactDTO(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId2 = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactWithId = new Contact(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithId2 = new Contact(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId2 = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactList = Arrays.asList(contactWithId, contactWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);
        MockitoAnnotations.openMocks(this);
        underTest = new ContactService(repository, convertDataObjetToEntity, convertEntityToDataObject);

        given(repository.findAll()).willReturn(contactList);
        given(convertEntityToDataObject.convert(contactWithId)).willReturn(dtoWithId);
        //when
        boolean end = false;
        List<ContactDTO> except = underTest.getAllContacts();
        if (except.get(0).equals(dtoList.get(0)))
            end = true;
        //then
        assertTrue(end);
    }

    @Test
    public void getContactWithIdShouldReturnContact() {
        //given
        dtoWithId = new ContactDTO(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithId2 = new ContactDTO(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId2 = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactWithId = new Contact(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithId2 = new Contact(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId2 = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactList = Arrays.asList(contactWithId, contactWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);
        MockitoAnnotations.openMocks(this);
        underTest = new ContactService(repository, convertDataObjetToEntity, convertEntityToDataObject);
        given(repository.findById(ID)).willReturn(java.util.Optional.ofNullable(contactWithId));
        given(convertEntityToDataObject.convert(contactWithId)).willReturn(dtoWithId);
        //when
        boolean end = false;
        ContactDTO except = underTest.getContact(ID);
        if (except.equals(dtoWithId))
            end = true;
        //then
        assertTrue(end);
    }


    @Test
    public void cretateContactWithoudIdShouldReturnWithId() {
        //given
        dtoWithId = new ContactDTO(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithId2 = new ContactDTO(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId2 = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactWithId = new Contact(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithId2 = new Contact(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId2 = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactList = Arrays.asList(contactWithId, contactWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);
        MockitoAnnotations.openMocks(this);
        underTest = new ContactService(repository, convertDataObjetToEntity, convertEntityToDataObject);
        given(repository.save(contactWithoutId)).willReturn(contactWithId);
        given(convertEntityToDataObject.convert(contactWithId)).willReturn(dtoWithId);
        given(convertDataObjetToEntity.convert(dtoWithoutId)).willReturn(contactWithoutId);
        //when
        boolean end = false;
        ContactDTO except = underTest.createContact(dtoWithoutId);
        if(except.equals(dtoWithId))
            end = true;
        //then
        assertTrue(end);
    }

    @Test
    public void deleteContactById() {
        //given
        dtoWithId = new ContactDTO(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithId2 = new ContactDTO(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId2 = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactWithId = new Contact(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithId2 = new Contact(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId2 = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactList = Arrays.asList(contactWithId, contactWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);
        MockitoAnnotations.openMocks(this);
        underTest = new ContactService(repository, convertDataObjetToEntity, convertEntityToDataObject);
        //when
        underTest.deleteContact(ID);
        //then
        verify(repository).deleteById(ID);
    }

    @Test
    public void updateContactById() {
        //given
        dtoWithId = new ContactDTO(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithId2 = new ContactDTO(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        dtoWithoutId2 = new ContactDTO(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactWithId = new Contact(ID, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithId2 = new Contact(ID2, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);
        contactWithoutId2 = new Contact(null, FIRST_NAME, LAST_NAME, BIRTHDAY, PHONE_NUMBER, EMAIL_ADDRESS, ADDRESS, OTHER);

        contactList = Arrays.asList(contactWithId, contactWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);
        MockitoAnnotations.openMocks(this);
        underTest = new ContactService(repository, convertDataObjetToEntity, convertEntityToDataObject);
        given(convertEntityToDataObject.convert(contactWithId)).willReturn(dtoWithId);
        given(repository.save(contactWithId)).willReturn(contactWithId);
        given(repository.findById(ID)).willReturn(java.util.Optional.ofNullable(contactWithId));
        //when
        boolean end = false;
        ContactDTO except = underTest.updateContact(ID, dtoWithId);
        if (except.equals(dtoWithId))
            end = true;
        //then
        assertTrue(end);
    }
}
