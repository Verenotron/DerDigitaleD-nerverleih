package de.hsrm.mi.web.derdigitaledoenerverleih.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class FrontendNachrichtenService {

    @Autowired 
    private SimpMessagingTemplate messaging;

    @EventListener //brauche ich, damit das ApplicationEventPublisher.publishEvent abgefangen wird
    public void sendEvent(FrontendNachrichtEvent ev){
        messaging.convertAndSend("/topic/doener", ev);
    }
    
}
