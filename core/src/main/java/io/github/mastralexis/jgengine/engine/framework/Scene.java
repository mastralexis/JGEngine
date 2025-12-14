package io.github.mastralexis.jgengine.engine.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Acts as a Manager for Entities and System
 * Can add Entities and Systems to the Current Scene
 * Uses all Systems each frame
 */
public class Scene {
    private Map<Integer, GameObject> objectMap = new HashMap<>();
    private List<GameObject> objects = new ArrayList<>();
    private List<GameSystem> systems = new ArrayList<>();

    public void addGameObject(GameObject gameObject) {
        objects.add(gameObject);
        objectMap.put(gameObject.getId(), gameObject);
    }

    public void addGameSystem(GameSystem gameSystem) {
        systems.add(gameSystem);        // add system
        gameSystem.attach(this); // make the system access the scene
    }

    public void update(float delta) {

        for(GameSystem system : systems) {
            system.update(delta);
        }

        // remove objects that are deactivated
        // iterate backwards to safely remove items while looping
        for (int i = objects.size() - 1; i >= 0; i--) {
            GameObject obj = objects.get(i);
            if (!obj.isActive()) {
                objects.remove(i);             // Remove from List
                objectMap.remove(obj.getId()); // Remove from Map
            }
        }
    }

    public List<GameObject> getGameObjects() {
        return objects;
    }

    public GameObject getGameObject(int id) {
        return objectMap.get(id);
    }
}
