package EventProcess.prototype;

import java.util.HashMap;
import java.util.Map;

public class EventTemplateRegistry {

    private final Map<String, EventPrototype> prototypes = new HashMap<>();

    public void registerTemplate(String key, EventPrototype prototype) {
        prototypes.put(key, prototype);
    }

    public EventPrototype getTemplate(String key) {
        EventPrototype prototype = prototypes.get(key);
        if (prototype == null) return null;
        return prototype.clonePrototype();
    }
}

