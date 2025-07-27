package de.hsrm.mi.web.derdigitaledoenerverleih.errorhandling;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class GlobalErrorController implements ErrorController {
    public static final String ERRORPATH = "/error";
    
    // public String getErrorPath() { return ERRORPATH; };

    @RequestMapping(ERRORPATH)
    public String handleError(){
        return "error/404.html";
    }

    @ExceptionHandler(SeiteNichtVorhandenException.class)
    public String seiteNichtVorhanden(SeiteNichtVorhandenException ex, Model m){
        m.addAttribute("info", ex.getMessage());
        return "error/404.html";
    }
}
