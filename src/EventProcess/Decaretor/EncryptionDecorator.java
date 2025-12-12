package EventProcess.Decaretor;

import EventProcess.Event;

public class EncryptionDecorator extends EventDataDecorator {

    @Override
    public boolean supports(Event event) {
        return event.isEncrypt();
    }

    @Override
    public EventData decorate(EventData data, Event event) {
        this.wrappee = data;
        return this;    }





    @Override
    public String Process() {
        return "ENC(" + wrappee.Process() + ")";
    }
}