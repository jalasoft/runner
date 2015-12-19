package cz.jalasoft.runner.infrastructure.persistence.type;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Duration;
import java.util.Objects;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/19/15.
 */
public class DurationType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[] {Types.VARCHAR};
    }

    @Override
    public Class returnedClass() {
        return Duration.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return Objects.equals(o, o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        String durationString = StringType.INSTANCE.nullSafeGet(resultSet, strings[0], sessionImplementor);
        Duration duration = Duration.parse(durationString);

        return duration;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        Duration duration = (Duration) o;
        String durationString = duration.toString();
        StringType.INSTANCE.nullSafeSet(preparedStatement, durationString, i, sessionImplementor);
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return (Duration) o;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
