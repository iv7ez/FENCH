package Handlers;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// ZAGRUZKA SPRITOV I IH RASPREDELENIE

public class Content {

    public static BufferedImage[][] EnergyParticle = load("/Sprites/Player/EnergyParticle.gif", 15, 15);
    public static BufferedImage[][] Explosion = load("/Sprites/Enemies/Explosion.gif", 30, 30);
    public static BufferedImage[][] Ghost = load("/Sprites/Enemies/Ghost.gif", 117, 60);
    public static BufferedImage[][] Jija = load("/Sprites/Enemies/Jija.gif", 75, 75);

    public static BufferedImage[][] load(String s, int w, int h) {

        BufferedImage[][] ret;

        try {

            BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(s));
            int width = spritesheet.getWidth() / w;
            int height = spritesheet.getHeight() / h;
            ret = new BufferedImage[height][width];

            for(int i = 0; i < height; i++) {

                for(int j = 0; j < width; j++) {

                    ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
                }

            }

            return ret;

        }
        catch(Exception e) {

            e.printStackTrace();
            System.out.println("Error loading graphics");
            System.exit(0);
        }
        return null;
    }

}
