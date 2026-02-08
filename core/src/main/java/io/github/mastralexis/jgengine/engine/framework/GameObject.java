package io.github.mastralexis.jgengine.engine.framework;

import com.badlogic.gdx.utils.Bits;

import java.util.HashMap;
import java.util.Map;

public class GameObject {
    private static int currentId = 0;  // keep track of ids to so when an object is created to have that id
    private final int id;               // GameObject instance actual id
    private final String name;
    private boolean active;
    private GameComponent[] components; // the components of the entity
    private Bits componentBits;         // represents what type of components this entity has

    public GameObject(String name) {
        this.id = currentId++;
        this.name = name;
        this.active = true;
        this.components = new GameComponent[32];
        this.componentBits = new Bits();
    }

    public int getId()        { return id; }
    public String getName()   { return name; }
    public boolean isActive() { return active; }
    public void destroy()     { this.active = false; }

    public <T extends GameComponent> T getComponent(Class<T> componentClassType) {
        int index = ComponentType.getIndexFor(componentClassType);
        if (index >= components.length) return null;
        return componentClassType.cast(components[index]);
    }

    public GameObject addComponent(GameComponent component) {
        int index = ComponentType.getIndexFor(component.getClass());
        // auto resize if it exceeds the array size
        if (index >= components.length) {
            GameComponent[] newComponents = new GameComponent[index + 16];
            System.arraycopy(components, 0, newComponents, 0, components.length);
            components = newComponents;
        }
        components[index] = component;
        componentBits.set(index); // mark this component type as present
        return this;
    }

    public Bits getComponentBits() {
        return componentBits;
    }
}
