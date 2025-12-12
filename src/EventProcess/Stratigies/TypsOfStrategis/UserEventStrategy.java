package EventProcess.Stratigies.TypsOfStrategis;

import EventProcess.Event;
import EventProcess.Stratigies.EventStrategy;

public class UserEventStrategy implements EventStrategy {
    @Override
    public void execute(Event event) {
        System.out.println("[USER] user-specific step for " + event.getId());

    }
}
