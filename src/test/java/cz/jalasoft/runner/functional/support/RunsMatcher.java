package cz.jalasoft.runner.functional.support;


import cz.jalasoft.runner.domain.model.run.Run;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Collection;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/19/15.
 */
public class RunsMatcher extends TypeSafeMatcher<Collection<Run>> {

    public static RunsMatcher has(RunExpectation expectation) {
        return new RunsMatcher(expectation);
    }

    //-------------------------------------------------
    //INSTANCE SCOPE
    //-------------------------------------------------

    private final RunExpectation expectation;

    private RunsMatcher(RunExpectation expectation) {
        this.expectation = expectation;
    }

    @Override
    protected boolean matchesSafely(Collection<Run> runs) {
        for(Run run : runs) {
            if (expectation.matches(run)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
