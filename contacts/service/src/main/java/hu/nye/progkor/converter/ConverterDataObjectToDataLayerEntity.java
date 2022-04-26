package hu.nye.progkor.converter;

import hu.nye.progkor.model.Contact;
import hu.nye.progkor.model.ContactDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Service layer model to Data layer model.
 */
@Component
@Slf4j
public class ConverterDataObjectToDataLayerEntity implements Converter<ContactDTO, Contact> {

    @Override
    public Contact convert(@NonNull final ContactDTO dto) {
        return new Contact(dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.birthday(),
                dto.phoneNumber(),
                dto.emailAddress(),
                dto.address(),
                dto.other()
        );
    }
}
