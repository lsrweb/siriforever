package orm.sififorever.framework.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModelResponse {
    private List<Model> models;

    @Data
    public static class Model {
        private String name;
        private String modifiedAt;
        private Long size;
        private String digest;
        private Details details;
    }

    @Data
    public static class Details {
        private String format;
        private String family;
        private List<String> families;
        private String parameterSize;
        private String quantizationLevel;
    }
}
