package by.itacademy.web.initializer;

import by.itacademy.service.config.ServiceConfig;
import by.itacademy.web.config.InternationalizationConfig;
import by.itacademy.web.config.SecurityConfig;
import by.itacademy.web.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String SERVLET_MAPPING = "/";

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ServiceConfig.class, SecurityConfig.class,
                InternationalizationConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{SERVLET_MAPPING};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }
}
