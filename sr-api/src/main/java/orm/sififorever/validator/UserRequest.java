package orm.sififorever.validator;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @NotBlank(message = "姓名不能为空")
    private String name;

    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 6, message = "密码长度至少6位")
    private String password;
}
