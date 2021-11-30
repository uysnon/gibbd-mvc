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

    public CoordinateSupplier() {
        init();
    }

    public synchronized Coordinate stayOnFieldAndGetCoordinate(Person person) {
        return statesSlots.get(person.getStateInstance().getState().getId())
                .stayOnSlotAndGetCoordinate();
    }

    public void clear() {
        statesSlots.values().forEach(StateSlotManager::clear);
    }

    private void init() {
        statesSlots = new HashMap<>();
        initNewComersWaitingState();
    }

    private void initNewComersWaitingState() {
        statesSlots.put(
                States.NEW_COMERS_WAITING_STATE.getId(),
                StateSlotManager.
                        builder()
                        .slots(List.of(
                                new StateSlot(80, 100),
                                new StateSlot(80, 150),
                                new StateSlot(80, 200),
                                new StateSlot(80, 250),
                                new StateSlot(80, 300),
                                new StateSlot(80, 350)
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
