package orm.sififorever.framework.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private String format;
    private Map<String, Object> options;
    private Boolean stream;
    private List<Tool> tools;

    public ChatRequest() {
        // Jackson needs the default constructor
    }

    @Data
    public static class Message {
        private String role;
        private String content;
        private List<String> images;
        private List<ToolCall> toolCalls;
    }

    @Data
    public static class Tool {
        private String type;
        private Function function;
    }

    @Data
    public static class Function {
        private String name;
        private String description;
        private Map<String, Object> parameters;
    }

    @Data
    public static class ToolCall {
        private Function function;
    }
}
