package hu.nye.progkor.converter;

import hu.nye.progkor.model.User;
import hu.nye.progkor.model.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Data layer model to Service layer model.
 */
@Component
public class ConvertDataLayerEntityToUserObject implements Converter<User, UserDto> {
  @Override
  public UserDto convert(User source) {
    return new UserDto(
            source.getId(),
            source.getUserName(),
            source.getEmailAddress(),
            source.getPassword()
    );
  }
}
