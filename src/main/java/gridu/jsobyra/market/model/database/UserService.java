package gridu.jsobyra.market.model.database;

import gridu.jsobyra.market.model.User;
import gridu.jsobyra.market.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }

    public boolean checkIfUserExists(String username) {
        if(userRepository.findByUsername(username) == null) return false;
        else return true;
    }

    public User saveUser(String username, String password) {
        User user = new User(username, BCrypt.hashpw(password, BCrypt.gensalt()));
        userRepository.saveAndFlush(user);
        return user;
    }

    public UserSession getCurrentSession() {
        return new UserSession(RequestContextHolder.currentRequestAttributes().getSessionId());
    }
}