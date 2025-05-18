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
    //Sprachenwechsel läuft komplett über den LocleResolver(Kein Locale im Controller nötig)
    //Locale Objekt wird hier eingestellt. Über das Session-Objekt im Controller kann ich auch manuell auf dieses Locale zugreifen
    
    @Bean
    public LocaleResolver localeResolver(){ //Spracheeinstellung auflösen. Verwendet standardmäßig Browser Sprachpräferenz
        SessionLocaleResolver resolver = new SessionLocaleResolver(); 
        //SessionLocaleResolver Speichert das Locale in der HTTP-Session, sodass es auch bei späteren Anfragen erhalten bleibt.
        return resolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        /*ResourceBundleMessageSource ist die Spring-Implementierung zum Laden von .properties-Dateien für Internationalisierung. */
        //erkennt Sprachenwechsel und fordert LocaleResolver auf, die Sprache zu setzen
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
        //Beans sind passive Objekte, daher brauchen sie ein anderes Objekt, damit sie aufgerufen werden.
        registry.addInterceptor(localeChangeInterceptor());
    }


}
