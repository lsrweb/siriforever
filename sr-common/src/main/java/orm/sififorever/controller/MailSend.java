package orm.sififorever.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "邮箱接口", description = "MailSend 邮箱")
@Validated
@Controller
@ResponseBody
public class MailSend {

    @Resource
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @GetMapping("/sendMail")
    public void sendMail() {

        SimpleMailMessage message = new SimpleMailMessage();
        // 标题
        message.setSubject("标题");
        // 内容,设置一个富文本内容
        message.setText(from + "发送邮件给你");
        // 发送给谁
        message.setTo("siriforever.ltd@gmail.com");
        // 发送者
        message.setFrom(from);
        mailSender.send(message);
    }

    // testValidate
    @GetMapping("/testValidate")
    public String testValidate(@Length(min = 3) @RequestParam("name") String name) {
        System.out.println("你好");
        return name;
    }
}
