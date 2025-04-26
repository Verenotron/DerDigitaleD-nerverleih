package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class BenutzerController {

    @GetMapping("/benutzer/{loginName}")
    public String getBenutzer(@PathVariable("loginName") String name,
                                @ModelAttribute("formular") BenutzerFormular benutzerFormular,
                                Model model) {
        
        model.addAttribute("name", name);
        return "benutzer/bearbeiten.html";
    }

    @PostMapping("/benutzer/{loginName}")
    public String postMethodName(@PathVariable("loginName") String name,
                                    @ModelAttribute("formular") BenutzerFormular benutzerFormular,
                                    Model model
                                    ) {

        model.addAttribute("name", benutzerFormular.getName());
        model.addAttribute("fomrular", benutzerFormular);
        
        return "benutzer/bearbeiten.html";
    }
    
    
}
