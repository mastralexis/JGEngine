package io.github.mastralexis.jgengine.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.mastralexis.jgengine.Main;
import io.github.mastralexis.jgengine.engine.physics.MovementSystem;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.Scene;
import io.github.mastralexis.jgengine.engine.physics.BoxColliderComponent;
import io.github.mastralexis.jgengine.engine.physics.TransformComponent;
import io.github.mastralexis.jgengine.engine.physics.VelocityComponent;
import io.github.mastralexis.jgengine.engine.rendering.DebugRenderSystem;
import io.github.mastralexis.jgengine.engine.rendering.RenderSystem;
import io.github.mastralexis.jgengine.engine.rendering.SpriteComponent;
import io.github.mastralexis.jgengine.game.player.InputComponent;
import io.github.mastralexis.jgengine.game.player.PlayerComponent;
import io.github.mastralexis.jgengine.game.player.PlayerControlSystem;
import io.github.mastralexis.jgengine.game.player.PlayerInputSystem;

public class GameplayScreen implements Screen {
    final Main game;
    Scene scene;        // object manager
    String currentMapPath;

    public GameplayScreen(Main game, String mapPath) {
        this.game = game;
        scene = new Scene();    // create the scene of that Screen
        currentMapPath = mapPath;

        // add systems that are going to be used in that screen
        // scene.addGameSystem(new RenderSystem(game.batch));
        scene.addGameSystem(new PlayerInputSystem());
        scene.addGameSystem(new PlayerControlSystem());
        scene.addGameSystem(new MovementSystem());
        scene.addGameSystem(new DebugRenderSystem());
    }

    // runs after the screen is fully active
    @Override public void show() {
        Gdx.input.setInputProcessor(null);
        createPlayer();
        createWall();
    }

    private void createPlayer() {
        SpriteComponent sprite = new SpriteComponent(game.assets.getTexture("sprites/player/wayne-t-pose.png"), 128, 128);
        GameObject player = new GameObject("Player")
            .addComponent(sprite)
            .addComponent(new TransformComponent(100, 100))
            .addComponent(new VelocityComponent(250f))
            .addComponent(new PlayerComponent())
            .addComponent(new BoxColliderComponent(sprite.width - 25, sprite.height))
            .addComponent(new InputComponent());
        scene.addGameObject(player);
    }

    private void createWall() {

        SpriteComponent sprite = new SpriteComponent(game.assets.getTexture("sprites/player/wayne-t-pose.png"), 128, 128);
        GameObject wall = new GameObject("Wall")
            .addComponent(new TransformComponent(300, 300))
            .addComponent(sprite)
            .addComponent(new BoxColliderComponent(sprite.width - 25, sprite.height));

        scene.addGameObject(wall);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        scene.update(delta); // this triggers RenderSystem.update() -> batch.draw()

        // =============== Temporary Exit Logic ===============
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        // =============== Temporary Exit Logic ===============
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {} // called when leaving this screen
    @Override public void dispose() {}
    @Override public void resize(int width, int height) {}// If you add a Camera later, update it here

}
