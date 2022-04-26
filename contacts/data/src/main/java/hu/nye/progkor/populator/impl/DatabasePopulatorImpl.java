package hu.nye.progkor.populator.impl;

import java.util.List;

import hu.nye.progkor.Repository;
import hu.nye.progkor.model.Contact;
import hu.nye.progkor.populator.DatabasePopulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Implements DatabasePopulator interface and create sample data to present how it works.
 */
@Component
@Slf4j
public class DatabasePopulatorImpl implements DatabasePopulator {

    private static Contact TM = new Contact(1L, "Antonio", "Montana",
            "XXXX-XX-XX", "0612345678", "tony@montanacorp.com", "N.A.",
            "Cuban nationality");
    private static Contact AS = new Contact(2L, "Alejandro", "Sosa",
            "XXXX-XX-XX", "0612345678", "alex@bolivian.cartel", "Cochabamba",
            "");
    private static Contact TS = new Contact(3L, "Anthony John", "Soprano ",
            "1959-XX-XX", "0612345678", "anthonysoprano@hbo.com",
            "Newark, New Jersey", "capo");

    private static final List<Contact> CONTACTS = List.of(TM, AS, TS);

    private final Repository repository;

    public DatabasePopulatorImpl(final Repository contactRepo) {
        this.repository = contactRepo;
    }

    @Override
    public void populateDatabase() {
        log.info("Create stock contacts to database.");
        repository.saveAll(CONTACTS);
    }
}
