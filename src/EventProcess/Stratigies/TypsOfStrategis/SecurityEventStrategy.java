package EventProcess.Stratigies.TypsOfStrategis;

import EventProcess.Event;
import EventProcess.Stratigies.EventStrategy;

public class SecurityEventStrategy implements EventStrategy {
    @Override
    public void execute(Event event) {
        System.out.println("[SECURITY] extra analysis for " + event.getId());
        System.out.println("[SecurityMonitor] alert for " + event.getId());
    }
}
