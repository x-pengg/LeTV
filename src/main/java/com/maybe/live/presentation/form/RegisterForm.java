package com.maybe.live.presentation.form;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Tate on 2016/5/8.
 */

@Data
public class RegisterForm {

    @NotEmpty(message = "请输入昵称")
    @Size(min = 2, max = 25, message = "昵称长度为2-25个字符")
    private String name;
    @NotEmpty(message = "请输入邮箱")
    @Email(message = "邮箱格式错误")
    private String email;
    @NotEmpty(message = "请输入密码")
    @Size(min = 6, max = 100, message = "密码长度为6-100个字符")
    private String password;

}
