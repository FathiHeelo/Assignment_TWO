package EventProcess.Decaretor;

import EventProcess.Event;

public abstract class EventDataDecorator implements EventData {

    protected EventData wrappee;

    public abstract boolean supports(Event event);

    public abstract EventData decorate(EventData data, Event event);
}