package com.dglee.mini_prj.user;

import com.dglee.mini_prj.question.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * PackageName : com.dglee.mini_prj.user
 * FileName : SiteUserDTO
 * Author : dglee
 * Create : 2023/02/10 8:25 PM
 * Description :
 **/

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteUserDTO {
    private Long id;
    private String userName;
    private String password;
    private String email;

    public SiteUser toEntity(){
        return new SiteUser(this.userName,this.password,this.email);
    }
}
