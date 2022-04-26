package hu.nye.progkor.model.response;

/**
 * Web layer representation of a contact.
 */
public record ContactResponse(Long id, String firstName, String lastName, String birthday, String phoneNumber,
                              String emailAddress, String address, String other) {
}
