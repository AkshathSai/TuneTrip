package com.tunetrip.config;

import com.tunetrip.service.YouTube;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.springframework.web.reactive.function.client.support.WebClientAdapter.forClient;


@Configuration
public class YouTubeConfig {

    private static final String YOUTUBE_V3_API = "https://www.googleapis.com/youtube/v3";

    @Bean
    WebClient webClient(OAuth2AuthorizedClientManager
                                authorizedClientManager) {

        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = //
                new ServletOAuth2AuthorizedClientExchangeFilterFunction( //
                        authorizedClientManager);
        oauth2.setDefaultClientRegistrationId("google");

        return WebClient.builder() //
                .baseUrl(YOUTUBE_V3_API) //
                .apply(oauth2.oauth2Configuration()) //
                .build();
    }

    @Bean
    YouTube client(WebClient oauth2WebClient) {
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory.builder(
                                 //new WebClientAdapter(oauth2WebClient))
                                forClient(oauth2WebClient))
                        .build();

        return factory.createClient(YouTube.class);
    }

}
