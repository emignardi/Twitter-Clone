package org.ac.cst8277.mignardi.eric.messageservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Subscription {
    private int producers_id;
    private int subscribers_id;
}
