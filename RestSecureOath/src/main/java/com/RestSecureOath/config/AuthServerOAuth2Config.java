package com.RestSecureOath.config;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.RestSecureOath.repo.UserRepositoryX;

@Configuration
@EnableAuthorizationServer
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {
	
		/*curl -F grant_type=password -F username=john -F password=123 -X POST http://localhost:8080/oauth/token -u clientIdPassword:secret*/  
		/*curl -F grant_type=authorization_code -F username=john -F password=123 -X POST http://localhost:8080/oauth/token -u clientIdPassword:secret*/
	/*@Autowired
   // @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;*/
    
	@Autowired
	AuthenticationManagerBuilder authenticationManager;


    
    @Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyPair keyPair = new KeyStoreKeyFactory(
				new ClassPathResource("keystore.jks"), "foobar".toCharArray())
				.getKeyPair("test");
		converter.setKeyPair(keyPair);
		return converter;
	}
 
    @Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) 
      throws Exception {
        oauthServer
          .tokenKeyAccess("permitAll()")
          .checkTokenAccess("isAuthenticated()");
    }
 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) 
      throws Exception {
        clients.inMemory()
        //.jdbc(dataSource)
               .withClient("sampleClientId")
               .authorizedGrantTypes("implicit")
               .scopes("read")
               .autoApprove(true)
               .and()
               .withClient("clientIdPassword").resourceIds("RestSecureOath")
               .secret("secret")
               .authorizedGrantTypes(
                 "password","authorization_code", "refresh_token")
               .scopes("read");
    }
 
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) 
      throws Exception {
        endpoints
          .tokenStore(tokenStore())
          //.userDetailsService(userDetailsService())
          .authenticationManager(new AuthenticationManager() {
  			@Override
  			public Authentication authenticate(Authentication authentication)
  					throws AuthenticationException {
  				return authenticationManager.getOrBuild().authenticate(authentication);
  			}
  		})
          .accessTokenConverter(jwtAccessTokenConverter());
    }
 
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }
}
