package com.dglee.mini_prj.user;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<SiteUser, Long> { // SiteUser의 Pk는 Long이다

}
