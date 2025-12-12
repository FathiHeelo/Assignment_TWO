package EventProcess.Decaretor;

import EventProcess.Event;

public class MetadataDecorator extends EventDataDecorator {

    private String metadata;

    @Override
    public boolean supports(Event event) {
        return event.isAddMetadata();
    }

    @Override
    public EventData decorate(EventData data, Event event) {
        this.wrappee = data;
        this.metadata = event.getMetadata();
        return this;
    }

    @Override
    public String Process() {
        return "META(" + metadata + ")::" + wrappee.Process();
    }
}