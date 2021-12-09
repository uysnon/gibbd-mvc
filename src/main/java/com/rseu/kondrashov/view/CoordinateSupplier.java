package com.rseu.kondrashov.view;

import com.rseu.kondrashov.model.Person;
import com.rseu.kondrashov.model.States;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CoordinateSupplier {
    private Map<String, StateSlotManager> statesSlots;

    private static final int IN_COLUMN_GAP = 90;
    private static final int COLUMN_GAP = 210;


    private static final int COLUMN_1_1 = 30;
    private static final int COLUMN_1_2 = COLUMN_1_1 + IN_COLUMN_GAP;

    private static final int COLUMN_2_1 = COLUMN_1_1 + COLUMN_GAP;
    private static final int COLUMN_2_2 =  COLUMN_2_1 + IN_COLUMN_GAP;

    private static final int COLUMN_3_1 = COLUMN_2_1 + COLUMN_GAP;
    private static final int COLUMN_3_2 =  COLUMN_3_1 + IN_COLUMN_GAP;

    private static final int COLUMN_4_1 = COLUMN_3_1 + COLUMN_GAP;
    private static final int COLUMN_4_2 =  COLUMN_4_1 + IN_COLUMN_GAP;

    private static final int COLUMN_5_1 = COLUMN_4_1 + COLUMN_GAP;
    private static final int COLUMN_5_2 =  COLUMN_5_1 + IN_COLUMN_GAP;

    private static final int COLUMN_6_1 = COLUMN_5_1 + COLUMN_GAP;
    private static final int COLUMN_6_2 =  COLUMN_6_1 + IN_COLUMN_GAP;

    private static final int ROW_1 = 150;
    private static final int ROW_2 = 200;
    private static final int ROW_3 = 250;
    private static final int ROW_4 = 300;
    private static final int ROW_5 = 350;
    private static final int ROW_6 = 400;
    private static final int ROW_7 = 450;
    private static final int ROW_8 = 500;
    private static final int ROW_9 = 550;


    public CoordinateSupplier() {
        init();
    }

    public synchronized Coordinate stayOnFieldAndGetCoordinate(Person person) {
        if (person.getStateInstance() != null) {
            return statesSlots
                    .get(person.getStateInstance().getState().getId())
                    .stayOnSlotAndGetCoordinate();
        } else {
            return null;
        }
    }

    public void clear() {
        statesSlots.values().forEach(StateSlotManager::clear);
    }

    private void init() {
        statesSlots = new HashMap<>();
        initNewComersWaitingState();
        initNewComersWindowState();
        initNewComersWaitingRoomBeforeInspectionState();
        initInspectionState();
        initWaitingRoomAfterInspection();
        initRepeatedComersState();
    }

    private void initNewComersWaitingState() {
        statesSlots.put(
                States.NEW_COMERS_WAITING_STATE.getId(),
                StateSlotManager.
                        builder()
                        .slots(List.of(
                                new StateSlot(COLUMN_1_1, ROW_1),
                                new StateSlot(COLUMN_1_1, ROW_2),
                                new StateSlot(COLUMN_1_1, ROW_3),
                                new StateSlot(COLUMN_1_1, ROW_4),
                                new StateSlot(COLUMN_1_1, ROW_5),
                                new StateSlot(COLUMN_1_1, ROW_6),
                                new StateSlot(COLUMN_1_1, ROW_7),
                                new StateSlot(COLUMN_1_1, ROW_8),
                                new StateSlot(COLUMN_1_1, ROW_9),
                                new StateSlot(COLUMN_1_2, ROW_1),
                                new StateSlot(COLUMN_1_2, ROW_2),
                                new StateSlot(COLUMN_1_2, ROW_3),
                                new StateSlot(COLUMN_1_2, ROW_4),
                                new StateSlot(COLUMN_1_2, ROW_5),
                                new StateSlot(COLUMN_1_2, ROW_6),
                                new StateSlot(COLUMN_1_2, ROW_7),
                                new StateSlot(COLUMN_1_2, ROW_8),
                                new StateSlot(COLUMN_1_2, ROW_9)
                        ))
                        .build()
        );
    }

    private void initNewComersWindowState() {
        statesSlots.put(
                States.NEW_COMERS_WINDOW_STATE.getId(),
                StateSlotManager.
                        builder()
                        .slots(List.of(
                                new StateSlot(COLUMN_2_1, ROW_1),
                                new StateSlot(COLUMN_2_1, ROW_2),
                                new StateSlot(COLUMN_2_1, ROW_3),
                                new StateSlot(COLUMN_2_1, ROW_4),
                                new StateSlot(COLUMN_2_1, ROW_5),
                                new StateSlot(COLUMN_2_1, ROW_6),
                                new StateSlot(COLUMN_2_1, ROW_7),
                                new StateSlot(COLUMN_2_1, ROW_8),
                                new StateSlot(COLUMN_2_1, ROW_9),
                                new StateSlot(COLUMN_2_2, ROW_1),
                                new StateSlot(COLUMN_2_2, ROW_2),
                                new StateSlot(COLUMN_2_2, ROW_3),
                                new StateSlot(COLUMN_2_2, ROW_4),
                                new StateSlot(COLUMN_2_2, ROW_5),
                                new StateSlot(COLUMN_2_2, ROW_6),
                                new StateSlot(COLUMN_2_2, ROW_7),
                                new StateSlot(COLUMN_2_2, ROW_8),
                                new StateSlot(COLUMN_2_2, ROW_9)
                        ))
                        .build()
        );
    }

    private void initNewComersWaitingRoomBeforeInspectionState() {
        statesSlots.put(
                States.WAITING_ROOM_BEFORE_INSPECTION.getId(),
                StateSlotManager.
                        builder()
                        .slots(List.of(
                                new StateSlot(COLUMN_3_1, ROW_1),
                                new StateSlot(COLUMN_3_1, ROW_2),
                                new StateSlot(COLUMN_3_1, ROW_3),
                                new StateSlot(COLUMN_3_1, ROW_4),
                                new StateSlot(COLUMN_3_1, ROW_5),
                                new StateSlot(COLUMN_3_1, ROW_6),
                                new StateSlot(COLUMN_3_1, ROW_7),
                                new StateSlot(COLUMN_3_1, ROW_8),
                                new StateSlot(COLUMN_3_1, ROW_9),
                                new StateSlot(COLUMN_3_2, ROW_1),
                                new StateSlot(COLUMN_3_2, ROW_2),
                                new StateSlot(COLUMN_3_2, ROW_3),
                                new StateSlot(COLUMN_3_2, ROW_4),
                                new StateSlot(COLUMN_3_2, ROW_5),
                                new StateSlot(COLUMN_3_2, ROW_6),
                                new StateSlot(COLUMN_3_2, ROW_7),
                                new StateSlot(COLUMN_3_2, ROW_8),
                                new StateSlot(COLUMN_3_2, ROW_9)
                        ))
                        .build()
        );
    }

    private void initInspectionState() {
        statesSlots.put(
                States.INSPECTION_STATE.getId(),
                StateSlotManager.
                        builder()
                        .slots(List.of(
                                new StateSlot(COLUMN_4_1, ROW_1),
                                new StateSlot(COLUMN_4_1, ROW_2),
                                new StateSlot(COLUMN_4_1, ROW_3),
                                new StateSlot(COLUMN_4_1, ROW_4),
                                new StateSlot(COLUMN_4_1, ROW_5),
                                new StateSlot(COLUMN_4_1, ROW_6),
                                new StateSlot(COLUMN_4_1, ROW_7),
                                new StateSlot(COLUMN_4_1, ROW_8),
                                new StateSlot(COLUMN_4_1, ROW_9),
                                new StateSlot(COLUMN_4_2, ROW_1),
                                new StateSlot(COLUMN_4_2, ROW_2),
                                new StateSlot(COLUMN_4_2, ROW_3),
                                new StateSlot(COLUMN_4_2, ROW_4),
                                new StateSlot(COLUMN_4_2, ROW_5),
                                new StateSlot(COLUMN_4_2, ROW_6),
                                new StateSlot(COLUMN_4_2, ROW_7),
                                new StateSlot(COLUMN_4_2, ROW_8),
                                new StateSlot(COLUMN_4_2, ROW_9)
                        ))
                        .build()
        );
    }

    private void initWaitingRoomAfterInspection() {
        statesSlots.put(
                States.WAITING_ROOM_AFTER_INSPECTION.getId(),
                StateSlotManager.
                        builder()
                        .slots(List.of(
                                new StateSlot(COLUMN_5_1, ROW_1),
                                new StateSlot(COLUMN_5_1, ROW_2),
                                new StateSlot(COLUMN_5_1, ROW_3),
                                new StateSlot(COLUMN_5_1, ROW_4),
                                new StateSlot(COLUMN_5_1, ROW_5),
                                new StateSlot(COLUMN_5_1, ROW_6),
                                new StateSlot(COLUMN_5_1, ROW_7),
                                new StateSlot(COLUMN_5_1, ROW_8),
                                new StateSlot(COLUMN_5_1, ROW_9),
                                new StateSlot(COLUMN_5_2, ROW_1),
                                new StateSlot(COLUMN_5_2, ROW_2),
                                new StateSlot(COLUMN_5_2, ROW_3),
                                new StateSlot(COLUMN_5_2, ROW_4),
                                new StateSlot(COLUMN_5_2, ROW_5),
                                new StateSlot(COLUMN_5_2, ROW_6),
                                new StateSlot(COLUMN_5_2, ROW_7),
                                new StateSlot(COLUMN_5_2, ROW_8),
                                new StateSlot(COLUMN_5_2, ROW_9)
                        ))
                        .build()
        );
    }

    private void initRepeatedComersState() {
        statesSlots.put(
                States.REPEATED_COMERS_STATE.getId(),
                StateSlotManager.
                        builder()
                        .slots(List.of(
                                new StateSlot(COLUMN_6_1, ROW_1),
                                new StateSlot(COLUMN_6_1, ROW_2),
                                new StateSlot(COLUMN_6_1, ROW_3),
                                new StateSlot(COLUMN_6_1, ROW_4),
                                new StateSlot(COLUMN_6_1, ROW_5),
                                new StateSlot(COLUMN_6_1, ROW_6),
                                new StateSlot(COLUMN_6_1, ROW_7),
                                new StateSlot(COLUMN_6_1, ROW_8),
                                new StateSlot(COLUMN_6_1, ROW_9),
                                new StateSlot(COLUMN_6_2, ROW_1),
                                new StateSlot(COLUMN_6_2, ROW_2),
                                new StateSlot(COLUMN_6_2, ROW_3),
                                new StateSlot(COLUMN_6_2, ROW_4),
                                new StateSlot(COLUMN_6_2, ROW_5),
                                new StateSlot(COLUMN_6_2, ROW_6),
                                new StateSlot(COLUMN_6_2, ROW_7),
                                new StateSlot(COLUMN_6_2, ROW_8),
                                new StateSlot(COLUMN_6_2, ROW_9)
                        ))
                        .build()
        );
    }


    @Data
    @Builder
    private static class StateSlotManager {
        private List<StateSlot> slots;

        public Coordinate stayOnSlotAndGetCoordinate() {
            for (StateSlot slot : slots) {
                if (!slot.isOccupied()) {
                    slot.setOccupied(true);
                    return slot.getCoordinate();
                }
            }
            return new Coordinate(0, 0);
        }

        public void clear() {
            slots.forEach(s -> s.setOccupied(false));
        }
    }

    @Data
    private static class StateSlot {
        private Coordinate coordinate;
        private boolean isOccupied;

        public StateSlot(Coordinate coordinate) {
            this.coordinate = coordinate;
            this.isOccupied = false;
        }

        public StateSlot(int x, int y) {
            this.coordinate = new Coordinate(x, y);
        }
    }
}
