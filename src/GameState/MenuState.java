package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Entity.PlayerSave;
import Handlers.Keys;
import TileMap.Background;


//MENU
public class MenuState extends GameState {

    private Background bg;
    private BufferedImage head;

    private int currentChoice = 0;

    private String[] options = {

            "Adventure",
            "Exit"
    };

    private Color titleColor;
    private Font titleFont;
    private Font font;
    private Font font2;

    public MenuState(GameStateManager gsm) {

        super(gsm);



        try {

            bg = new Background("/Backgrounds/menubg.png", 1);
            bg.setVector(0, 0);

            // ZAGRUZKA "LAPKI"
            head = ImageIO.read(getClass().getResourceAsStream("/HUD/LAPA.png")).getSubimage(0, 0, 83, 64);

            // TITLE and FONT
            titleColor = Color.YELLOW;
            titleFont = new Font("Times New Roman", Font.PLAIN, 100);
            font = new Font("Arial", Font.PLAIN, 80);
            font2 = new Font("Arial", Font.PLAIN, 80);


        }
        catch(Exception e) {

            e.printStackTrace();
        }

    }

    public void init() {

    }

    public void update() {

        bg.update();

        // check keys
        handleInput();

    }

    public void draw(Graphics2D g) {
        // DRAV BG

        bg.draw(g);

        // TITLE
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("FENCH ADVENTURE", 500, 250);

        // MENU OPCII
        g.setFont(font);
        g.setColor(Color.ORANGE);
        g.drawString("ADVENTURE", 600, 500);
        g.drawString("EXIT", 600, 600);

        // "LAPKA"
        if(currentChoice == 0) g.drawImage(head, 500, 440, null);
        else if(currentChoice == 1) g.drawImage(head, 500, 540, null);




    }



    //VIBOr
    private void select() {

        if(currentChoice == 0) {

            PlayerSave.init();
            gsm.setState(GameStateManager.LEVEL1);
        }

        else if(currentChoice == 1) {

            System.exit(0);
        }
    }

    public void handleInput() {

        if(Keys.isPressed(Keys.ENTER)) select();
        if(Keys.isPressed(Keys.UP)) {

            if(currentChoice > 0) {

                currentChoice--;
            }
        }

        if(Keys.isPressed(Keys.DOWN)) {

            if(currentChoice < options.length - 1) {

                currentChoice++;
            }
        }
    }

}
