package com.rseu.kondrashov.view;

import lombok.Data;

@Data
public class SettingsView implements View{
    public final static String ID = "settings";

    @Override
    public void draw() {

    }

    @Override
    public String getId() {
        return null;
    }
}
