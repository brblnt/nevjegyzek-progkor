package hu.nye.progkor.converter;

import hu.nye.progkor.model.Contact;
import hu.nye.progkor.model.ContactDto;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Service layer model to Data layer model.
 */
@Component
public class ConverterDataObjectToDataLayerEntity implements Converter<ContactDto, Contact> {

  @Override
  public Contact convert(@NonNull final ContactDto dto) {
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
