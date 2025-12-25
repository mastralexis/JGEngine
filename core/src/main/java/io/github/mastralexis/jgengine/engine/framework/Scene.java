package io.github.mastralexis.jgengine.engine.framework;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

import java.util.*;

/**
 * Acts as a Manager for Entities and System
 * Can add Entities and Systems to the Current Scene
 * Uses all Systems each frame
 */
public class Scene {
    private IntMap<GameObject> objectMap = new IntMap<>();
    private Array<GameObject> objects = new Array<>();
    private List<GameSystem> systems = new ArrayList<>();
    private Map<Family, Array<GameObject>> familyCache = new HashMap<>(); // cache that maps a Family to a List of Objects

    public void addGameObject(GameObject gameObject) {
        objects.add(gameObject);
        objectMap.put(gameObject.getId(), gameObject);

        // Check which families this new object belongs to
        // Notify scene to check families (from our previous fix)
        // Ensure you added the logic to check matches here or in onGameObjectChanged
        updateObjectFamilies(gameObject);
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
        // We iterate BACKWARDS so we can remove items without breaking the index order
        for (int i = objects.size - 1; i >= 0; i--) {
            GameObject obj = objects.get(i);

            if (!obj.isActive()) {
                // 1. Remove from ID map
                objectMap.remove(obj.getId());

                // 2. Remove from Family Lists efficiently
                removeFromFamilies(obj);

                // 3. Remove from main list using SWAP
                // removeValue(obj, true) finds the object and moves the last item
                // into its spot to avoid shifting the whole array. O(1) performance.
                objects.removeIndex(i);
                // Note: objects.removeSwap(i) would be even faster if order truly doesn't matter,
                // but iterating backwards + removeIndex is safe and standard.
            }
        }

    }

    // The method Systems will call to get entities of a specific family
    public Array<GameObject> getGameObjects(Family family) {
        // If we already have a list for this family, return it
        if (familyCache.containsKey(family)) {
            return familyCache.get(family);
        }

        // If not, create the list and populate it for the first time
        Array<GameObject> matchingObjects = new Array<>();
        for (GameObject go : objects) {
            if (family.matches(go)) {
                matchingObjects.add(go);
            }
        }

        familyCache.put(family, matchingObjects);
        return matchingObjects;
    }

    public Array<GameObject> getGameObjects() {
        return objects;
    }

    public GameObject getGameObject(int id) {
        return objectMap.get(id);
    }

    private void removeFromFamilies(GameObject obj) {
        for (Array<GameObject> familyList : familyCache.values()) {
            // removeValue(object, identity=true) checks by reference (==)
            // It returns true if removed.
            // true passed as second arg means "use .equals()?", wait:
            // LibGDX Array.removeValue(T value, boolean identity)
            // identity = true means check with == (faster)
            familyList.removeValue(obj, true);
        }
    }

    private void updateObjectFamilies(GameObject gameObject) {
        for (Map.Entry<Family, Array<GameObject>> entry : familyCache.entrySet()) {
            Family family = entry.getKey();
            Array<GameObject> familyList = entry.getValue();

            boolean matches = family.matches(gameObject);
            boolean contains = familyList.contains(gameObject, true);

            if (matches && !contains) {
                familyList.add(gameObject);
            } else if (!matches && contains) {
                familyList.removeValue(gameObject, true);
            }
        }
    }
}
