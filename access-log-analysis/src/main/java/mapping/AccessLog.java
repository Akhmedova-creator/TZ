package mapping;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Маппим на этот класс содержимое файла
 */
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AccessLog implements Serializable {
    private final LocalDateTime time;
    private final String status;
    private final Float ms;
}
