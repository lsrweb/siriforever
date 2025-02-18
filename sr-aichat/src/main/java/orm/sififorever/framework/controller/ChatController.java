package orm.sififorever.framework.controller;

import com.siriforever.common.annotaion.ApiRestController;
import com.siriforever.common.core.AjaxResult;
import orm.sififorever.framework.core.client.OllamaClient;
import orm.sififorever.framework.core.model.ChatRequest;
import orm.sififorever.framework.core.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatController {

    @Autowired
    private OllamaClient ollamaClient;

    @GetMapping("/chatTemplate")
    public String chatTemplate(Model model) {
        model.addAttribute("message", "Hello, I am a chat bot!");
        return "chat"; // 返回 chat.html 模板
    }

    @PostMapping("/chat")
    public AjaxResult<ChatResponse> sendMessage(@RequestBody ChatRequest request) {
        ChatResponse response = ollamaClient.chat(request);
        return AjaxResult.success(response);
    }
}
