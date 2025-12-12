package EventProcess.Decaretor;

public class RawEventData implements EventData {

    private final String payload;

    public RawEventData(String payload) {
        this.payload = payload;
    }

    @Override
    public String Process() {
        return payload;
    }
}
