package orm.sififorever.framework.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatResponse {
    private String model;
    private String createdAt;
    private Message message;
    private Boolean done;
    private String doneReason;
    private Long totalDuration;
    private Long loadDuration;
    private Integer promptEvalCount;
    private Long promptEvalDuration;
    private Integer evalCount;
    private Long evalDuration;

    @Data
    public static class Message {
        private String role;
        private String content;
        private ChatRequest.ToolCall[] toolCalls;
    }
}
