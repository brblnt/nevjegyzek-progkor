package hu.nye.progkor;

import java.util.List;

import hu.nye.progkor.model.User;
import hu.nye.progkor.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Main class of service layer for users.
 */
@Service
@Slf4j
public class UserService {

  private final UserRepository repository;
  private final Converter<UserDTO, User> convertDataObjectToEntity;
  private final Converter<User, UserDTO> convertEntityToDataObject;

  @Autowired
  public UserService(UserRepository repository, Converter<UserDTO,
          User> convertDataObjectToEntity, Converter<User,
          UserDTO> convertEntityToDataObject) {
    this.repository = repository;
    this.convertDataObjectToEntity = convertDataObjectToEntity;
    this.convertEntityToDataObject = convertEntityToDataObject;
  }

  /**
   * Get all user from the database.
   *
   * @return web layer compatible list with data.
   */
  public List<UserDTO> getAllUser() {
    log.info("Fetch all from database.");
    return repository.findAll().stream()
            .map(convertEntityToDataObject::convert)
            .toList();
  }

  /**
   * Check the email and the password is correct.
   */
  public boolean loginToTheSite(UserDTO dto) {
    List<UserDTO> list = getAllUser();
    for (UserDTO temp : list) {
      if (temp.emailAddress().equals(dto.emailAddress())) {
        if (temp.password().equals(dto.password())) {
          return true;
        }
      }
    }
    return false;
  }

}
