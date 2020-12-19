package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import Handlers.Keys;
import Main.GamePanel;

//SOSTOYANIE PAUSE
public class PauseState extends GameState {

    private Font font;
    private Font font2;

    public PauseState(GameStateManager gsm) {

        super(gsm);

        // SHRIFT
        font = new Font("Century Gothic", Font.PLAIN, 140);
        font2 = new Font("Century Gothic", Font.PLAIN, 54);

    }

    public void init() {

    }

    public void update() {

        handleInput();
    }

    public void draw(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("PAUSE", 960, 340);
        g.drawString("EXIT to Menu", 260, 840);
        g.setFont(font2);
        g.drawString("[to continue press ESC]", 960, 440);
        g.drawString("[you exit from session in Menu, game process is not save press W]", 160, 940);
    }

    public void handleInput() {

        if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(false);
        if(Keys.isPressed(Keys.BUTTON1)) {

            gsm.setPaused(false);
            gsm.setState(GameStateManager.MENUSTATE);
        }
    }

}
