package EventProcess.prototype.Templets;

import EventProcess.Event;
import EventProcess.prototype.EventPrototype;

public class SecurityEventTemplate extends EventPrototype {

    public SecurityEventTemplate(String payload, String metadata) {
        super("SECURITY", payload, metadata, true, true, true);
    }

    @Override
    public EventPrototype clonePrototype() {
        return new SecurityEventTemplate(payload, metadata);
    }

    @Override
    public Event buildEvent() {
        Event e = new Event(type, payload);
        e.setEncrypt(true);
        e.setCompress(true);
        e.setAddMetadata(true);
        e.setMetadata(metadata);
        return e;
    }
}