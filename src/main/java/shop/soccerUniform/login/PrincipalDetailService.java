package shop.soccerUniform.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.soccerUniform.entity.User;
import shop.soccerUniform.repository.user.UserRepository;

@Slf4j
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public PrincipalDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username={}", username);
        User user = userRepository.findByLoginId(username);
        log.info("security user={}", user);
        log.info("security user.password={}", user.getPassword());
        if(user != null) {
            return new PrincipalDetails(user);
        }
        return null;
    }
}
