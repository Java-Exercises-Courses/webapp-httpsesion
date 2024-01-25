package config;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ApplicationScoped
public class ProducerResources {

    @Resource(lookup = "java:/MySqlDS")
    private DataSource dataSource;

    @PersistenceUnit(name = "carshopejemplo")
    private EntityManagerFactory emf;

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

    @Produces
    @RequestScoped
    private EntityManager beanEntityManager() {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()){
            entityManager.close();
        }
    }
}
