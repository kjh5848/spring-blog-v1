package shop.mtcoding.blog._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration// 컴포넌트 스캔, 설정파일에서만 사용하는게 좋다.
public class SecurityConfig {

    //시큐리티가 로그인할때 기본 해시를 알 수 있다.
    //PasswordEncoder 부모객체다.
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer ignore() {
        return w -> w.ignoring().requestMatchers("/static/**", "/h2-console/**");
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

//        http.csrf(c -> c.disable());

//        http.authorizeHttpRequests(a -> {
//            a.requestMatchers("/board/saveForm").authenticated() // 게시글 작성 폼만 인증 필요
//                    .requestMatchers(
//                            // 게시물 상세 페이지 접근 허용
//                            HttpMethod.GET,
//                            "/board/{id}" // 게시물 ID 패턴
//                    ).permitAll()
//                    .requestMatchers("/user/**", "/board/**").authenticated() // 기존 인증 로직 유지
//                    .anyRequest().permitAll();
//        });
//
//        http.authorizeHttpRequests(a -> {
//            a.requestMatchers("board/?").permitAll()
//                    .requestMatchers("/user/**","/board/**").authenticated()
//                    .anyRequest().permitAll();
//        });

        http.authorizeHttpRequests(a -> {
            a.requestMatchers(RegexRequestMatcher.regexMatcher("/board/\\d+")).permitAll()
                    .requestMatchers("/user/**","/board/**").authenticated()
                    .anyRequest().permitAll();
        });

        http.formLogin(f -> {
            f.loginPage("/loginForm").loginProcessingUrl("/login")
                    .defaultSuccessUrl("/").failureUrl("/loginForm");
        });

        return http.build();
    }
}

