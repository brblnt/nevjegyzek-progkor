package hu.nye.progkor.converter;

import hu.nye.progkor.model.User;
import hu.nye.progkor.model.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Service layer model to Data layer model.
 */
@Component
public class ConverterUserObjectToDataLayerEntity implements Converter<UserDto, User> {
  @Override
  public User convert(UserDto source) {
    return new User(
            source.id(),
            source.username(),
            source.emailAddress(),
            source.password()
    );
  }
}
