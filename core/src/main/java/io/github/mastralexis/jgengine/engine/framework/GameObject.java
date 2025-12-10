package io.github.mastralexis.jgengine.engine.framework;

import java.util.HashMap;
import java.util.Map;

public class GameObject {

    private static int currentId = 0;  // keep track of ids to so when an object is created to have that id

    private final int id;               // GameObject instance actual id
    private final String name;
    private boolean active;
    private Map<Class<? extends GameComponent>, GameComponent> components = new HashMap<>();  // Key = Position.class, Value = new Position(10, 10)

    public GameObject(String name) {
        this.id = currentId++;
        this.name = name;
        this.active = true;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
    public void destroy() { this.active = false; }

    public <T extends GameComponent> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }

    public GameObject addComponent(GameComponent component) {
        components.put(component.getClass(), component);
        return this;    // return self for method chaining
    }

    public boolean hasComponent(Class<? extends GameComponent> type) {
        return components.containsKey(type);
    }
}
