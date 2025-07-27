package de.hsrm.mi.web.derdigitaledoenerverleih.errorhandling;

public class SeiteNichtVorhandenException extends RuntimeException{ 
    // ! RuntimeException damit ich sie im controller nciht fanden muss, sondern errorhandler aufgerufen wird.
    // ! Java unterscheided zwischen checked und unckecked Exceptions
    // ! checked brauchen try/catch -> vorhersehbare Fehler (Exception, IOException)
    // ! unchecked nicht -> unvorhersehbare Fehler (RuntimeException, NullpointerException)

    public SeiteNichtVorhandenException(String message){
        super(message);
    }
    
}
