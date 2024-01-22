package interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import services.ServiceException;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {
    @Inject
    private EntityManager entityManager;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        try {
            entityManager.getTransaction().begin();
            Object resultado = invocationContext.proceed();
            entityManager.getTransaction().commit();
            return resultado;
        } catch (ServiceException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
