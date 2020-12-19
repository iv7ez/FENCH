package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//HP and TRY'i
public class HUD {

    private Player player;

    private BufferedImage heart;
    private BufferedImage life;

    public HUD(Player p) {

        player = p;

        try {

            BufferedImage image = ImageIO.read(getClass().getResourceAsStream( "/HUD/Hud.gif"));

            heart = image.getSubimage(0, 0, 39, 36);
            life = image.getSubimage(0, 36, 36, 33);
        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g) {

        for(int i = 0; i < player.getHealth(); i++) {

            g.drawImage(heart, 10 + i * 50, 60, null);
        }

        for(int i = 0; i < player.getLives(); i++) {

            g.drawImage(life, 10 + i * 50, 150, null);
        }

        g.setColor(java.awt.Color.WHITE);
        g.drawString(player.getTimeToString(), 1600, 100);
    }

}
