package interceptors;

import config.MysqlConn;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import services.ServiceException;

import java.sql.Connection;

@TransactionalDB
@Interceptor
public class TransactionalInterceptor {
    @Inject
    @MysqlConn
    private Connection connection;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        if(connection.getAutoCommit()) {
            connection.setAutoCommit(false);
        }

        try {
            Object resultado = invocationContext.proceed();
            connection.commit();
            return resultado;
        } catch (ServiceException e) {
            connection.rollback();
            throw e;
        }
    }
}
