package org.ac.cst8277.mignardi.eric.usermanagementservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Message {
    private int id;
    private String content;
    private int producer_id;
}