package io.github.mastralexis.jgengine.engine.framework;

import java.util.*;

/**
 * Acts as a Manager for Entities and System
 * Can add Entities and Systems to the Current Scene
 * Uses all Systems each frame
 */
public class Scene {
    private Map<Integer, GameObject> objectMap = new HashMap<>();
    private List<GameObject> objects = new ArrayList<>();
    private List<GameSystem> systems = new ArrayList<>();
    private Map<Family, List<GameObject>> familyCache = new HashMap<>(); // cache that maps a Family to a List of Objects

    public void addGameObject(GameObject gameObject) {
        objects.add(gameObject);
        objectMap.put(gameObject.getId(), gameObject);

        // Check which families this new object belongs to
        for (Map.Entry<Family, List<GameObject>> entry : familyCache.entrySet()) {
            if (entry.getKey().matches(gameObject)) {
                entry.getValue().add(gameObject);
            }
        }
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
        // use iterator to remove safely items from all lists
        Iterator<GameObject> it = objects.iterator();
        while (it.hasNext()) {
            GameObject obj = it.next();
            if (!obj.isActive()) {
                objectMap.remove(obj.getId()); // Remove from global map
                for (List<GameObject> familyList : familyCache.values())
                    familyList.remove(obj);    //Remove from all Family lists
                it.remove();                   //Remove from global list
            }
        }

    }

    // The method Systems will call to get entities of a specific family
    public List<GameObject> getGameObjects(Family family) {
        // If we already have a list for this family, return it
        if (familyCache.containsKey(family)) {
            return familyCache.get(family);
        }

        // If not, create the list and populate it for the first time
        List<GameObject> matchingObjects = new ArrayList<>();
        for (GameObject go : objects) {
            if (family.matches(go)) {
                matchingObjects.add(go);
            }
        }

        familyCache.put(family, matchingObjects);
        return matchingObjects;
    }

    public List<GameObject> getGameObjects() {
        return objects;
    }

    public GameObject getGameObject(int id) {
        return objectMap.get(id);
    }
}
