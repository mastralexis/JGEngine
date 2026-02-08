package io.github.mastralexis.jgengine.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.mastralexis.jgengine.Main;

public class LoadingScreen implements Screen {
    final Main game;
    int targetLevel;    // the level we are loading into

    public LoadingScreen(Main game, int targetLevel) {
        this.game = game;
        this.targetLevel = targetLevel;

        // upload old assets
        game.assets.unloadLevelAssets();

        // queue new assets
        if (targetLevel == 1) game.assets.queueLevel1Assets();
        //else if (targetLevel == 2) game.assets.queueLevel2Assets();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // update() returns TRUE when loading is done
        if (game.assets.manager.update()) {
            // loading complete! Switch to the actual game
            game.setScreen(new GameplayScreen(game));
        }

        // draw a loading bar here using game.assets.manager.getProgress()
        float progress = game.assets.manager.getProgress();
        System.out.println("Loading... " + (progress * 100) + "%");
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
