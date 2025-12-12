package EventProcess.Connictions;

public class PooledDatabase implements Database {

    private final ConnectionPool pool;

    public PooledDatabase(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void save(String eventId, String data) {
        Connection conn = null;
        try {
            conn = pool.acquire();

            System.out.println(
                    "[DB][" + conn.getId() + "] Saved event "
                            + eventId + " : " + data
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("DB operation interrupted", e);
        } finally {
            if (conn != null) {
                pool.release(conn);
            }
        }
    }
}
