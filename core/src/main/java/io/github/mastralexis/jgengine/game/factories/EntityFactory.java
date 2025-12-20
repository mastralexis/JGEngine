package io.github.mastralexis.jgengine.game.factories;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import io.github.mastralexis.jgengine.Main;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.game.components.HealthComponent;
import io.github.mastralexis.jgengine.game.components.TransformComponent;
import io.github.mastralexis.jgengine.game.components.SpriteComponent;

public class EntityFactory {
    private final Main game;
    private JsonValue enemyData;

    public EntityFactory(Main game) {
        this.game = game;

        JsonReader reader = new JsonReader();

    }

    public GameObject createEnemy(String type, float x, float y) {
        // 1. Find the data for "GOBLIN" or "BOSS"
        JsonValue data = null;
        for (JsonValue entry : enemyData) {
            if (entry.getString("type").equals(type)) {
                data = entry;
                break;
            }
        }

        if (data == null) {
            throw new RuntimeException("Enemy type not found: " + type);
        }

        // 2. Extract Data
        String textureName = data.getString("texture");
        int hp = data.getInt("hp");
        float speed = data.getFloat("speed");

        // 3. Build the GameObject
        return new GameObject(type)
            .addComponent(new TransformComponent(x, y))
            .addComponent(new SpriteComponent(game.assets.getTexture(textureName)))
            .addComponent(new HealthComponent(hp));
    }
}
