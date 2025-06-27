package nl.suriani.code.is.text.is.data.application.runtime;

public interface EventListener<E> {
    void on(E event);
}
