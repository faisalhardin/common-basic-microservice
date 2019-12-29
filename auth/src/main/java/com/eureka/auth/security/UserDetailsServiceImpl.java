package com.eureka.auth.security;

import java.util.List;

import com.eureka.auth.response.Response;

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


@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {


    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        try {
            HttpHeaders headers = new HttpHeaders();

            JSONObject user = new JSONObject();
            String url = "http://localhost:8089/user/email";

            headers.setContentType(MediaType.APPLICATION_JSON);
            user.put("email",email);

            HttpEntity<String> request = new HttpEntity<String>(user.toString(), headers);
            ResponseEntity<Response> userAsResponse = rest().postForEntity(url, request, Response.class);

            System.out.println("\n\n" + userAsResponse.getBody().toString() + "\n\n");

            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(userAsResponse.getBody().getData().getRole());

            return new User(userAsResponse.getBody().getData().getName(), userAsResponse.getBody().getData().getPassword(), grantedAuthorities);
        } catch (Exception e) {
            System.out.println(e);
            throw new UsernameNotFoundException("Username: username not found");
        }


        // If user not found. Throw this exception.

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
