package orm.sififorever.framework.core.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import orm.sififorever.framework.core.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class OllamaClient {
    private final String baseUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OllamaClient(@Value("${ollama.base-url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public GenerateResponse generate(GenerateRequest request) {
        String url = baseUrl + "/api/generate";
        return post(url, request, GenerateResponse.class);
    }

    public ChatResponse chat(ChatRequest request) {
        String url = baseUrl + "/api/chat";
        return post(url, request, ChatResponse.class);
    }

    public EmbeddingResponse createEmbeddings(EmbeddingRequest request) {
        String url = baseUrl + "/api/embeddings";
        return post(url, request, EmbeddingResponse.class);
    }

    public ModelResponse listModels() {
        String url = baseUrl + "/api/tags";
        return get(url, ModelResponse.class);
    }

    public void deleteModel(String modelName) {
        String url = baseUrl + "/api/delete";
        DeleteRequest request = new DeleteRequest(modelName);
        post(url, request, Void.class);
    }

    private <T> T post(String url, Object request, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(request, headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                responseType);

        return response.getBody();
    }

    private <T> T get(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }
}
