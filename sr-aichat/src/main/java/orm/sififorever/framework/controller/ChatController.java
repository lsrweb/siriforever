package orm.sififorever.framework.controller;

import com.siriforever.common.annotaion.ApiRestController;
import com.siriforever.common.core.AjaxResult;
import orm.sififorever.framework.core.client.OllamaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ApiRestController
public class ChatController {

    @Autowired
    private OllamaClient ollamaClient;

    @PostMapping("/chat")
    @ResponseBody
    public AjaxResult chat(@RequestBody String prompt) {
        // 初始化客户端
        OllamaClient client = new OllamaClient("http://localhost:11434");

        // 流式生成示例
        OllamaClient.GenerateRequest generateRequest = new OllamaClient.GenerateRequest();
        generateRequest.setModel("llama3.2");
        generateRequest.setPrompt("Why is the sky blue?");

        client.generateStream(generateRequest)
                .subscribe(response -> {
                    if (response.isDone()) {
                        System.out.println("Final response: " + response.getResponse());
                    } else {
                        System.out.println("Partial response: " + response.getResponse());
                    }
                });

        return AjaxResult.success();
    }
}
