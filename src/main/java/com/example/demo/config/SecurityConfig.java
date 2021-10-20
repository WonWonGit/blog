package com.example.demo.config;

import com.example.demo.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;

//
@Configuration //빈등록
@EnableWebSecurity //필터 추가 ==> 스프링 시큐리티가 활성화가 되어있는데 설정을 해당 파일에서 하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소로 접근하면 권한 및 인증을 미치 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    //비밀번호가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 같은 해쉬로 암호화해서 디비에 있는 해쉬랑 비교 가능
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
          .csrf().disable() //테스트시
          .authorizeRequests()
             .antMatchers("/","/auth/**","/js/**","/css/**","/img/**")
             .permitAll()
             .antMatchers("/h2-console/**")
             .permitAll()
             .anyRequest()
             .authenticated()
          .and()
             .csrf() // 추가
                .ignoringAntMatchers("/h2-console/**").disable() //
                .httpBasic()
          .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc") //해당주소로 오는 로그인을 시큐리티가 가로챔
                .defaultSuccessUrl("/"); //정상적으로 요청 완료

    }

    // Security 무시하기
    public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/h2-console/**");
    }

}
