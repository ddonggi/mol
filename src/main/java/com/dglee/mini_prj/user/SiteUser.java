package com.dglee.mini_prj.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * PackageName : com.dglee.mini_prj.user
 * FileName : SiteUser
 * Author : dglee
 * Create : 2023/02/10 7:47 PM
 * Description :
 **/

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SiteUser {

    /*
    * SiteUser로 하는 이유는 User 클래스가 이미 스프링 시큐리티에 있기 때문에, 혼선을 막기 위함
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) //유일한 값 저장
    private String userName;

    private String password;

    @Column(unique = true)
    @Email
    private String email;

    public SiteUser(String userName, String password, String email) {
        this.userName=userName;
        this.password=password;
        this.email=email;
    }
}
