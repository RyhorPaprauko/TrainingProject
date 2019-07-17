package by.itacademy.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("by.itacademy.web")
@Import(value = {InternationalizationConfig.class, ThymeleafConfig.class})
public class WebConfig implements WebMvcConfigurer {

    private static final String[] PATH_PATTERNS = {
            "/images/**",
            "/css/**",
            "/js/**"
    };

    private static final String[] CLASSPATH = {
            "classpath:/static/images/",
            "classpath:/static/css/",
            "classpath:/static/js/"
    };

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return new MappingJackson2HttpMessageConverter(mapper);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH_PATTERNS)
                .addResourceLocations(CLASSPATH)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
