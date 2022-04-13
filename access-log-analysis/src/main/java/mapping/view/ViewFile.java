package mapping.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ViewFile implements Comparable<ViewFile> {
    private final LocalDateTime beginTime;
    private final LocalDateTime finishTime;
    private final Float percent;

    @Override
    public int compareTo(ViewFile o) {
        return o.getBeginTime().compareTo(this.getBeginTime());
    }
}
