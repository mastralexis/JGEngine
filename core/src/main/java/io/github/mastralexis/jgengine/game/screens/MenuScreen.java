package io.github.mastralexis.jgengine.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.mastralexis.jgengine.Main;


public class MenuScreen implements Screen {
    final Main game;
    Stage stage;        // Scene2D's version of "Scene" (like a manager for the ui)
    Skin skin;          // fonts and textures
    Array<TextButton> buttons;
    int currentSelection = 0;

    public MenuScreen(Main game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        Table table = new Table();  // like html table
        table.setFillParent(true);  // take up the whole screen
        stage.addActor(table);

        buttons = new Array<>();

        TextButton startButton = new TextButton("Start Game", skin);    //buttons
        TextButton optionsButton = new TextButton("Options", skin);
        TextButton quitButton = new TextButton("Quit", skin);
        buttons.add(startButton);
        buttons.add(optionsButton);
        buttons.add(quitButton);

        // Event Listener (mouse click)
        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Switch to the Gameplay Screen
//                game.setScreen(new GameplayScreen(game));
                System.out.println("No game here\n Just quit man");
            }
        });

        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Go to Options Screen...");
            }
        });

        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit(); // Close the app
            }
        });

        // 4. KEYBOARD LOGIC
        // We add the listener to the STAGE so it catches keys globally
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.UP) {
                    currentSelection--;
                    if (currentSelection < 0) currentSelection = buttons.size - 1; // Wrap around
                    updateButtonVisuals();
                    return true;
                }

                if (keycode == Input.Keys.DOWN) {
                    currentSelection++;
                    if (currentSelection >= buttons.size) currentSelection = 0; // Wrap around
                    updateButtonVisuals();
                    return true;
                }

                if (keycode == Input.Keys.ENTER) {
                    // Simulate a click on the currently selected button
                    buttons.get(currentSelection).fire(new ChangeListener.ChangeEvent());
                    return true;
                }
                return false;
            }
        });

        // 6. Add Buttons to Table (with padding)
        table.add(startButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(optionsButton).fillX().uniformX();
        table.row();
        table.add(quitButton).fillX().uniformX();

        updateButtonVisuals();
    }

    // Helper method to change colors
    private void updateButtonVisuals() {
        for (int i = 0; i < buttons.size; i++) {
            TextButton btn = buttons.get(i);
            if (i == currentSelection) {
                btn.setColor(Color.YELLOW); // Selected Color
            } else {
                btn.setColor(Color.WHITE);  // Default Color
            }
        }
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update and Draw the UI
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport when window is resized
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void show() {
        // IMPORTANT: Tell LibGDX to send mouse clicks to our Stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

}
