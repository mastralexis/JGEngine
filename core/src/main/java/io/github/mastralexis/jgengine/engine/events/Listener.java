package io.github.mastralexis.jgengine.engine.events;

/**
 * The interface that observers must implement
 */
public interface Listener<T> {
    void receive(T event);
}
