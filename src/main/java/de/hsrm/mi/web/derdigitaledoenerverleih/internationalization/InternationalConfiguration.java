package de.hsrm.mi.web.derdigitaledoenerverleih.internationalization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class InternationalConfiguration implements WebMvcConfigurer{
    
    @Bean
    public LocaleResolver localeResolver(){ //Spracheeinstellung auflösen. Verwendet standardmäßig Browser Sprachpräferenz
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        /*ResourceBundleMessageSource ist die Spring-Implementierung zum Laden von .properties-Dateien für Internationalisierung. */
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("lang/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){ //überwacht z.B. gewünschte Request-Parameter für Sprachumstellung
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("sprache");
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){ //Interceptor muss bei der Interceptor Registry angemeldet werden
        registry.addInterceptor(localeChangeInterceptor());
    }


}
