package hu.nye.progkor.converter;

import hu.nye.progkor.model.UserDTO;
import hu.nye.progkor.model.response.UserResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class convert service layer model to web layer model.
 */
@Component
public class ConverterUserDataObjectToResponse implements Converter<UserDTO, UserResponse> {

    @Override
    public UserResponse convert(@NonNull final UserDTO source) {
        return new UserResponse(
                source.id(),
                source.username(),
                source.emailAddress(),
                source.password());
    }
}
