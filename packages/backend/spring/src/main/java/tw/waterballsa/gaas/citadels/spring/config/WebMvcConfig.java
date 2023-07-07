package tw.waterballsa.gaas.citadels.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tw.waterballsa.gaas.citadels.spring.filter.CorsFilter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
}
