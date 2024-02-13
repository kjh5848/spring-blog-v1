package shop.mtcoding.blog._core.config.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shop.mtcoding.blog.user.User;

import java.util.Collection;

@Getter
// 세션에 저장되는 오브젝트
@RequiredArgsConstructor
public class MyLoginUser implements UserDetails {
    // 시큐리티가 알아서 내부에 있는 메소드를 호출해서 user의 객체에 있는 패스워드와 비교해서 세션에 저장해준다.
    // User에 대한 의존성 주입이 필요하다.

    private final User user;

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
