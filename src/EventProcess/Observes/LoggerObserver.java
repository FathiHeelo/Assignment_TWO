package EventProcess.Observes;

import EventProcess.Event;

public class LoggerObserver implements EventObserver {


    @Override
    public void update(Event e) {
        System.out.println("[LOG] Event logged " + e.getId());
    }
}
