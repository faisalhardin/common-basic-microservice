package com.eureka.auth.security;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;


@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {


    @Autowired
    private BCryptPasswordEncoder encoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HttpHeaders headers = new HttpHeaders();

        JSONObject user = new JSONObject();
        String url = "http://localhost:8089/user/email";

        headers.setContentType(MediaType.APPLICATION_JSON);
        user.put("email",username);

        HttpEntity<String> request = new HttpEntity<String>(user.toString(), headers);
        ResponseEntity<String> userAsResponse = rest().postForEntity(url, request, String.class);

        System.out.println("\n\n" + userAsResponse.toString() + "\n\n");
//
//        final List<AppUser> users = Arrays.asList(
//                new AppUser(1, "omar", encoder.encode("12345"), "USER"),
//                new AppUser(2, "admin", encoder.encode("12345"), "ADMIN")
//        );


//        for(AppUser appUser: users) {
//            if(appUser.getUsername().equals(username.trim())) {
//
//                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
//                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + "user");
//
//                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
//                // And used by auth manager to verify and check user authentication.
//                return new User(appUser.getUsername(), appUser.getPassword(), grantedAuthorities);
//            }
//        }
        if (true)
        return new User("faisalhardin23@gmail.com", encoder.encode("123456"), grantedAuthorities);

        // If user not found. Throw this exception.
        throw new UsernameNotFoundException("Username: username not found");
    }

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

    // A (temporary) class represent the user saved in the database.
    private static class AppUser {
        private Integer id;
        private String username, password;
        private String role;

        public AppUser(Integer id, String username, String password, String role) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.role = role;
        }

        //

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
