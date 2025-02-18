package orm.sififorever.framework.core.client;

import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Data
public class OllamaClient {
    private final WebClient webClient;

    public OllamaClient(String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    // 通用响应结构
    @Data
    public static class GenerateResponse {
        private String model;
        private String response;
        private boolean done;
        private List<Integer> context;
        private Long total_duration;
        // 其他字段根据需要添加
    }

    // 流式生成
    public Flux<GenerateResponse> generateStream(GenerateRequest request) {
        return webClient.post()
                .uri("/api/generate")
                .bodyValue(request)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(GenerateResponse.class);
    }

    // 非流式生成
    public Mono<GenerateResponse> generate(GenerateRequest request) {
        request.setStream(false);
        return webClient.post()
                .uri("/api/generate")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(GenerateResponse.class);
    }

    @Data
    public static class GenerateRequest {
        private String model;
        private String prompt;
        private String suffix;
        private List<String> images;
        private String format;
        private Map<String, Object> options;
        private String system;
        private String template;
        private Boolean stream = true;
        private Boolean raw;
        private String keep_alive;
        // 其他字段根据需要添加
    }

    // 聊天相关
    @Data
    public static class ChatMessage {
        private String role; // system/user/assistant/tool
        private String content;
        private List<String> images;
        private List<ToolCall> tool_calls;
    }

    @Data
    public static class ToolCall {
        private String function;
        private Map<String, Object> arguments;
    }

    public Flux<ChatResponse> chatStream(ChatRequest request) {
        return webClient.post()
                .uri("/api/chat")
                .bodyValue(request)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(ChatResponse.class);
    }

    @Data
    public static class ChatRequest {
        private String model;
        private List<ChatMessage> messages;
        private List<Map<String, Object>> tools;
        private String format;
        private Map<String, Object> options;
        private Boolean stream = true;
        private String keep_alive;
    }

    @Data
    public static class ChatResponse {
        private String model;
        private ChatMessage message;
        private boolean done;
        private Long total_duration;
        // 其他字段根据需要添加
    }

    // 模型操作
    public Flux<ModelOperationStatus> createModel(CreateModelRequest request) {
        return webClient.post()
                .uri("/api/create")
                .bodyValue(request)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(ModelOperationStatus.class);
    }

    @Data
    public static class CreateModelRequest {
        private String model;
        private String from;
        private Map<String, String> files;
        private Map<String, String> adapters;
        private String template;
        private String license;
        private String system;
        private Map<String, Object> parameters;
        private List<ChatMessage> messages;
        private Boolean stream;
        private String quantize;
    }

    @Data
    public static class ModelOperationStatus {
        private String status;
        private String digest;
        private Long total;
        private Long completed;
    }

    @Data
    public static class ModelListResponse {
        private List<String> models;
    }

    // 其他API方法类似实现...

    // 异常处理示例
    private <T> Mono<T> handleError(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(body -> Mono.error(new OllamaException(
                        response.statusCode().value(),
                        "Ollama API error: " + body)));
    }

    public static class OllamaException extends RuntimeException {
        private final int statusCode;

        public OllamaException(int statusCode, String message) {
            super(message);
            this.statusCode = statusCode;
        }
    }
}