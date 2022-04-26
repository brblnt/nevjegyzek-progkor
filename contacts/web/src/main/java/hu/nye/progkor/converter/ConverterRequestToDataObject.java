package hu.nye.progkor.converter;

import hu.nye.progkor.model.ContactDTO;
import hu.nye.progkor.model.request.ContactRequest;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class convert web layer model to service layer model.
 */
@Component
public class ConverterRequestToDataObject implements Converter<ContactRequest, ContactDTO> {

    @Override
    public ContactDTO convert(@NonNull final ContactRequest request) {
        return new ContactDTO(null,
                request.getFirstName(),
                request.getLastName(),
                request.getBirthday(),
                request.getPhoneNumber(),
                request.getEmailAddress(),
                request.getAddress(),
                request.getOther()
        );
    }
}
