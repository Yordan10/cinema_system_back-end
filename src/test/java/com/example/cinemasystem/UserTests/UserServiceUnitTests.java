package com.example.cinemasystem.UserTests;



import com.example.cinemasystem.service.AuthenticationUserDetailService;
import com.example.cinemasystem.service.UserService;
import com.example.cinemasystem.serviceInterfaces.IAccount;
import com.example.cinemasystem.model.UserAccount;
import com.example.cinemasystem.model.request.UserCreateRequest;
import com.example.cinemasystem.repository.AccountDalJDBC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTests {

    @Autowired
    UserService userService;

    @MockBean
    AccountDalJDBC accountDalJDBC;



    @Test
     void getAllAccountsTest()  {

        List<IAccount> users = new ArrayList<>();
        UserAccount user =  new UserAccount(1,"ne","ekke","keke","err","lastname","USER");
        users.add(user);

        when(accountDalJDBC.getAllAccounts()).thenReturn(Stream.of(user)
                .collect(Collectors.toList()));
        Assertions.assertEquals(new ResponseEntity(users, HttpStatus.OK),userService.returnAllAccounts());

    }
    @Test
     void getAllAccountsFailTest() throws IOException {


        when(accountDalJDBC.getAllAccounts()).thenReturn(null);
        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.returnAllAccounts());

    }
    @Test
     void getAccountByIdTest(){


        int id = 10;
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","USER");
        UserAccount user2 = new UserAccount();
        user.getId();
        user.getEmail();
        user.getFirstName();
        user.getUsername();
        user.getRole();
        user.getPassword();
        user.getLastName();

        user.setEmail("asd");
        user.setUsername("asd");
        user.setPassword("ahjfd");
        user.setLastName("asd");
        user.setRole("ajsd");
        user.setId(1);
        user.setFirstName("aj");

        when(accountDalJDBC.getAccountById(id))
                .thenReturn(user);

        Assertions.assertEquals(new ResponseEntity(user, HttpStatus.OK),userService.returnAccountByID(id));

    }
//
    @Test
     void getAccountByIdFailTest(){

        int id = 9;

        when(accountDalJDBC.getAccountById(id))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.returnAccountByID(id));


    }

    @Test
     void getAccountByUsernameTest(){

        String username = "name";
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","AA");

        when(accountDalJDBC.getAccountByUsername(username))
                .thenReturn(user);

        Assertions.assertEquals(user.getId(),userService.getAccountByUsername(username).getId());

    }
    @Test
     void returnAccountByUsernameTest(){

        String username = "name";
        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","role");

        when(accountDalJDBC.getAccountByUsername(username))
                .thenReturn(user);

        Assertions.assertEquals(new ResponseEntity(user, HttpStatus.OK),userService.returnAccountByUsername(username));

    }
    @Test
     void getAccountByUsernameFailTest(){

        String username = "name";
        when(accountDalJDBC.getAccountByUsername(username))
                .thenReturn(null);

        Assertions.assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND),userService.returnAccountByUsername(username));

    }
    @Test
     void UserRegistrationTest(){

        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUsername("secret");
        userCreateRequest.setPassword("taina");
        userCreateRequest.setEmail("com");
        userCreateRequest.setLastName("yo");
        userCreateRequest.setId(1);
        userCreateRequest.setRole("role");

        UserAccount user = new UserAccount(10,"nz","jsd","hshja","jsd","hds","role");

        when(accountDalJDBC.addAccount(user)).thenReturn(true);

        Assertions.assertEquals(true,userService.userRegistration(userCreateRequest));
    }
    /*@Test
    void getAllAccountsAsync(){
        List<IAccount> users = new ArrayList<>();
        UserAccount user =  new UserAccount(1,"ne","ekke","keke","err","lastname","USER");
        users.add(user);

        when(accountDalJDBC.getAllAccounts()).thenReturn(Stream.of(user)
                .collect(Collectors.toList()));


        Assertions.assertEquals(CompletableFuture.completedFuture(users),userService.getAllAccountsAsync());

    }*/
}
