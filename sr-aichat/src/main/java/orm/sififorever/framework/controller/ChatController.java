package orm.sififorever.framework.controller;

import com.siriforever.common.annotaion.ApiRestController;
import com.siriforever.common.core.AjaxResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@ApiRestController
public class ChatController {

    @PostMapping("/chat")
    public AjaxResult<Object> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String response = "收到你的消息：" + message;
        return AjaxResult.success();
    }
}
