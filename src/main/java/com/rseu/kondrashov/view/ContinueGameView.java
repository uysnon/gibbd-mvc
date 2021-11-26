package com.rseu.kondrashov.view;

import com.rseu.kondrashov.controller.GameController;

import java.util.Map;
import java.util.function.Supplier;

public class ContinueGameView extends GameView {
    public final static String ID = "continue_game";

    public ContinueGameView(ParentView parentView, Supplier<Map<String, Supplier<View>>> viewSupplier, GameController gameController) {
        super(parentView, viewSupplier, gameController);
    }

    @Override
    public String getId() {
        return ID;
    }
}
