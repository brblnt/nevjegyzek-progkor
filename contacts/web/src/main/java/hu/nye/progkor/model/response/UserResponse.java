package hu.nye.progkor.model.response;

/**
 * User web layer representation.
 */
public record UserResponse(Long id, String username, String emailAddress, String password) {

}
