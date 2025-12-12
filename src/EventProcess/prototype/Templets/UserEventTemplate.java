package EventProcess.prototype.Templets;

import EventProcess.Event;
import EventProcess.prototype.EventPrototype;

public class UserEventTemplate extends EventPrototype {

    public UserEventTemplate(String payload) {
        super("USER", payload, null, false, false, false);
    }

    @Override
    public EventPrototype clonePrototype() {
        return new UserEventTemplate(payload);
    }

    @Override
    public Event buildEvent() {
        return new Event(type, payload);
    }
}