package EventProcess.prototype;

import EventProcess.Event;

public abstract class EventPrototype {

    protected final String type;
    protected final String payload;
    protected final String metadata;
    protected final boolean encrypt;
    protected final boolean compress;
    protected final boolean addMetadata;

    protected EventPrototype(String type,
                             String payload,
                             String metadata,
                             boolean encrypt,
                             boolean compress,
                             boolean addMetadata) {
        this.type = type;
        this.payload = payload;
        this.metadata = metadata;
        this.encrypt = encrypt;
        this.compress = compress;
        this.addMetadata = addMetadata;
    }

    public abstract EventPrototype clonePrototype();
    public abstract Event buildEvent();
}