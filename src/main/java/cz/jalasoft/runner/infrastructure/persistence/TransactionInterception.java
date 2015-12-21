package cz.jalasoft.runner.infrastructure.persistence;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
@Aspect
@EnableAspectJAutoProxy
@Configuration
public class TransactionInterception {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SessionProvider sessionProvider;

    @Pointcut("execution(* cz.jalasoft.runner.application.*ApplicationService.*(..))")
    private void applicationServicePointcut() {}

    @Around("applicationServicePointcut()")
    private Object runInTransactionAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Session session = sessionFactory.openSession();
        sessionProvider.setSession(session);

        Transaction transaction = session.beginTransaction();
        try {
            Object result = joinPoint.proceed();
            transaction.commit();
            return result;
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            sessionProvider.removeSession();
        }
    }
}
