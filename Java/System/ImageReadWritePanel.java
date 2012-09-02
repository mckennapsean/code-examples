// works with ImageReadWrite.java

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.JPanel;

public class ImageReadWritePanel extends JPanel {

    private BufferedImage image;

    public ImageReadWritePanel() {
        this.image = null;
    } // ImageReadWritePanel()

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (this.image != null) {

            // Image might be bigger (or smaller) than panel
            // in which we plan to draw it.
            // Determine what scaling factor we should use
            // to make it fit.
            double imageWidth = this.image.getWidth();
            double imageHeight = this.image.getHeight();
            double panelWidth = this.getWidth();
            double panelHeight = this.getHeight();

            double scaleX = 1.0;
            double scaleY = 1.0;
            if (imageWidth > imageHeight) {
                scaleX = panelWidth / imageWidth;
                scaleY = scaleX;
            } // if
            else {
                scaleY = panelHeight / imageHeight;
                scaleX = scaleY;
            } // else

            // Create the operator that will scale the image.
            AffineTransform transform = new AffineTransform();
            transform.setToScale(scaleY, scaleY);

            // Change the contents of this array to get different effects.
            // The convolution computes a weighted average of the values
            // of a pixel and its neighbors. These are the weights.
            float[] kernelData = {
                -0.125f, -0.125f, -0.125f,
                -0.125f, 2.000f, -0.125f,
                -0.125f, -0.125f, -0.125f
            };

            // Create the kernel of the convolution.
            Kernel kernel = new Kernel(3, 3, kernelData);

            // Create the convolution operator.
            ConvolveOp operator =
                    new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

            // Create the filter.
            BufferedImageFilter filter = new BufferedImageFilter(operator);

            // Create a place to store the filtered version of the image.
            BufferedImage filteredImage =
                    new BufferedImage(image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_ARGB);

            // Apply the filter to create the filtered image.
            operator.filter(image, filteredImage);

//            for( int j = image.getMinY(); j < image.getHeight(); j++ ) {
//                for( int i = image.getMinX(); i < image.getWidth(); i++ ) {
//                    if( i < j ) {
//                        int c = image.getRGB(i,j);
//                        int alpha = (c & 0xFF000000) >> 24;
//                        int red = (c & 0x00FF0000) >> 16;
//                        int green = (c & 0x0000FF00) >> 8;
//                        int blue = c & 0x000000FF;
//                        red = (128 + red)/2;
//                        green = (128 + green)/2;
//                        blue = (128 + blue)/2;
//                        int color = (alpha << 24) | (red << 16) | (green << 8) | blue;
//                        image.setRGB(i,j, color );
//                   } // if
//                } // for
//            } // for

            g2d.setColor( Color.BLACK );
            g2d.fillRect(0,0, (int) panelWidth, (int) panelHeight) ;

            AffineTransform scale = new AffineTransform() ;
            scale.setToScale( panelWidth, panelHeight );

            int xMin = image.getMinX();
            int xMax = xMin + image.getWidth();
            int yMin = image.getMinY();
            int yMax = yMin + image.getHeight();

//            double margin = 1.0/4.0;
//            int numberOfSteps= 32;
//            int stepSize = (int) Math.min(imageWidth, imageHeight)/numberOfSteps;
//            double fraction = 1.0 / numberOfSteps;
//
//            for( int j = yMin; j <= yMax - stepSize - 1; j+= stepSize ) {
//                for( int i = xMin; i <= xMax - stepSize - 1; i += stepSize ) {
//                    int red = 0;
//                    int green = 0;
//                    int blue = 0;
//                    for( int m = 0; m < stepSize; m ++ ) {
//                        for( int n = 0; n < stepSize; n++ ) {
//                            int color = image.getRGB( i + m, j + n);
//                            red += (color & 0x00FF0000) >> 16;
//                            green += (color & 0x0000FF00) >> 8;
//                            blue += (color & 0x000000FF);
//                        } // for
//                    } // for
//                    red = red / (stepSize * stepSize);
//                    green = green / (stepSize * stepSize);
//                    blue = blue / (stepSize * stepSize) ;
//                    g2d.setColor( new Color(red, green, blue));
//                    double x = i/stepSize * fraction;
//                    double y = j/stepSize * fraction ;
//                    x += margin * fraction;
//                    y += margin * fraction;
//                    double d = fraction - 2 * margin * fraction;
//                    Ellipse2D circle =
//                            new Ellipse2D.Double(x, y, d, d);
//                    g2d.fill( scale.createTransformedShape(circle)) ;
//                } // for
//            }

            // Draw the scaled and filtered image.
//            g2d.drawImage(image, transform, this);
            g2d.drawImage(filteredImage, transform, this);
        } // if
    } // paintComponent( Graphics )

    public void addImage(BufferedImage image) {
        this.image = image;
        this.repaint();
    } // addImage( BufferedImage )
} // ImageReadWritePanel
