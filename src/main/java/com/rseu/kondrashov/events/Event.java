package com.rseu.kondrashov.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
    String objectSenderId;
    EventData data;
}
