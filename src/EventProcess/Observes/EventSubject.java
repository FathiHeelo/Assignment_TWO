package EventProcess.Observes;

import EventProcess.Event;

public interface EventSubject {

    void register(EventObserver observer);
    void unregister(EventObserver observer);
    void notifyObservers(Event event);
}
