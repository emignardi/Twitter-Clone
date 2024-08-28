package org.ac.cst8277.mignardi.eric.usermanagementservice.controller;

import lombok.AllArgsConstructor;
import org.ac.cst8277.mignardi.eric.usermanagementservice.model.UserHasRole;
import org.ac.cst8277.mignardi.eric.usermanagementservice.repository.UserHasRoleDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserHasRoleController {

    private final UserHasRoleDao userHasRoleDao;

    @GetMapping("/user_has_roles")
    public Flux<UserHasRole> getAllUserHasRoles() throws SQLException {
        List<UserHasRole> userHasRoles = userHasRoleDao.findAll();
        return Flux.fromIterable(userHasRoles);
    }

    @GetMapping("user_has_roles/{id}")
    public Mono<UserHasRole> getUserHasRoleById(@PathVariable int id) throws SQLException {
        UserHasRole userHasRole = userHasRoleDao.findById(id);
        return Mono.just(userHasRole);
    }

}