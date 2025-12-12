package EventProcess.prototype.Templets;

import EventProcess.Event;
import EventProcess.prototype.EventPrototype;

public class SystemEventTemplate extends EventPrototype {

    public SystemEventTemplate(String payload) {
        super("SYSTEM", payload, null, false, true, false);
    }

    @Override
    public EventPrototype clonePrototype() {
        return new SystemEventTemplate(payload);
    }

    @Override
    public Event buildEvent() {
        Event e = new Event(type, payload);
        e.setCompress(true);
        return e;
    }
}
