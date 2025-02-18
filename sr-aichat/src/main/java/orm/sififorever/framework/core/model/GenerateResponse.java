package orm.sififorever.framework.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateResponse {
    private String model;
    private String createdAt;
    private String response;
    private Boolean done;
    private String context;
    private Long totalDuration;
    private Long loadDuration;
    private Integer promptEvalCount;
    private Long promptEvalDuration;
    private Integer evalCount;
    private Long evalDuration;
}
