package hu.nye.progkor.model.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * User web layer representation.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class UserRequest {

    private final String username;
    private final String emailAddress;
    private final String password;
}
