package EventProcess.Observes;

import EventProcess.Event;

public class DashboardObserver implements EventObserver {

    @Override
    public void update(Event event) {
        System.out.println("[Dashboard] metrics updated for " + event.getId());
    }
}
