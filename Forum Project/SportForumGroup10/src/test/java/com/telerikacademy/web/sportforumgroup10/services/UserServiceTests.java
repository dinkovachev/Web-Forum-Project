package com.telerikacademy.web.sportforumgroup10.services;

import com.telerikacademy.web.sportforumgroup10.exceptions.*;
import com.telerikacademy.web.sportforumgroup10.models.User;
import com.telerikacademy.web.sportforumgroup10.models.UserFilterOptions;
import com.telerikacademy.web.sportforumgroup10.repositories.Contracts.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.telerikacademy.web.sportforumgroup10.Helpers.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    UserRepository mockUserRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void getAllUsers_Should_CallRepository() {
        //Arrange
        UserFilterOptions mockFilterOptions = createMockUserFilterOptions();

        Mockito.when(mockUserRepository.getAllUsers(mockFilterOptions))
                .thenReturn(null);

        //Act, Assert
        userService.getAllUsers(mockFilterOptions);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .getAllUsers(mockFilterOptions);
    }

    @Test
    public void getByID_Should_CallRepository() {
        //Arrange
        User user = createMockUser();

        Mockito.when(mockUserRepository.getById(user.getId()))
                .thenReturn(null);

        //Act, Assert
        userService.getById(user.getId());
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .getById(user.getId());
    }

    @Test
    public void getByFirstName_Should_CallRepository() {
        //Arrange
        User user = createMockUser();
        User userRequester = createMockAdmin();

        Mockito.when(mockUserRepository.getByFirstName(user.getFirstName()))
                .thenReturn(null);

        //Act, Assert
        userService.getByFirstName(user.getFirstName(), userRequester);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .getByFirstName(user.getFirstName());
    }
    @Test
    public void getByEmail_Should_CallRepository() {
        //Arrange
        User user = createMockUser();
        User userRequester = createMockAdmin();

        Mockito.when(mockUserRepository.getByEmail(user.getEmail()))
                .thenReturn(null);

        //Act, Assert
        userService.getByEmail(user.getEmail(), userRequester);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .getByEmail(user.getEmail());
    }

    @Test
    public void getByUsername_Should_CallRepository() {
        //Arrange
        User user = createMockUser();
        User userRequester = createMockAdmin();

        Mockito.when(mockUserRepository.getByUsername(user.getUsername()))
                .thenReturn(null);

        //Act, Assert
        userService.getByUsername(user.getUsername(), userRequester);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .getByUsername(user.getUsername());
    }

    @Test
    public void getByUsernameAuthentication_Should_CallRepository() {
        //Arrange
        User user = createMockUser();

        Mockito.when(mockUserRepository.getByUsername(user.getUsername()))
                .thenReturn(null);

        //Act, Assert
        userService.getByUsernameAuthentication(user.getUsername());
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .getByUsername(user.getUsername());
    }

    @Test
    public void create_should_CallRepository_When_UserIsUnique(){
        //Arrange
        User user = createMockUser();

        Mockito.when(mockUserRepository.getByUsername(user.getUsername()))
                .thenThrow(EntityNotFoundException.class);

        //Act, Assert
        userService.create(user);
        Mockito.verify(mockUserRepository,Mockito.times(1))
                .create(user);
    }

    @Test
    public void create_should_ThrowException_When_UserIsNotUnique(){
        //Arrange
        User user = createMockUser();

        Mockito.when(mockUserRepository.getByUsername(user.getUsername()))
                .thenReturn(user);

        //Act, Assert
        Assertions.assertThrows(EntityDuplicateException.class,
                () -> userService.create(user));
    }

    @Test
    public void update_should_Call_Repository_When_UserIsAuthorizedAndInformationIsAcceptable(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstName");
        userWithNewInformation.setLastName("AnotherLastName");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("AnotherPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        userService.update(userWithNewInformation);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .update(userToUpdate);
    }

    @Test
    public void update_should_Call_Repository_When_FirstNameIsEmpty(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("");
        userWithNewInformation.setLastName("AnotherLastName");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("AnotherPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        userService.update(userWithNewInformation);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .update(userToUpdate);
    }
    @Test
    public void update_should_Call_Repository_When_LastNameIsEmpty(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstName");
        userWithNewInformation.setLastName("");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("AnotherPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        userService.update(userWithNewInformation);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .update(userToUpdate);
    }

    @Test
    public void update_should_Call_Repository_When_PasswordIsEmpty(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstName");
        userWithNewInformation.setLastName("AnotherLastName");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        userService.update(userWithNewInformation);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .update(userToUpdate);
    }

    @Test
    public void update_should_Call_Repository_When_EmailIsEmpty(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstName");
        userWithNewInformation.setLastName("AnotherLastName");
        userWithNewInformation.setEmail("");
        userWithNewInformation.setPassword("AnotherPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        userService.update(userWithNewInformation);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .update(userToUpdate);
    }

    @Test
    public void update_should_Throw_EmailChange_Exception_When_EmailIsTheSame(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstName");
        userWithNewInformation.setLastName("AnotherLastName");
        userWithNewInformation.setEmail("MockEmail@mock.mock");
        userWithNewInformation.setPassword("AnotherMockPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert

        Assertions.assertThrows(EmailChangeProfileError.class,
                ()-> userService.update(userWithNewInformation));
    }

    @Test
    public void update_should_Throw_PasswordChange_Exception_When_PasswordIsTheSame(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstName");
        userWithNewInformation.setLastName("AnotherLastName");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("MockPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        Assertions.assertThrows(PasswordChangeProfileError.class,
                ()-> userService.update(userWithNewInformation));
    }

    @Test
    public void update_should_Throw_FirstNameChange_Exception_When_FirstNameIsShorterThan4Symbols(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("Ano");
        userWithNewInformation.setLastName("AnotherLastName");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("AnotherMockPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        Assertions.assertThrows(FirstNameChangeProfileError.class,
                ()-> userService.update(userWithNewInformation));
    }

    @Test
    public void update_should_Throw_FirstNameChange_Exception_When_FirstNameIsLongerThan32Symbols(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstNameAnotherFirstNameAnotherFirstNameAnotherFirstName" +
                "AnotherFirstNameAnotherFirstNameAnotherFirstNameAnotherFirstNameAnotherFirstNameAnotherFirstName" +
                "AnotherFirstNameAnotherFirstNameAnotherFirstNameAnotherFirstNameAnotherFirstNameAnotherFirstName");
        userWithNewInformation.setLastName("AnotherLastName");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("AnotherMockPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        Assertions.assertThrows(FirstNameChangeProfileError.class,
                ()-> userService.update(userWithNewInformation));
    }

    @Test
    public void update_should_Throw_LastNameChange_Exception_When_LastNameIsShorterThan4Symbols(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstName");
        userWithNewInformation.setLastName("Ano");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("AnotherMockPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        Assertions.assertThrows(LastNameChangeProfileError.class,
                ()-> userService.update(userWithNewInformation));
    }

    @Test
    public void update_should_Throw_LastNameChange_Exception_When_LastNameIsBiggerThan32Symbols(){
        //Arrange
        User userToUpdate = createMockUser();
        User userWithNewInformation = createMockUser();

        userWithNewInformation.setFirstName("AnotherFirstName");
        userWithNewInformation.setLastName("AnotherLastNameAnotherLastNameAnotherLastName" +
                "AnotherLastNameAnotherLastNameAnotherLastNameAnotherLastNameAnotherLastNameAnotherLastName" +
                "AnotherLastNameAnotherLastNameAnotherLastNameAnotherLastName");
        userWithNewInformation.setEmail("AnotherMockEmail@mock.mock");
        userWithNewInformation.setPassword("AnotherMockPassword");

        Mockito.when(mockUserRepository.getById(1))
                .thenReturn(userToUpdate);

        //Act,Assert
        Assertions.assertThrows(LastNameChangeProfileError.class,
                ()-> userService.update(userWithNewInformation));
    }

    @Test
    public void delete_should_Call_Repository_WhenUserIsAuthorized(){
        //Arrange
        User userToDelete = createMockUser();
        User userModifier = createMockUser();

        Mockito.when(mockUserRepository.delete(userToDelete.getId()))
                .thenReturn(userToDelete);

        //Act, Assert
        userService.delete(userToDelete.getId(), userModifier);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .delete(userToDelete.getId());
    }

    @Test
    public void makeUserAdmin_should_Call_Repository_WhenUserIsAuthorized(){
        //Arrange
        User userToMakeAdmin = createMockUser();
        User userModifier = createMockAdmin();

        Mockito.when(mockUserRepository.makeUserAdmin(userToMakeAdmin.getId()))
                .thenReturn(userToMakeAdmin);

        //Act, Assert
        userService.makeUserAdmin(userToMakeAdmin.getId(), userModifier);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .makeUserAdmin(userToMakeAdmin.getId());
    }

    @Test
    public void unmakeUserAdmin_should_Call_Repository_WhenUserIsAuthorized(){
        //Arrange
        User userToMakeAdmin = createMockUser();
        User userModifier = createMockAdmin();

        Mockito.when(mockUserRepository.unmakeUserAdmin(userToMakeAdmin.getId()))
                .thenReturn(userToMakeAdmin);

        //Act, Assert
        userService.unmakeUserAdmin(userToMakeAdmin.getId(), userModifier);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .unmakeUserAdmin(userToMakeAdmin.getId());
    }

    @Test
    public void blockUserAdmin_should_Call_Repository_WhenUserIsAuthorized(){
        //Arrange
        User userToBlock = createMockUser();
        User userModifier = createMockAdmin();

        Mockito.when(mockUserRepository.blockUser(userToBlock.getId()))
                .thenReturn(userToBlock);

        //Act, Assert
        userService.blockUser(userToBlock.getId(), userModifier);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .blockUser(userToBlock.getId());
    }

    @Test
    public void unblockUserAdmin_should_Call_Repository_WhenUserIsAuthorized(){
        //Arrange
        User userToUnblock = createMockUser();
        User userModifier = createMockAdmin();

        Mockito.when(mockUserRepository.unblockUser(userToUnblock.getId()))
                .thenReturn(userToUnblock);

        //Act, Assert
        userService.unblockUser(userToUnblock.getId(), userModifier);
        Mockito.verify(mockUserRepository, Mockito.times(1))
                .unblockUser(userToUnblock.getId());
    }
}
