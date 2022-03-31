package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class Game {
    private long id;
    private String date;
    private int season;
    private Team homeTeam;
    private Team visitorTeam;

    public Game(@JsonProperty("id") long id,
                @JsonProperty("date") String date,
                @JsonProperty("season") int season,
                @JsonProperty("home_team") Team homeTeam,
                @JsonProperty("visitor_team") Team visitorTeam){
        this.id = id;
        this.date = date;
        this.season = season;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
    }
}
