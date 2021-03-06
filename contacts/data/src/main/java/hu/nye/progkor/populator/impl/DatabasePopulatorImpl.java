package hu.nye.progkor.populator.impl;

import java.util.List;

import hu.nye.progkor.Repository;
import hu.nye.progkor.UserRepository;
import hu.nye.progkor.model.Contact;
import hu.nye.progkor.model.User;
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

  private static User admin = new User(1L, "admin", "admin@admin.admin", "password");

  private static final List<Contact> CONTACTS = List.of(TM, AS, TS);

  private final Repository repository;
  private final UserRepository userRepository;

  public DatabasePopulatorImpl(final Repository repository, final UserRepository userRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
  }

  @Override
  public void populateDatabase() {
    log.info("Create stock contacts and users to database.");
    saveUsers();
    repository.saveAll(CONTACTS);
  }

  private void saveUsers() {
    if (userRepository.findAll().contains(admin)) {
      return;
    }
    userRepository.save(admin);
  }
}
