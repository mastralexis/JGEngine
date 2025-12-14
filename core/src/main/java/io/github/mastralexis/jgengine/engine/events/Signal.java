package io.github.mastralexis.jgengine.engine.events;

import java.util.ArrayList;
import java.util.List;

/**
 * Broadcaster that holds a list of listeners and dispatches events.
 */
public class Signal<T> {
    private List<Listener<T>> listeners = new ArrayList<>();

    public void addListener(Listener<T> listener) { listeners.add(listener); }
    public void removeListener(Listener<T> listener) { listeners.remove(listener); }

    public void dispatch(T event) {
        for (Listener<T> listener : listeners) {
            listener.receive(event);
        }
    }
}
