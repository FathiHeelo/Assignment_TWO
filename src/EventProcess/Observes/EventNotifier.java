package EventProcess.Observes;

import EventProcess.Event;
import java.util.ArrayList;
import java.util.List;

public class EventNotifier implements EventSubject {

    private final List<EventObserver> observers = new ArrayList<>();
    private Event event;

    public void setEvent(Event event) {
        this.event = event;
        notifyObservers(this.event);
    }

    @Override
    public void register(EventObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(EventObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {
        for (EventObserver o : observers) {
            o.update(event);
        }
    }
}
