# Event Processing System - Refactored Version
Java
Design Patterns
Repository: https://github.com/FathiHeelo/Refactored-TMPS-System-Running.git
Project Overview
This repository contains my complete refactoring of the TaskMaster Processing System (TMPS), a job processing and resource management application designed to handle various job types such as email sending, data processing, and report generation.
Starting from a deliberately naive and flawed initial codebase, I thoroughly analyzed its issues (including SOLID violations, tight coupling, code duplication, poor cohesion, misuse of inheritance, incomplete TODOs, and structural problems), documented them, and transformed it into a clean, modular, extensible, and maintainable system. In addition to the four mandatory design patterns specified in the assignment (Connection Pool, Prototype, Strategy, Proxy), I incorporated additional patterns like Singleton (for managing the connection pool) and Factory (for strategy selection) to further enhance the architecture.
Key Refactorings & Design Patterns Applied
1. Decorator Pattern (Dynamic Behavior Addition)

Used to add shared behaviors (e.g., validation, encryption handling, logging) dynamically without modifying the core event processor class
Implemented EventProcessorDecorator abstract class
Created concrete decorators: ValidationDecorator, NotificationDecorator, EncryptionDecorator (as applicable based on event details)
Allows wrapping the base processor to extend functionality at runtime, ensuring flexibility for future changes in event structures

2. Observer Pattern (Notification Mechanism)

Employed to decouple the event processor from dependent modules, enabling loose coupling for notifications
Introduced EventObserver interface
Implemented concrete observers for other modules (e.g., LoggingObserver, ExternalModuleObserver)
The EventProcessor acts as the subject, notifying observers upon successful event processing or key state changes
Removes hard-coded notification logic, making the system easier to extend with new observers
3. Connection Pool (Efficient Resource Management with Singleton)

Implemented as a thread-safe Singleton to ensure a single instance manages up to 10 reusable database connections
acquire() method blocks or queues when no connections are available
release() safely returns connections to the pool
All job executions obtain connections via the Proxy pattern
Additional Pattern: Singleton ensures global access and controlled instantiation of the pool

4. Prototype Pattern (Fast Job Templating)

Replaced expensive from-scratch job template creation with efficient cloning
Implemented JobPrototype interface
Created concrete prototypes: EmailJobTemplate, DataProcessingJobTemplate, ReportJobTemplate
Built JobTemplateRegistry to store and retrieve reusable templates

5. Strategy Pattern (Flexible Job Execution with Factory)

Eliminated long if/else chains and hard-coded type checks in JobExecutor
Introduced JobStrategy interface
Implemented concrete strategies: EmailJobStrategy, DataProcessingStrategy, ReportGenerationStrategy
Created JobStrategyFactory to map job types to appropriate strategies
Additional Pattern: Factory provides a clean way to create and select strategies dynamically

6. Proxy Pattern (Controlled & Monitored Execution)

Added a proxy layer for secure and monitored job execution
Handles user permission validation
Logs job start/end events
Measures execution time
Automatically acquires and releases connections from the pool
Delegates to the real executor while keeping direct access available for internal use

Benefits of the Refactoring

Scalability: Efficient connection reuse via Singleton-managed pooling prevents resource exhaustion
Extensibility: New job types can be added via Factory and Strategy without modifying existing code
Maintainability: Clear separation of concerns, reduced duplication, and strong adherence to SOLID principles
Performance: Reduced object creation overhead through Prototype cloning
Reliability: Controlled execution with logging, timing, and permission checks via Proxy

Technologies Used

Language: Java 17+
Concurrency: BlockingQueue and synchronization for thread safety
Core Concepts: Design Patterns (Prototype, Strategy, Proxy, Singleton, Factory)


How to Run
Bashgit clone https://github.com/FathiHeelo/Refactored-TMPS-System-Running.git
cd Refactored-TMPS-System-Running

Open the project in your favorite Java IDE (IntelliJ IDEA or Eclipse recommended)
Run the Main class to see sample job executions (email, data processing, report)
Observe console output for validation, processing, connection pooling, timing, and execution flow

What I Learned

Practical application of multiple design patterns (including additional ones like Singleton and Factory) to resolve real architectural problems
Systematic identification and documentation of code smells, design flaws, and principle violations
Building scalable, enterprise-ready systems in Java that adapt to evolving requirements
Importance of combining patterns for optimal flexibility and performance in complex systems

Future Enhancements (Ideas)

Integrate with a real database (currently simulated)
Add asynchronous job processing using ExecutorService
Develop a simple web or desktop UI for job submission and monitoring
Implement job persistence and scheduling features

Refactored by: Fathi Heelo 🚀
License: MIT - Feel free to fork, explore, and contribute!
