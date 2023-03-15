package shop.soccerUniform.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.User;
import shop.soccerUniform.repository.user.UserRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public PrincipalDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        log.info("loginId={}", loginId);
        User user = userRepository.findByLoginId(loginId);
        if(user != null) {
            return new PrincipalDetail(user);
        }
        return null;
    }
}
