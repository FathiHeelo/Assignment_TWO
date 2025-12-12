package EventProcess.Stratigies.TypsOfStrategis;

import EventProcess.Event;
import EventProcess.Stratigies.EventStrategy;

public class SystemEventStrategy  implements EventStrategy {
    @Override
    public void execute(Event event) {
        System.out.println("[SYSTEM] system audit log " + event.getId());

    }
}
