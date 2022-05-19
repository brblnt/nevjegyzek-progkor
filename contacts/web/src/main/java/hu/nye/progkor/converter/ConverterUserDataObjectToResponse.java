package hu.nye.progkor.converter;

import hu.nye.progkor.model.UserDto;
import hu.nye.progkor.model.response.UserResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class convert service layer model to web layer model.
 */
@Component
public class ConverterUserDataObjectToResponse implements Converter<UserDto, UserResponse> {

  @Override
  public UserResponse convert(@NonNull final UserDto source) {
    return new UserResponse(
            source.id(),
            source.username(),
            source.emailAddress(),
            source.password());
  }
}
