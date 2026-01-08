package io.github.mastralexis.jgengine.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.mastralexis.jgengine.Main;

public class TitleScreen implements Screen {

    final Main game;
    Texture background;
    BitmapFont font;

    public TitleScreen(Main game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("titlescreen.png"));    // load it here directly without the asset manager
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
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(game.batch, "Herro: Press ENTER", 100, 100);
        game.batch.end();


        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new GameplayScreen(game)); // go to GameScreen
            dispose(); // Clean up this screen
        }

    }
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
