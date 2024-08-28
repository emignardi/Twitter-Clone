package org.ac.cst8277.mignardi.eric.messageservice.controller;

import lombok.AllArgsConstructor;
import org.ac.cst8277.mignardi.eric.messageservice.model.Subscription;
import org.ac.cst8277.mignardi.eric.messageservice.repository.SubscriptionDao;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionDao subscriptionDao;

    @GetMapping("/subscriptions/{id}")
    public Flux<Subscription> getSubscriptionsForSubscriberById(@PathVariable int id) throws SQLException {
        List<Subscription> subscriptions = subscriptionDao.getSubscriptionsForSubscriberById(id);
        return Flux.fromIterable(subscriptions);
    }

    @PostMapping("/subscriptions")
    public Mono<Integer> createSubscription(Subscription subscription) throws SQLException {
        int count = subscriptionDao.createSubscription(subscription);
        return Mono.just(count);
    }

}