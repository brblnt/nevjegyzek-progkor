package hu.nye.progkor.populator;

import java.util.List;
import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Populator service add to the database sample contacts.
 */
@Service
@Slf4j
public class DatabasePopulatorService {

  private final List<DatabasePopulator> databasePopulators;

  public DatabasePopulatorService(final List<DatabasePopulator> databasePopulators) {
    this.databasePopulators = databasePopulators;
  }

  @PostConstruct
  public void populateDatabase() {
    log.info("Add sample records for database to present how it work.");
    databasePopulators.forEach(DatabasePopulator::populateDatabase);
  }
}
