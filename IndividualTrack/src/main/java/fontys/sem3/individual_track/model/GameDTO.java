package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {
    private long id;
    private String date;
    private int season;
    private TeamDTO homeTeam;
    private TeamDTO visitorTeam;

    public GameDTO(@JsonProperty("id") long id,
                   @JsonProperty("date") String date,
                   @JsonProperty("season") int season,
                   @JsonProperty("home_team") TeamDTO homeTeam,
                   @JsonProperty("visitor_team") TeamDTO visitorTeam){
        this.id = id;
        this.date = date;
        this.season = season;
        this.homeTeam = homeTeam;
        this.visitorTeam = visitorTeam;
    }
}
