package cz.jalasoft.myhealth.application;

import cz.jalasoft.myhealth.application.exception.NoSuchUserException;
import cz.jalasoft.myhealth.application.exception.UserAlreadyExistsException;
import cz.jalasoft.myhealth.domain.model.run.Distance;
import cz.jalasoft.myhealth.domain.model.run.Run;
import cz.jalasoft.myhealth.domain.model.run.RunRepository;
import cz.jalasoft.myhealth.domain.model.user.Email;
import cz.jalasoft.myhealth.domain.model.user.User;
import cz.jalasoft.myhealth.domain.model.user.UserId;
import cz.jalasoft.myhealth.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ISO_DATE;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/5/15.
 */
@Component
public class MyHealthApplicationService {

    private UserRepository userRepository;
    private RunRepository runRepository;

    @Autowired
    public MyHealthApplicationService(UserRepository userRepository, RunRepository runRepository) {
        this.userRepository = userRepository;
        this.runRepository = runRepository;
    }

    public User registerUser(String emailAddress, String name, String surname, LocalDate birthday) throws UserAlreadyExistsException {

        User newUser = userRepository.add(emailAddress, name, surname, birthday);

        return newUser;
    }

    public User getUser(String emailAddress) throws NoSuchUserException {

        Email email = new Email(emailAddress);

        Optional<User> maybeUser = userRepository.byEmail(email);

        if (!maybeUser.isPresent()) {
            throw new NoSuchUserException(emailAddress);
        }

        return maybeUser.get();
    }

    public void unregistersUser(String emailAddress) throws NoSuchUserException {
        Email email = new Email(emailAddress);

        Optional<User> maybeUser = userRepository.byEmail(email);

        if (!maybeUser.isPresent()) {
            throw new NoSuchUserException(emailAddress);
        }

        userRepository.remove(maybeUser.get().id());
    }

    public Collection<Run> getRuns(String emailAddress) throws NoSuchUserException {
        Email email = new Email(emailAddress);

        Optional<User> maybeUser = userRepository.byEmail(email);

        if (!maybeUser.isPresent()) {
            throw new NoSuchUserException(emailAddress);
        }

        User user = maybeUser.get();
        Collection<Run> result = runRepository.all(user.id());
        return result;
    }

    public Run insertRun(String emailAddress, String dateInIso, double distanceInKilometers, int durationInMinutes) throws NoSuchUserException {
        Email email = new Email(emailAddress);

        Optional<User> maybeUser = userRepository.byEmail(email);

        if (!maybeUser.isPresent()) {
            throw new NoSuchUserException(emailAddress);
        }

        UserId userId = maybeUser.get().id();
        LocalDate date = parse(dateInIso, ISO_DATE);
        Distance distance = Distance.ofKilometers(distanceInKilometers);
        Duration duration = Duration.ofMinutes(durationInMinutes);

        Run newRun = runRepository.add(userId, date, distance, duration);
        return newRun;
    }
}
