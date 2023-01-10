package com.korit.library.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private int userId;

    @NotBlank
    @ApiModelProperty(name = "username",value = "사용자이름",example = "abc",required = true)
    private String username;
    @ApiModelProperty(name = "password",value = "비번",example = "1234",required = true)
    @NotBlank
    private String password;
    @NotBlank
    @ApiModelProperty(name = "repassword",value = "비번확인",example = "1234",required = true)
    private String repassword;

    @NotBlank
    @ApiModelProperty(name = "name",value = "이름",example = "김규민",required = true)
    private String name;
    @NotBlank
    @ApiModelProperty(name = "email",value = "이메일",example = "kgm0986@naver.com",required = true)
    @Email
    private String email;

    @ApiModelProperty(hidden = true)
    private List<RoleDtlDto> roleDtlDto;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createDate;
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateDate;
}