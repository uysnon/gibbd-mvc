package com.rseu.kondrashov.utils;

import com.rseu.kondrashov.model.GameParams;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameParamsManager {
    public GameParams getGameParams() {
        GameParams gameParams = new GameParams();
        gameParams.setPersonCount(15);
        gameParams.setNewComerWindowsCount(10);
        gameParams.setInspectionPlacesCount(5);
        gameParams.setRepeatedComerWindowsCount(2);
        return gameParams;
    }

    public void saveGameParams(GameParams gameParams) {
        // TODO
    }
}
