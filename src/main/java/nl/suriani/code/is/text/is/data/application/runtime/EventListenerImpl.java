package nl.suriani.code.is.text.is.data.application.runtime;

import nl.suriani.code.is.text.is.data.application.event.Event;

public class EventListenerImpl implements EventListener<Event> {
    public void on(Event event) {
        // Handle the event
        System.out.println("Event received: " + event);
    }
}
