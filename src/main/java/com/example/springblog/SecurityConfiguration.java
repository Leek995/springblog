package com.example.springblog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/posts") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/", "/posts", "/signup") // anyone can see the home and the ads pages
                .permitAll()
                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/posts/create", // only authenticated users can create ads
                        "/posts/{id}/edit" // only authenticated users can edit ads
                )
                .authenticated()
        ;
        return http.build();
    }

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = SpringblogApplication.class)
    @AutoConfigureMockMvc
    public class AdsIntegrationTests {

        private User testUser;
        private HttpSession httpSession;

        @Autowired
        private MockMvc mvc;

        @Autowired
        UserRepository userDao;

        @Autowired
        AdRepository adsDao;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Before
        public void setup() throws Exception {

            testUser = userDao.findByUsername("testUser");

            // Creates the test user if not exists
            if(testUser == null){
                User newUser = new User();
                newUser.setUsername("testUser");
                newUser.setPassword(passwordEncoder.encode("pass"));
                newUser.setEmail("testUser@codeup.com");
                testUser = userDao.save(newUser);
            }

            // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
            httpSession = this.mvc.perform(post("/login").with(csrf())
                            .param("username", "testUser")
                            .param("password", "pass"))
                    .andExpect(status().is(HttpStatus.FOUND.value()))
                    .andExpect(redirectedUrl("/ads"))
                    .andReturn()
                    .getRequest()
                    .getSession();
        }
    }

}