package vote.infrastructure.db;

import org.flywaydb.core.Flyway;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class DatabaseMigration implements ServletContextListener {

    @Resource(lookup = "java:app/vote-db")
    private DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Flyway flyway = new Flyway();

        flyway.setDataSource(this.ds);
        flyway.clean();
        flyway.migrate();
    }

    @Override public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
