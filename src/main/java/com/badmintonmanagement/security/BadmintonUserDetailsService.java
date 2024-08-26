package com.badmintonmanagement.security;

import com.badmintonmanagement.entity.User;
import com.badmintonmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BadmintonUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(username);
        if (user != null) {
            return new BadmintonUserDetails((user));
        }
        throw new UsernameNotFoundException("Không tìm thấy người dùng: " + username);
    }
}
