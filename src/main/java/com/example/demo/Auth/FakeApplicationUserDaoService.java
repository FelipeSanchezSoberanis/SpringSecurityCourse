package com.example.demo.Auth;

import static com.example.demo.Security.ApplicationUserRole.STUDENT;
import static com.example.demo.Security.ApplicationUserRole.ADMIN;
import static com.example.demo.Security.ApplicationUserRole.ADMINTRAINEE;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
            .stream()
            .filter(applicationUser -> username.equals(applicationUser.getUsername()))
            .findFirst();
	}

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(
                STUDENT.getGrantedAuthorities(),
                passwordEncoder.encode("password"),
                "annasmith",
                true,
                true,
                true,
                true
            ),
            new ApplicationUser(
                ADMIN.getGrantedAuthorities(),
                passwordEncoder.encode("password"),
                "linda",
                true,
                true,
                true,
                true
            ),
            new ApplicationUser(
                ADMINTRAINEE.getGrantedAuthorities(),
                passwordEncoder.encode("password"),
                "tom",
                true,
                true,
                true,
                true
            )
        );

        return applicationUsers;
    }

}

