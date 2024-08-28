package org.ac.cst8277.mignardi.eric.usermanagementservice.controller;

import lombok.AllArgsConstructor;
import org.ac.cst8277.mignardi.eric.usermanagementservice.model.Role;
import org.ac.cst8277.mignardi.eric.usermanagementservice.repository.RoleDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class RoleController {

    private final RoleDao roleDao;

    @GetMapping("/roles")
    public Flux<Role> getRoles() throws SQLException {
        List<Role> roles = roleDao.findAll();
        return Flux.fromIterable(roles);
    }

    @GetMapping("/roles/{id}")
    public Mono<Role> getRoleById(@PathVariable int id) throws SQLException {
        Role role = roleDao.findById(id);
        return Mono.just(role);
    }

}