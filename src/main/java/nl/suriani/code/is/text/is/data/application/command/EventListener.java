package nl.suriani.code.is.text.is.data.application.command;

public interface EventListener<E> {
    void on(E event);
}
