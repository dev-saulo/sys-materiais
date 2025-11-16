package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir chamadas da porta do IntelliJ (63342) para facilitar testes quando a página for aberta dali
        registry.addMapping("/usuarios/**")
                .allowedOrigins("http://localhost:9090", "http://localhost:63342")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:9090", "http://localhost:63342")
                .allowedMethods("POST")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
