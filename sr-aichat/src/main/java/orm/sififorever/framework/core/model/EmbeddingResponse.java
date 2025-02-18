package orm.sififorever.framework.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingResponse {
    private String model;
    private List<List<Double>> embeddings;
    private Long totalDuration;
    private Long loadDuration;
    private Integer promptEvalCount;
}
