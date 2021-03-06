package com.example.cinemasystem.controller;

import com.example.cinemasystem.serviceInterfaces.IAccount;
import com.example.cinemasystem.serviceInterfaces.IUserService;

import com.example.cinemasystem.model.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IUserService userService ;

    @GetMapping
    public CompletableFuture<ResponseEntity> getAllAccounts()
    {
        return userService.getAllAccountsAsync();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IAccount> getAccountById(@PathVariable(value = "id") int id)
    {
        return userService.returnAccountByID(id);
    }

    @GetMapping("/user")
    public ResponseEntity<IAccount> getAccountByUsername()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return userService.returnAccountByUsername(currentPrincipalName);

    }

    @PostMapping("/register")
    public ResponseEntity userRegistration(@RequestBody UserCreateRequest userCreateRequest) {

       userService.userRegistration(userCreateRequest);
       return ResponseEntity.ok().build();
    }
}
