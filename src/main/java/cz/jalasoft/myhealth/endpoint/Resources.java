package cz.jalasoft.myhealth.endpoint;

import cz.jalasoft.myhealth.domain.model.run.DistanceUnit;
import cz.jalasoft.myhealth.domain.model.run.Run;
import cz.jalasoft.myhealth.domain.model.user.User;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static java.time.format.DateTimeFormatter.ISO_DATE;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-09-29.
 */
final class Resources {

    static UserResource user(User user) {

        String emailAddress = user.email().address();
        String firstName = user.firstName();
        String lastName = user.lastName();
        String birthday = user.birthDay().format(DateTimeFormatter.ISO_DATE);

        return new UserResource(emailAddress, firstName, lastName, birthday);
    }

    static RunResource run(Run run) {
        double kilometers = run.distance().in(DistanceUnit.KILOMETER);
        int minutes = (int) run.duration().get(ChronoUnit.MINUTES);
        String date = run.date().format(ISO_DATE);

        return new RunResource(kilometers, minutes, date);
    }

    //------------------------------------------------------
    //------------------------------------------------------

    private Resources() {
        throw new RuntimeException("Cannot instantiate");
    }
}
