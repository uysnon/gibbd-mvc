package com.rseu.kondrashov.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NeedRefreshEventData implements EventData {
    String tag;
    Object oldValue;

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public Object getValue() {
        return oldValue;
    }
}
