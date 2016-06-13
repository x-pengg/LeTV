package com.maybe.live.presentation.form;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Tate on 2016/5/7 0007.
 */

@Data
public class LoginForm {

    @NotEmpty(message = "请输入邮箱")
    @Email(message = "邮箱格式错误")
    private String email;
    @NotEmpty(message = "请输入密码")
    @Size(max = 100, message = "密码最大长度为100个字符")
    private String password;

}
