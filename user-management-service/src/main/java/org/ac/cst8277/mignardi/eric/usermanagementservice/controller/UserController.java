package org.ac.cst8277.mignardi.eric.usermanagementservice.controller;

import lombok.AllArgsConstructor;
import org.ac.cst8277.mignardi.eric.usermanagementservice.model.User;
import org.ac.cst8277.mignardi.eric.usermanagementservice.repository.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserDao userDao;

    // Current User Details
    @GetMapping("/user")
    public OAuth2User user(@AuthenticationPrincipal OAuth2User user) {
        return user;
    }

    @GetMapping("/users")
    public Mono<ResponseEntity<List<User>>> getUsers (@AuthenticationPrincipal OAuth2User user) throws SQLException {
        if (user.getAttribute("login").equals("emignardi")){
            List<User> users = userDao.findAll();
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body(users));
        }
        else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
    }

    @GetMapping("/users/{id}")
    public Mono<ResponseEntity<User>> getUser (@AuthenticationPrincipal OAuth2User user, @PathVariable int id) throws SQLException {
        if (user.getAttribute("login").equals("emignardi")){
            User user1 = userDao.findById(id);
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body(user1));
        }
        else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
    }

    @PostMapping("/users")
    public Mono<ResponseEntity<Integer>> saveUser (@AuthenticationPrincipal OAuth2User user, @RequestBody User user1) throws SQLException {
        if (user.getAttribute("login").equals("emignardi")){
            int count = userDao.insert(user1);
            return Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(count));
        }
        else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
    }

    @PutMapping("/users/{id}")
    public Mono<ResponseEntity<Integer>> updateUser (@AuthenticationPrincipal OAuth2User user, @PathVariable int id, @RequestBody User user2) throws SQLException {
        if (user.getAttribute("login").equals("emignardi")){
            int count = userDao.update(user2);
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body(count));
        }
        else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
    }

    @DeleteMapping("/users/{id}")
    public Mono<ResponseEntity<Integer>> deleteUser (@AuthenticationPrincipal OAuth2User user, @PathVariable int id) throws SQLException {
        if (user.getAttribute("login").equals("emignardi")){
            int count = userDao.delete(id);
            return Mono.just(ResponseEntity.status(HttpStatus.OK).body(count));
        }
        else {
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
        }
    }

}