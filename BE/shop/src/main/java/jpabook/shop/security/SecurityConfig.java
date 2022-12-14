package jpabook.shop.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// SecurityFilterChain 을 @Bean 객체로 등록할 때 발생하는 중복 등록하는 에러 해결
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
// 기본적인 보안 설정 활성화
@EnableWebSecurity
public class SecurityConfig {

    // WebSecurityConfigurerAdopter 지원 불가능 - Configuring HttpSecurity
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()    // http request 사용하는 요청에 접근 제한 설정
                .anyRequest().permitAll();  // 나머지 요청들에 대해서 . 모두 항상 허용
        return http.build();
    }
}
