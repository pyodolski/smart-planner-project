package com.project.planner.dto;

import com.project.planner.domain.Role;
import com.project.planner.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@NoArgsConstructor
@Data
public class UserDto {
    // @NotBlank : null, "", " " 허용 X
    @NotBlank(message = "이메일을 입력하세요.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Length(min = 6, max = 16, message = "비밀번호는 6자 이상 16자 이하로 입력하세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    @Length(min = 6, max = 16, message = "비밀번호는 6자 이상, 16자 이하로 입력해주세요.")
    private String password_confirm;

    @NotBlank(message = "이름을 입력하세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "이름은 2개 이상 10개 이하로 입력하세요.(특수문자 제외)")
    private String name;

    @Pattern(regexp = "^01([016789])-?([0-9]{3,4})-?([0-9]{4})$", message = "휴대폰 번호 양식이 아닙니다.(xxx-xxxx-xxxx.")
    // 정규식 사용
    private String phone;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .role(Role.USER)
                .build();
    }

}
