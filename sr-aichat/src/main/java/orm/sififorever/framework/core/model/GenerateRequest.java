package orm.sififorever.framework.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenerateRequest {
    private String model;
    private String prompt;
    private String system;
    private String template;
    private Boolean stream;
    private Boolean raw;
    private String format;
    private Map<String, Object> options;
    private String context;
}
