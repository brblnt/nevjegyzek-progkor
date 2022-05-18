package hu.nye.progkor.converter;

import hu.nye.progkor.model.User;
import hu.nye.progkor.model.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Data layer model to Service layer model.
 */
@Component
public class ConvertDataLayerEntityToUserObject implements Converter<User, UserDTO> {
  @Override
  public UserDTO convert(User source) {
    return new UserDTO(
            source.getId(),
            source.getUserName(),
            source.getEmailAddress(),
            source.getPassword()
    );
  }
}
