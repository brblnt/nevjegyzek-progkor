package hu.nye.progkor.model;

/**
 * Service layer representation of a contact.
 */
public record ContactDto(
        Long id,
        String firstName,
        String lastName,
        String birthday,
        String phoneNumber,
        String emailAddress,
        String address,
        String other) {
}
