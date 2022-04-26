package hu.nye.progkor.model.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a request and contains necessary data about the contact.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class ContactRequest {

    private final String firstName;
    private final String lastName;
    private final String birthday;
    private final String phoneNumber;
    private final String emailAddress;
    private final String address;
    private final String other;


}
