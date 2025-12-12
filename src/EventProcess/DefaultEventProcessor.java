package EventProcess;

import EventProcess.Connictions.Database;
import EventProcess.Decaretor.EventData;
import EventProcess.Decaretor.EventDataPipelineBuilder;
import EventProcess.Stratigies.EventStrategy;
import EventProcess.Stratigies.EventStrategyFactory;
import EventProcess.Stratigies.TypsOfStrategis.UserEventStrategy;

public class DefaultEventProcessor implements EventProcsss {

    private final Database database;
    private final EventStrategyFactory strategyFactory;
    private final EventDataPipelineBuilder pipelineBuilder;

    public DefaultEventProcessor(Database database,
                                 EventStrategyFactory strategyFactory,
                                 EventDataPipelineBuilder pipelineBuilder) {
        this.database = database;
        this.strategyFactory = strategyFactory;
        this.pipelineBuilder = pipelineBuilder;
    }

    @Override
    public void process(Event event) {

        EventData data = pipelineBuilder.build(event);
        String finalData = data.Process();

        event.setId(
                System.currentTimeMillis()
                        + "-" + Math.abs(finalData.hashCode())
        );
        database.save(event.getId(), finalData);

        EventStrategy strategy =
                strategyFactory.register(event.getType(), new UserEventStrategy());
        strategy.execute(event);
    }
}