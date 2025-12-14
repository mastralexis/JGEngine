package io.github.mastralexis.jgengine.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.mastralexis.jgengine.Main;
import io.github.mastralexis.jgengine.engine.framework.GameObject;
import io.github.mastralexis.jgengine.engine.framework.Scene;
import io.github.mastralexis.jgengine.game.components.PlayerComponent;
import io.github.mastralexis.jgengine.game.components.PositionComponent;
import io.github.mastralexis.jgengine.game.components.SpriteComponent;
import io.github.mastralexis.jgengine.game.components.VelocityComponent;
import io.github.mastralexis.jgengine.game.systems.MovementSystem;
import io.github.mastralexis.jgengine.game.systems.PlayerInputSystem;
import io.github.mastralexis.jgengine.game.systems.RenderSystem;

public class GameplayScreen implements Screen {
    final Main game;
    Scene scene;        // object manager

    public GameplayScreen(Main game) {
        this.game = game;

        scene = new Scene();    // create the scene of that Screen

        // add systems that are going to be used in that screen
        scene.addGameSystem(new RenderSystem(game.batch));
        scene.addGameSystem(new PlayerInputSystem());
        scene.addGameSystem(new MovementSystem());

        createPlayer();
    }

    private void createPlayer() {
        Texture playerTexture = game.assets.getTexture("sprites/player/wayne-t-pose-scaled.png");
        GameObject player = new GameObject("Player")
            .addComponent(new PositionComponent(100, 100))
            .addComponent(new VelocityComponent(150f))
            .addComponent(new SpriteComponent(playerTexture))
            .addComponent(new PlayerComponent());
        scene.addGameObject(player);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();  // We must begin/end the batch because your RenderSystem calls batch.draw()
        scene.update(delta); // This triggers RenderSystem.update() -> batch.draw()
        game.batch.end();

        // =============== Temporary Exit Logic ===============
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game)); // Return to menu
            dispose(); // Clean up this screen
        }
        // =============== Temporary Exit Logic ===============
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {} // Called when leaving this screen
    // We don't dispose textures here because they belong to Main.assets
    // But if you had specific "Level 1" assets loaded just for this screen,
    // you would unload them here.
    @Override public void dispose() {}
    @Override public void show() {Gdx.input.setInputProcessor(null);}// Called when this screen becomes the current one
    @Override public void resize(int width, int height) {}// If you add a Camera later, update it here

}
