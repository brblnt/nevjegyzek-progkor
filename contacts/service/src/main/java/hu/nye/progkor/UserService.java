package hu.nye.progkor;

import java.util.List;

import hu.nye.progkor.model.User;
import hu.nye.progkor.model.UserDto;
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
  private final Converter<UserDto, User> convertDataObjectToEntity;
  private final Converter<User, UserDto> convertEntityToDataObject;

  @Autowired
  public UserService(UserRepository repository, Converter<UserDto,
          User> convertDataObjectToEntity, Converter<User,
          UserDto> convertEntityToDataObject) {
    this.repository = repository;
    this.convertDataObjectToEntity = convertDataObjectToEntity;
    this.convertEntityToDataObject = convertEntityToDataObject;
  }

  /**
   * Get all user from the database.
   *
   * @return web layer compatible list with data.
   */
  public List<UserDto> getAllUser() {
    log.info("Fetch all from database.");
    return repository.findAll().stream()
            .map(convertEntityToDataObject::convert)
            .toList();
  }

  /**
   * Check the email and the password is correct.
   */
  public boolean loginToTheSite(UserDto dto) {
    List<UserDto> list = getAllUser();
    for (UserDto temp : list) {
      if (temp.emailAddress().equals(dto.emailAddress())) {
        if (temp.password().equals(dto.password())) {
          return true;
        }
      }
    }
    return false;
  }

}
