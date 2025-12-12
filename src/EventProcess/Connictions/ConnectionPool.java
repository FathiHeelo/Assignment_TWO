package EventProcess.Connictions;

import java.util.ArrayDeque;
import java.util.Queue;

public class ConnectionPool {

    private static final int LIMIT = 3;

    private final Queue<Connection> pool;
    private int totalCreated = 0;

    public ConnectionPool() {
        pool = new ArrayDeque<>();
    }

    public synchronized Connection acquire() throws InterruptedException {

        if (!pool.isEmpty()) {
            return pool.poll();
        }

        if (totalCreated < LIMIT) {
            totalCreated++;
            return new Connection("C-" + totalCreated);
        }

        long start = System.currentTimeMillis();
        long timeout = 3000;

        while (pool.isEmpty() && (System.currentTimeMillis() - start) < timeout) {
            wait(timeout - (System.currentTimeMillis() - start));
        }

        if (!pool.isEmpty()) {
            return pool.poll();
        }

        throw new IllegalStateException("No available connections. Try again later.");
    }

    public synchronized void release(Connection connection) {

        if (connection == null) {
            System.out.println("Attempted to release a null connection.");
            return;
        }

        pool.offer(connection);
        notifyAll();
    }
}
