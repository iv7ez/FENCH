package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import TileMap.TileMap;

//TELEPORT
public class Teleport extends MapObject {

    private BufferedImage[] sprites;

    public Teleport(TileMap tm) {

        super(tm);
        facingRight = true;
        width = height = 120;
        cwidth = 60;
        cheight = 120;

        try {

            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/Teleport.gif"));
            sprites = new BufferedImage[9];
            for(int i = 0; i < sprites.length; i++) {

                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);

            }

            animation.setFrames(sprites);
            animation.setDelay(1);
        }
        catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void update() {

        animation.update();
    }

    public void draw(Graphics2D g) {

        super.draw(g);
    }

}
