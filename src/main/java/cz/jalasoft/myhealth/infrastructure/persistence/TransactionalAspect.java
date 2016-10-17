package cz.jalasoft.myhealth.infrastructure.persistence;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
@Aspect
@Component
public class TransactionalAspect {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SessionHolder sessionHolder;

    @Pointcut("execution(* cz.jalasoft.myhealth.application.MyHealthApplicationService.*(..))")
    private void applicationServicePointcut() {}

    @Around("applicationServicePointcut()")
    private Object runInTransactionAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Session session = sessionFactory.openSession();
        sessionHolder.setSession(session);

        Transaction transaction = session.beginTransaction();
        try {
            Object result = joinPoint.proceed();
            transaction.commit();
            return result;
        } catch (Throwable t) {
            transaction.rollback();
            throw t;
        } finally {
            sessionHolder.removeSession();
        }
    }
}
