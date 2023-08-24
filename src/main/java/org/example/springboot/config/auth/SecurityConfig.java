package org.example.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
//Spring Security 설정 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CustomOAuth2UserService customOAuth2UserService;

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http
        .csrf().disable()
        .headers().frameOptions().disable()    //h2-console 화면 사용을 위해 disable
      .and()
        .authorizeRequests()    //URL별 권환 관리 설정 -> antMatchers 옵션 선행 조건
        .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
        .anyRequest().authenticated()   //authenicated 된 사용자 -> 로그인 한 사용자
      .and()
        .logout()
          .logoutSuccessUrl("/")
      .and()
        .oauth2Login()
        .userInfoEndpoint()   //로그인 이후 사용자 정보를 가져올 때 설정
        .userService(customOAuth2UserService);    //소셜 로그인 성공 시 후속 조치를 진행할 인터페이스 구현체

  }

}
