package config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ProducerResources {

    @Resource(name = "jdbc/MysqlDB")
    private DataSource dataSource;

    @Produces
    @RequestScoped
    @MysqlConn
    private Connection produceConnection() throws SQLException {
        return dataSource.getConnection();
    }


    public void close(@Disposes @MysqlConn Connection connection) throws SQLException {
        connection.close();
        System.out.println("Cerrando la conexión a la BD");
    }
}
