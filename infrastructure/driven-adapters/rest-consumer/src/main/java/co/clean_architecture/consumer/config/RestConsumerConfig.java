package co.clean_architecture.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConsumerConfig {

    @Bean
    public RestClient.Builder restClientBuilder(AuthHeaderInterceptor interceptor) {
        return RestClient.builder()
                .requestInterceptor(interceptor);
    }

    @Bean
    public RestClient userRestClient(
            RestClient.Builder builder,
            @Value("${adapter.restconsumer.user-url}") String url
    ) {
        return builder
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public RestClient notificationRestClient(
            RestClient.Builder builder,
            @Value("${adapter.restconsumer.notification-url}") String url
    ) {
        return builder
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
