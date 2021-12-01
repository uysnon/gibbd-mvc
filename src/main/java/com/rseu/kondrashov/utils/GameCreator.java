package com.rseu.kondrashov.utils;

import com.rseu.kondrashov.model.*;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rseu.kondrashov.model.States.*;

@UtilityClass
public class GameCreator {
    List<String> availableNames = Stream.of(
            "А",
            "B",
            "C",
            "D",
            "E",
            "G",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
    ).collect(Collectors.toList());


    public Game createGame(GameParams gameParams) {
        List<PersonProcess> personProcesses = createPersons(gameParams);
        Gibbd gibbd = createGibbd(gameParams);
        personProcesses
                .stream()
                .map(PersonProcess::getPerson)
                .forEach(p -> {
                    p.getListeners().add(gibbd);
                    p.setStateInstance(
                            gibbd.getStateStoragesMap().get(
                                    gibbd
                                            .getStateChain()
                                            .getAvailableStates()
                                            .get(0)
                            ).tryToGetInstance()
                    );
                });
        return new Game(personProcesses, gibbd);
    }

    private List<PersonProcess> createPersons(GameParams gameParams) {
        Collections.shuffle(availableNames);
        List<PersonProcess> personProcesses = new ArrayList<>();
        for (int i = 0; i < gameParams.getPersonCount(); i++) {
            personProcesses.add(
                    new PersonProcess(
                            Person.builder()
                                    .id(availableNames.get(i) + "-id")
                                    .name(availableNames.get(i))
                                    .listeners(new ArrayList<>())
                                    .build()
                    )
            );
        }
        return personProcesses;
    }

    private Gibbd createGibbd(GameParams gameParams) {
        Gibbd gibbd = new Gibbd();
        Map<State, StateInstanceStorage> statesStorage = createStatesStorage(gameParams);
        gibbd.setStateStoragesMap(statesStorage);
        gibbd.setStateChain(createStateChain(statesStorage.keySet()));
        return gibbd;
    }

    private Map<State, StateInstanceStorage> createStatesStorage(GameParams gameParams) {
        Map<State, StateInstanceStorage> map = new LinkedHashMap<>();
        putState(map, NEW_COMERS_WAITING_STATE, 1, 1, 1500);
        putState(map, NEW_COMERS_WINDOW_STATE, gameParams.getNewComerWindowsCount(), 1, 5000);
        putState(map, WAITING_ROOM_BEFORE_INSPECTION, 1, 1, 1500);
        putState(map, INSPECTION_STATE, gameParams.getInspectionPlacesCount(), 1, 15000);
        putState(map, WAITING_ROOM_AFTER_INSPECTION, 1, 1, 1500);
        putState(map, REPEATED_COMERS_STATE, gameParams.getRepeatedComerWindowsCount(), 15000, 5);
        return map;
    }

    private StateChain createStateChain(Collection<State> states) {
        return new StateChain(
                List.copyOf(states)
        );
    }

    private void putState(Map<State, StateInstanceStorage> map,
                          States stateType,
                          int instancesCount,
                          double speedPerMs,
                          double totalWork) {
        State newComersWaitingState = stateType.createState();
        map.put(
                newComersWaitingState,
                stateType.createInstanceStorage(
                        newComersWaitingState,
                        instancesCount,
                        speedPerMs,
                        totalWork
                ));
    }
}
