package cz.jalasoft.runner.infrastructure.endpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/21/15.
 */
public final class RunResource {

    private double distanceKilometers;
    private int durationInMinutes;
    private String date;

    @JsonCreator
    public RunResource(
            @JsonProperty(value = "distance_in_km", required = true)
            double distanceKilometers,
            @JsonProperty(value = "duration_in_minutes", required = true)
            int durationInMinutes,
            @JsonProperty(value = "date_in_iso", required = true)
            String date) {
        this.distanceKilometers = distanceKilometers;
        this.durationInMinutes = durationInMinutes;
        this.date = date;
    }

    @JsonProperty("distance_in_km")
    public double getDistanceKilometers() {
        return distanceKilometers;
    }

    @JsonProperty("duration_in_minutes")
    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    @JsonProperty("date_in_iso")
    public String getIsoDate() {
        return date;
    }
}
