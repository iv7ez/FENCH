package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import Handlers.Keys;
import TileMap.Background;

//KONCOVKA
public class GameOverScreen extends GameState {

    private Background bg;
    private Font font4;
    public GameOverScreen(GameStateManager gsm) {

        super(gsm);
        try {
            font4 = new Font("Century Gothic", Font.PLAIN, 50);
            bg = new Background("/Backgrounds/GAMEOVERSCREEN.png", 1);
            bg.setVector(0, 0);

        }
        catch(Exception e) {

        }
    }

    public void init() {

    }

    public void update() {

        bg.update();
        handleInput();

    }

    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(Color.WHITE);
        g.setFont(font4);
        g.drawString("[PRESS SOME KEY TO EXIT TO MENU]", 50, 1000);


    }

    public void handleInput() {
        if(Keys.isPressed(Keys.UP)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.LEFT)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.DOWN)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.RIGHT)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.BUTTON1)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.BUTTON2)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.BUTTON3)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.BUTTON4)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.ENTER)) gsm.setState(GameStateManager.MENUSTATE);
        if(Keys.isPressed(Keys.ESCAPE)) gsm.setState(GameStateManager.MENUSTATE);
    }

}
