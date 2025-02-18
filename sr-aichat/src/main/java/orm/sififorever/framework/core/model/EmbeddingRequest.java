package orm.sififorever.framework.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmbeddingRequest {
    private String model;
    private Object input; // 可以是String或List<String>
    private Boolean truncate;
    private Map<String, Object> options;
}
