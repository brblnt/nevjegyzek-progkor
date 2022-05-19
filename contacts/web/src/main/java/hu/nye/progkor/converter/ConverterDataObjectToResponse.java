package hu.nye.progkor.converter;

import hu.nye.progkor.model.ContactDto;
import hu.nye.progkor.model.response.ContactResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class convert service layer model to web layer model.
 */
@Component
public class ConverterDataObjectToResponse implements Converter<ContactDto, ContactResponse> {

  @Override
  public ContactResponse convert(@NonNull final ContactDto dto) {
    return new ContactResponse(
            dto.id(),
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
