package hu.nye.progkor.converter;

import hu.nye.progkor.model.UserDTO;
import hu.nye.progkor.model.request.UserRequest;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class convert web layer model to service layer model.
 */
@Component
public class ConverterRequestToUserDataObject implements Converter<UserRequest, UserDTO> {

  @Override
  public UserDTO convert(@NonNull final UserRequest source) {
    return new UserDTO(null,
            source.getUsername(),
            source.getEmailAddress(),
            source.getPassword());
  }
}
