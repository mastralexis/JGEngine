package io.github.mastralexis.jgengine.engine.framework;

import com.badlogic.gdx.utils.ObjectIntMap;

import java.util.HashMap;
import java.util.Map;

public class ComponentType {
    private static final ObjectIntMap<Class<? extends GameComponent>> classToIndex = new ObjectIntMap<>();
    private static int nextIndex = 0;

    public static int getIndexFor(Class<? extends GameComponent> type) {
        int index = classToIndex.get(type, -1);
        if (index == -1) {
            index = nextIndex++;
            classToIndex.put(type, index);
        }
        return index;
    }

    public static int getCount() {
        return nextIndex;
    }
}
