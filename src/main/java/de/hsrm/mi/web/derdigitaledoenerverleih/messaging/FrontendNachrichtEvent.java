package de.hsrm.mi.web.derdigitaledoenerverleih.messaging;

public class FrontendNachrichtEvent {

    private Typ typ;
    private long id;
    private Action action;

    public enum Typ {
        DOENER,
        ZUTAT,
        BENUTZER;
    }

    public enum Action {
        CREATE,
        UPDATE,
        DELETE,
        BOOKED,
        RETURNED;
    }

    public FrontendNachrichtEvent(Typ typ, long id, Action create) {
        this.typ = typ;
        this.id = id;
        this.action = create;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action art) {
        this.action = art;
    }

}
