package nl.suriani.code.is.text.is.data.event;

import nl.suriani.code.is.text.is.data.application.command.EventListener;

public class EventListenerImpl implements EventListener<Event> {
    public void on(Event event) {
        // Handle the event
        System.out.println("Event received: " + event);
    }
}
