package hu.nye.progkor.converter;

import hu.nye.progkor.model.Contact;
import hu.nye.progkor.model.ContactDTO;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Data layer model to Service layer model.
 */
@Component
public class ConverterDataLayerEntityToDataObject implements Converter<Contact, ContactDTO> {

    @Override
    public ContactDTO convert(@NonNull final Contact contact) {
        return new ContactDTO(
                contact.getId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getBirthday(),
                contact.getPhoneNumber(),
                contact.getEmailAddress(),
                contact.getAddress(),
                contact.getOther()
        );
    }
}
