package hu.nye.progkor;

import hu.nye.progkor.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa Repository for storage contacts.
 */
public interface Repository extends JpaRepository<Contact, Long> {
}
