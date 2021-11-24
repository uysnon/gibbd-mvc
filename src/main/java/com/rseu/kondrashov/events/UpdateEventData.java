package com.rseu.kondrashov.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateEventData implements EventData {
    String tag;
    Object oldValue;
    Object newValue;

    public String getTag() {
        return tag;
    }

    public Object getValue() {
        return newValue;
    }
}
