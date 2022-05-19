package hu.nye.progkor.model;

/**
 * Service layer representation of a user.
 */
public record UserDto(
        Long id,
        String username,
        String emailAddress,
        String password) {

}