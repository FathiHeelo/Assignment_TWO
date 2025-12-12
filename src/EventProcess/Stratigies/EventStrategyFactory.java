package EventProcess.Stratigies;

import java.util.HashMap;
import java.util.Map;

public class EventStrategyFactory {

    private final Map<String, EventStrategy> strategies = new HashMap<>();

    public EventStrategy register(String type, EventStrategy strategy) {
        strategies.put(type, strategy);
        return strategy;
    }

    public EventStrategy getStrategy(String type) {
        EventStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "No strategy registered for type: " + type
            );
        }
        return strategy;
    }


}