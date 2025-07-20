package de.hsrm.mi.web.derdigitaledoenerverleih.configuation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //@EnableWebSecurity
public class SecurityConfiguration {

    //  @Autowired 
    // BenutzerServiceImpl benutzerService;

    // @Value("${SECRETKEY}")
    // private String secretKey;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChainApp(HttpSecurity http) throws Exception {

        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/*", "/css/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/doener/liste", true)
                // .permitAll()
            )
            // .logout(logout -> logout
            //     .logoutSuccessUrl("/login")
            //     .permitAll()
            // )
            .build();

        // return http.build();
    }

    // @Bean
    // public NimbusJwtDecoder jwtDecoder() {
    //     byte[] keyBytes = secretKey.getBytes();
    //     SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
    //     return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    // }

    // @Order(1)
    // @Bean
    // public SecurityFilterChain filterChainAPI(HttpSecurity http) throws Exception{
    //     http
    //     // diese Filterkette ist für diese URI-Pfade zuständig
    //     .securityMatchers(s -> s.requestMatchers("/api/**","/stompbroker"))
    //     .authorizeHttpRequests(authz -> authz
    //     // Zugang zu /api/token und (hier) STOMP-Endpunkt offen
    //     .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
    //     .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
    //     .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() //OPTIONS prüft, ob eine Anfrage erlaubt ist(CORD-Vorabfragen vom Browser)
    //     .requestMatchers("/stompbroker").permitAll()
    //     // Zugriff auf sonstige /api-Endpunkte nur authentifiziert
    //     .requestMatchers("/api/**").authenticated()
    //     // Rest blocken
    //     .anyRequest().permitAll())
    //     // JWT-Authentifizierung mit eigenem JWT-Decoder (s.o.)
    //     // .oauth2ResourceServer(o -> o.jwt(j -> j.decoder(jwtDecoder())
    //     // kein CSRF-Check und kein Sessionmanagement für /api-Endpunkte
    //     .csrf(csrf -> csrf.disable())
    //     .sessionManagement((session) ->
    //     session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    //     return http.build();
    // }

     @Bean 
    PasswordEncoder passwordEncoder() {
        // @Bean -> Encoder woanders per @Autowired abrufbar
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
}
