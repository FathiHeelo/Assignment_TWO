package EventProcess.Decaretor;

import EventProcess.Event;

public class CompressionDecorator extends EventDataDecorator {

    @Override
    public boolean supports(Event event) {
        return event.isCompress();
    }

    @Override
    public EventData decorate(EventData data, Event event) {
        this.wrappee = data;
        return this;
    }

    @Override
    public String Process() {
        return "CMP(" + wrappee.Process() + ")";
    }
}