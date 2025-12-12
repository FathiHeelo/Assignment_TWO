package EventProcess;

import EventProcess.Observes.EventNotifier;

public class EventProcessorProxy implements EventProcsss {

    private final EventProcsss realProcessor;
    private final EventNotifier notifier;

    public EventProcessorProxy(EventProcsss realProcessor,
                               EventNotifier notifier) {
        this.realProcessor = realProcessor;
        this.notifier = notifier;
    }

    @Override
    public void process(Event event) {

        if (!validate(event)) {
            System.out.println("[Proxy] Invalid event");
            return;
        }

        event.setId(
                System.currentTimeMillis() + "-" + Math.abs(event.hashCode())
        );

        Event workingCopy = event.clone();

        notifier.notifyObservers(event);

        long start = System.currentTimeMillis();
        try {
            realProcessor.process(workingCopy);
        } finally {
            long time = System.currentTimeMillis() - start;
            System.out.println("[Proxy] Processed in " + time + "ms");
        }
    }

    private boolean validate(Event event) {
        return event != null
                && event.getPayload() != null
                && !event.getPayload().isEmpty();
    }
}