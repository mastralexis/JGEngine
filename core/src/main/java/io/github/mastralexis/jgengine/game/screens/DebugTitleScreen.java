package io.github.mastralexis.jgengine.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.mastralexis.jgengine.Main;

public class DebugTitleScreen implements Screen {
    final Main game;
    BitmapFont font;

    public DebugTitleScreen(Main game) {
        this.game = game;
        font = new BitmapFont();
        font.getData().setScale(2.0f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.batch.begin();
        font.draw(game.batch, "Herro: Press ENTER", 100, 100);
        game.batch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameplayScreen(game)); // go to GameScreen
            dispose(); // clean up this screen
        }

    }
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
