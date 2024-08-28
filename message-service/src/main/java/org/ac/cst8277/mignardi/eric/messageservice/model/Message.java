package org.ac.cst8277.mignardi.eric.messageservice.model;

import lombok.*;

@NoArgsConstructor
@Data
public class Message {
    private int id;
    private String content;
    private int producer_id;
}