package org.ac.cst8277.mignardi.eric.messageservice.controller;

import lombok.AllArgsConstructor;
import org.ac.cst8277.mignardi.eric.messageservice.CustomWebClient;
import org.ac.cst8277.mignardi.eric.messageservice.HttpResponseExtractor;
import org.ac.cst8277.mignardi.eric.messageservice.model.Message;
import org.ac.cst8277.mignardi.eric.messageservice.model.UserHasRole;
import org.ac.cst8277.mignardi.eric.messageservice.repository.MessageDao;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class MessageController {

    private final MessageDao messageDao;

    @GetMapping("/messages")
    public Flux<Message> getMessages() throws SQLException {
        List<Message> messages = messageDao.findAll();
        return Flux.fromIterable(messages);
    }

    @GetMapping("/messages/{id}")
    public Mono<Message> getMessageById(@PathVariable int id) throws SQLException {
        Message message = messageDao.findById(id);
        return Mono.just(message);
    }

    @GetMapping("/messages/producer/{id}")
    public Flux<Message> getMessagesForProducerById(@PathVariable int id) throws SQLException {
        List<Message> messages = messageDao.getMessagesForProducerById(id);
        return Flux.fromIterable(messages);
    }

    @GetMapping("/messages/subscriber/{id}")
    public Flux<Message> getMessagesForSubscriberById(@PathVariable int id) throws SQLException {
        List<Message> messages = messageDao.getMessagesForSubscriberById(id);
        return Flux.fromIterable(messages);
    }

    @PostMapping("messages")
    public Mono<Integer> saveMessage(@RequestBody Message message) throws SQLException {
        CustomWebClient customWebClient = new CustomWebClient();
        return customWebClient.retrieveUmsData("/user_has_roles/" + message.getProducer_id()).flatMap(res -> {
            UserHasRole userHasRole = HttpResponseExtractor.extractDataFromHttpClientResponse(res, UserHasRole.class);
            int count;
            try {
                if (userHasRole.getRoles_id() == 2){
                    count = messageDao.insert(message);
                    return Mono.just(count);
                }
                else {
                    return Mono.empty();
                }
            } catch (SQLException e) {
                return Mono.error(new RuntimeException(e));
            }
        });
    }

    @DeleteMapping("/messages/{id}")
    public Mono<Integer> deleteMessage(@PathVariable int id) throws SQLException {
        int count = messageDao.delete(id);
        return Mono.just(count);
    }

}