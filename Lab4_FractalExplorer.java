import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.geom.Rectangle2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.BorderLayout;





public class FractalExplorer {
    public static void main(String[] args){
        FractalExplorer fractalExplorer = new FractalExplorer(800);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }

    private int sizeWindow;
    private JImageDisplay image;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double fractalRectangle;

    public FractalExplorer(int size){
        this.sizeWindow = size;
        image = new JImageDisplay(sizeWindow, sizeWindow);        
        setFractalGenerator(new Mandelbrot());

        fractalRectangle = new Rectangle2D.Double();
        fractalGenerator.getInitialRange(fractalRectangle);
    }

    public void createAndShowGUI(){
        JFrame frame = new JFrame();
        JPanel buttons = new JPanel();

        JButton reset = new JButton("ResetDisplay");
        buttons.add(reset);
        reset.addActionListener(new ButtonListener());

        frame.getContentPane().add(image, BorderLayout.CENTER);
        frame.getContentPane().add(buttons, BorderLayout.SOUTH);
        frame.setTitle("Fractal explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack ();
        frame.setVisible (true);
        frame.setResizable (false);

        image.addMouseListener(new MouseDisplayListener());
    }
    
    private void drawFractal (){
        for (int x = 0; x < sizeWindow; ++x) {
            for (int y = 0; y < sizeWindow; ++y) {
                double xCoord = fractalGenerator.getCoord(fractalRectangle.x,
                        fractalRectangle.x + fractalRectangle.width, sizeWindow,
                        x);
                double yCoord = fractalGenerator.getCoord(fractalRectangle.y,
                        fractalRectangle.y + fractalRectangle.height, sizeWindow,
                        y);

                int rgbColor = 0;
                int iterations = fractalGenerator.numIterations(xCoord, yCoord);

                if (iterations != -1) {
                    float hue =  0.7f + (float) iterations / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                }

                image.drawPixel(x, y, rgbColor);
            }
        }

        image.repaint();
    }

    private class ButtonListener implements ActionListener {
        // @Override
        public void actionPerformed(ActionEvent arg0) {
            fractalGenerator.getInitialRange(fractalRectangle);
            drawFractal();
        }

    }

    private class MouseDisplayListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);

            int x = e.getX();
            int y = e.getY();

            double xCoord = FractalGenerator.getCoord(fractalRectangle.x,
                    fractalRectangle.x + fractalRectangle.width, sizeWindow, x);
            double yCoord = FractalGenerator.getCoord(fractalRectangle.y,
                    fractalRectangle.y + fractalRectangle.height, sizeWindow, y);

            fractalGenerator.recenterAndZoomRange(fractalRectangle, xCoord, yCoord, 0.5);
            drawFractal();
        }

    }

    public final void setFractalGenerator(final FractalGenerator generator) {
        this.fractalGenerator = generator;
    }
    
}
