package hu.nye.progkor.converter;

import hu.nye.progkor.model.ContactDto;
import hu.nye.progkor.model.request.ContactRequest;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class convert web layer model to service layer model.
 */
@Component
public class ConverterRequestToDataObject implements Converter<ContactRequest, ContactDto> {

  @Override
  public ContactDto convert(@NonNull final ContactRequest request) {
    return new ContactDto(null,
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
