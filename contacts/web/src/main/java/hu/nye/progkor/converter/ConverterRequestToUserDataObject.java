package hu.nye.progkor.converter;

import hu.nye.progkor.model.UserDto;
import hu.nye.progkor.model.request.UserRequest;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class convert web layer model to service layer model.
 */
@Component
public class ConverterRequestToUserDataObject implements Converter<UserRequest, UserDto> {

  @Override
  public UserDto convert(@NonNull final UserRequest source) {
    return new UserDto(null,
            source.getUsername(),
            source.getEmailAddress(),
            source.getPassword());
  }
}
