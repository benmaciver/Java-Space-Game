package game1;
import utilities.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import static game1.Constants.FRAME_HEIGHT;
import static game1.Constants.FRAME_WIDTH;

public class BasicView extends JComponent {
    // background colour
    public static final Color BG_COLOR = Color.black;

    private BasicGame game;

    Image im = ImageManager.loadImage("milkyway1");
    AffineTransform bgTransf;

    public BasicView(BasicGame game) throws IOException {

        this.game = game;
        double imWidth = im.getWidth(null);
        double imHeight = im.getHeight(null);
        double stretchx = (imWidth > Constants.FRAME_WIDTH? 1 :
                Constants.FRAME_WIDTH/imWidth);
        double stretchy = (imHeight > Constants.FRAME_HEIGHT? 1 :
                Constants.FRAME_HEIGHT/imHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchx, stretchy);


    }

    @Override
    public void paintComponent(Graphics g0) {
            Graphics2D graphics = (Graphics2D) g0;

            // paint the background
            graphics.drawImage(im,bgTransf,null);
            //graphics.fillRect(0, 0, getWidth(), getHeight());
            for (GameObject object : game.gameObjects) {
                if (object.alive)
                    object.draw(graphics);
            }
            graphics.setColor(Color.YELLOW);
            graphics.setFont(new Font("dialog", Font.BOLD, 20));
            graphics.drawString("Level: " + game.getLevel(), 20, FRAME_HEIGHT - 20);
            graphics.drawString("Score: " + game.getScore(), FRAME_WIDTH / 3 + 20, FRAME_HEIGHT - 20);
            graphics.drawString("Lives: " + game.getLives(), FRAME_WIDTH / 3 + FRAME_WIDTH / 3, FRAME_HEIGHT - 20);

            getJFrame().setResizable(false);
    }

    @Override

    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
    private JFrame getJFrame(){
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }


}
