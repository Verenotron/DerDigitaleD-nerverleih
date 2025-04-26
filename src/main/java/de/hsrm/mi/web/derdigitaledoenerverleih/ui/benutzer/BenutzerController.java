package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class BenutzerController {

    @GetMapping("/benutzer/{n}")
    public String getBenutzer(@PathVariable("n") String name,
                                Model model) {
        
        model.addAttribute("name", name);
        return "benutzer/bearbeiten.html";
    }
    
}
