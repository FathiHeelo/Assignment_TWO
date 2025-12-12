package EventProcess;

import EventProcess.Connictions.*;
import EventProcess.Decaretor.EventDataPipelineBuilder;
import EventProcess.Observes.*;
import EventProcess.Stratigies.*;
import EventProcess.Stratigies.TypsOfStrategis.SecurityEventStrategy;
import EventProcess.Stratigies.TypsOfStrategis.SystemEventStrategy;
import EventProcess.Stratigies.TypsOfStrategis.UserEventStrategy;
import EventProcess.prototype.EventTemplateRegistry;
import EventProcess.prototype.Templets.*;

public class App {

    public static void main(String[] args) {

        /* =========================
         * 1️⃣ Infrastructure
         * ========================= */
        ConnectionPool pool = new ConnectionPool();
        Database database = new PooledDatabase(pool);

        /* =========================
         * 2️⃣ Strategy Factory
         * ========================= */
        EventStrategyFactory strategyFactory = new EventStrategyFactory();
        strategyFactory.register("USER", new UserEventStrategy());
        strategyFactory.register("SYSTEM", new SystemEventStrategy());
        strategyFactory.register("SECURITY", new SecurityEventStrategy());

        /* =========================
         * 3️⃣ Decorator Pipeline Builder
         * ========================= */
        EventDataPipelineBuilder pipelineBuilder =
                new EventDataPipelineBuilder();

        /* =========================
         * 4️⃣ Real Event Processor
         * ========================= */
        EventProcsss realProcessor =
                new DefaultEventProcessor(database,
                        strategyFactory,
                        pipelineBuilder);

        /* =========================
         * 5️⃣ Observers
         * ========================= */
        EventNotifier notifier = new EventNotifier();
        notifier.register(new DashboardObserver());
        notifier.register(new LoggerObserver());

        /* =========================
         * 6️⃣ Proxy
         * ========================= */
        EventProcsss processor =
                new EventProcessorProxy(realProcessor, notifier);

        /* =========================
         * 7️⃣ Prototype Templates
         * ========================= */
        EventTemplateRegistry templateRegistry =
                new EventTemplateRegistry();

        templateRegistry.registerTemplate(
                "USER",
                new UserEventTemplate("User login attempt")
        );

        templateRegistry.registerTemplate(
                "SYSTEM",
                new SystemEventTemplate("System backup started")
        );

        templateRegistry.registerTemplate(
                "SECURITY",
                new SecurityEventTemplate(
                        "Unauthorized access",
                        "IP=10.0.0.5"
                )
        );

        /* =========================
         * 8️⃣ Create & Process Events
         * ========================= */

        System.out.println("\n================ USER EVENT ================\n");
        Event userEvent =
                templateRegistry.getTemplate("USER").buildEvent();
        processor.process(userEvent);

        System.out.println("\n============== SYSTEM EVENT ================\n");
        Event systemEvent =
                templateRegistry.getTemplate("SYSTEM").buildEvent();
        processor.process(systemEvent);

        System.out.println("\n============= SECURITY EVENT ===============\n");
        Event securityEvent =
                templateRegistry.getTemplate("SECURITY").buildEvent();
        processor.process(securityEvent);

        System.out.println("\n================= END =================\n");
    }
}