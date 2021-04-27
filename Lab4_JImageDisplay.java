// import javax.swing.*;
// import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class JImageDisplay extends JComponent {

    private BufferedImage image;
    
    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width, height));
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage (image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage(){
        for (int x = 0; x < image.getHeight(); ++x) {
            for (int y = 0; y < image.getWidth(); ++y) {
                image.setRGB(x, y, 0);
            }
        }
    }

    public void drawPixel (int x, int y, int rgbColor){
        image.setRGB(x, y, rgbColor);
    }
    
}
