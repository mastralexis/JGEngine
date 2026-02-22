package io.github.mastralexis.jgengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.mastralexis.jgengine.engine.assets.EngineAssetManager;
import io.github.mastralexis.jgengine.game.screens.DebugTitleScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;        // public to be available
    public EngineAssetManager assets;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // init and Load Assets
        assets = new EngineAssetManager();
        assets.finishLoading();       // blocks until everything is ready

        // start the Menu
        this.setScreen(new DebugTitleScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if (screen != null) screen.dispose();
        batch.dispose();
        assets.dispose();
    }
}
