package de.hsrm.mi.web.derdigitaledoenerverleih.ui.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LosungValidation implements ConstraintValidator <Losung, String>{

    protected String key;
    protected String key2;


    public void initialize(Losung geeigLosung){ //muss initialize heißen
        this.key = geeigLosung.value();
        this.key2 = geeigLosung.value2();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) { //ConstraintValidator führt Validierung tatsächlich aus
        if(value.contains(key) || value.toLowerCase().contains(key2) || value == ""){
            return true;
        }
        return false;
    }
    
}
