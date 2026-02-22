package io.github.mastralexis.jgengine.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.mastralexis.jgengine.Main;

public class LoadingScreen implements Screen {
    final Main game;
    private final Screen nextScreen;    // the screen to load next

    public LoadingScreen(Main game, Screen nextScreen) {
        this.game = game;
        this.nextScreen = nextScreen;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // update() returns TRUE when loading is done
        if (game.assets.update()) {
            // switch to the game screen
            game.setScreen(nextScreen);
            dispose();  // dispose of that screen
        } else {
            float progress = game.assets.manager.getProgress();
            System.out.println("Loading: " + (progress * 100) + "%");
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
    @Override public void show() {}
}
