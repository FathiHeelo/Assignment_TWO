package EventProcess.Decaretor;

import EventProcess.Event;
import java.util.ArrayList;
import java.util.List;

public class EventDataPipelineBuilder {

    private final List<EventDataDecorator> decorators = new ArrayList<>();

    public EventDataPipelineBuilder() {
        decorators.add(new EncryptionDecorator());
        decorators.add(new CompressionDecorator());
        decorators.add(new MetadataDecorator());
    }

    public EventData build(Event event) {

        EventData data = new RawEventData(event.getPayload());

        for (EventDataDecorator decorator : decorators) {
            if (decorator.supports(event)) {
                data = decorator.decorate(data, event);
            }
        }

        return data;
    }
}