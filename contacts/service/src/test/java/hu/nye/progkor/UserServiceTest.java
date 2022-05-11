package hu.nye.progkor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import hu.nye.progkor.model.Contact;
import hu.nye.progkor.model.ContactDTO;
import hu.nye.progkor.model.User;
import hu.nye.progkor.model.UserDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

/**
 * Unit test for {@link UserService}
 */
public class UserServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private Converter<UserDTO, User> convertDataObjectToEntity;
    @Mock
    private Converter<User, UserDTO> convertEntityToDataObject;

    private UserService underTest;

    private final Long ID = 110L;
    private final Long ID2 = 111L;
    private final String USER_NAME = "Béla";
    private final String USER_NAME1 = "Béla1";
    private final String EMAIL_ADDRESS = "sample@email.example";
    private final String EMAIL_ADDRESS1 = "sample@email.example1";
    private final String PASSWORD = "pwd";

    private UserDTO dtoWithId, dtoWithId2;
    private User userWithId, userWithId2;
    private List<User> userList;
    private List<UserDTO> dtoList;

    private final UserDTO falseData = new UserDTO(
            1010L,"nincs","nincs","nincs");
    private final UserDTO falseData2 = new UserDTO(
            1010L,"sample@email.example","nincs","nincs");

    @Test
    public void getAllUsersShouldReturnAListOfContactsCheckFirstMatch() {
        //given
        dtoWithId = new UserDTO(ID, USER_NAME, EMAIL_ADDRESS, PASSWORD);
        userWithId = new User(ID, USER_NAME, EMAIL_ADDRESS, PASSWORD);
        dtoWithId2 = new UserDTO(ID2, USER_NAME1, EMAIL_ADDRESS1, PASSWORD);
        userWithId2 = new User(ID2, USER_NAME1, EMAIL_ADDRESS1, PASSWORD);


        userList = Arrays.asList(userWithId, userWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);

        MockitoAnnotations.openMocks(this);

        underTest = new UserService(repository, convertDataObjectToEntity, convertEntityToDataObject);
        given(repository.findAll()).willReturn(userList);
        given(convertEntityToDataObject.convert(userWithId)).willReturn(dtoWithId);
        //when
        boolean end = false;
        List<UserDTO> except = underTest.getAllUser();
        if (except.get(0).equals(dtoList.get(0)))
            end = true;
        //then
        assertTrue(end);
    }


    @Test
    public void testLoginToTheSiteWithOneCorrectDataShouldReturnFalse() {
        //given
        dtoWithId = new UserDTO(ID, USER_NAME, EMAIL_ADDRESS, PASSWORD);
        userWithId = new User(ID, USER_NAME, EMAIL_ADDRESS, PASSWORD);
        dtoWithId2 = new UserDTO(ID2, USER_NAME1, EMAIL_ADDRESS1, PASSWORD);
        userWithId2 = new User(ID2, USER_NAME1, EMAIL_ADDRESS1, PASSWORD);


        userList = Arrays.asList(userWithId, userWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);
        MockitoAnnotations.openMocks(this);
        underTest = new UserService(repository, convertDataObjectToEntity, convertEntityToDataObject);
        given(convertEntityToDataObject.convert(userWithId)).willReturn(dtoWithId);
        given(convertEntityToDataObject.convert(userWithId2)).willReturn(dtoWithId2);
        given(repository.findAll()).willReturn(userList);

        //when
        boolean end = underTest.loginToTheSite(dtoWithId);
        //then
        assertTrue(end);
    }

    @Test
    public void testLoginToTheSiteWithCorrectDataShouldReturnTrue() {
        //given
        dtoWithId = new UserDTO(ID, USER_NAME, EMAIL_ADDRESS, PASSWORD);
        userWithId = new User(ID, USER_NAME, EMAIL_ADDRESS, PASSWORD);
        dtoWithId2 = new UserDTO(ID2, USER_NAME1, EMAIL_ADDRESS1, PASSWORD);
        userWithId2 = new User(ID2, USER_NAME1, EMAIL_ADDRESS1, PASSWORD);


        userList = Arrays.asList(userWithId, userWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);
        MockitoAnnotations.openMocks(this);
        underTest = new UserService(repository, convertDataObjectToEntity, convertEntityToDataObject);
        given(convertEntityToDataObject.convert(userWithId)).willReturn(dtoWithId);
        given(convertEntityToDataObject.convert(userWithId2)).willReturn(dtoWithId2);
        given(repository.findAll()).willReturn(userList);

        //when
        boolean end = underTest.loginToTheSite(dtoWithId);
        //then
        assertTrue(end);
    }

    @Test
    public void testLoginToTheSiteWithWrongDataShouldReturnFalse() {
        //given
        dtoWithId = new UserDTO(ID, USER_NAME, EMAIL_ADDRESS, PASSWORD);
        userWithId = new User(ID, USER_NAME, EMAIL_ADDRESS, PASSWORD);
        dtoWithId2 = new UserDTO(ID2, USER_NAME1, EMAIL_ADDRESS1, PASSWORD);
        userWithId2 = new User(ID2, USER_NAME1, EMAIL_ADDRESS1, PASSWORD);


        userList = Arrays.asList(userWithId, userWithId2);
        dtoList = Arrays.asList(dtoWithId, dtoWithId2);
        MockitoAnnotations.openMocks(this);
        underTest = new UserService(repository, convertDataObjectToEntity, convertEntityToDataObject);
        given(convertEntityToDataObject.convert(userWithId)).willReturn(dtoWithId);
        given(convertEntityToDataObject.convert(userWithId2)).willReturn(dtoWithId2);
        given(repository.findAll()).willReturn(userList);

        //when
        boolean end = underTest.loginToTheSite(falseData);
        //then
        assertFalse(end);
    }
}