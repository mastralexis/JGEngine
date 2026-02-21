package io.github.mastralexis.jgengine.game.world;

import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import io.github.mastralexis.jgengine.Main;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.game.stats.HealthComponent;
import io.github.mastralexis.jgengine.engine.physics.TransformComponent;
import io.github.mastralexis.jgengine.engine.rendering.SpriteComponent;

public class EntityFactory {
    private final Main game;
    private JsonValue enemyData;

    public EntityFactory(Main game) {
        this.game = game;

        JsonReader reader = new JsonReader();

    }

    public GameObject createEnemy(String type, float x, float y) {
        // find the data for "GOBLIN" or "BOSS" for example
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

        // extract data
        String textureName = data.getString("texture");
        int hp = data.getInt("hp");
        float speed = data.getFloat("speed");

        // build the GameObject
        return new GameObject(type)
            .addComponent(new TransformComponent(x, y))
            .addComponent(new SpriteComponent(game.assets.getTexture(textureName)))
            .addComponent(new HealthComponent(hp));
    }
}
