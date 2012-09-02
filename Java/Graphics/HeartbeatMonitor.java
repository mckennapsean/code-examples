// by Leon Tabak

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HeartbeatMonitor extends JPanel
        implements ActionListener, ChangeListener {
    // background and foreground colors
    private static final Color BG_COLOR = Color.GREEN;
    private static final Color FG_COLOR = Color.WHITE;
    // normalized value for incrementing angle between
    // drawing each frame of animation
    // 0.0 <= ANGLE_INCREMENT <= 1.0
    // this value determines the speed with which dot
    // moves---a large value results in a faster movement
    private final double ANGLE_INCREMENT = 0.04 ;
    // normalized radius (0.0 <= radius <= 1.0)
    private final double RADIUS = 0.02;
    private double angle = 0.0;
    private int frequency;

    public HeartbeatMonitor(int frequency) {
        this.setBackground(BG_COLOR);
        this.setForeground(FG_COLOR);
        this.frequency = frequency;
    } // HeartbeatMonitor()

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // normalized coordinates (0.0 <= x <= 1.0 and 0.0 <= y <= 1.0)
        double x = this.angle/(2.0 * Math.PI);
        double y = (1 + Math.cos(this.frequency*this.angle))/2;

        // get width and height of panel on which we are drawing
        int width = this.getWidth();
        int height = this.getHeight();

        // scale the coordinates
        x = x * width ;
        // y coordinate remains between 1/4 and 3/4 of panel's height
        y = 3*height/4 - (y * height)/2 ;
        double r = RADIUS * Math.min( width, height) ;

        // compute coordinates of upper left corner of
        // smallest square that contains dot
        double ulx = x - r ;
        double uly = y - r ;
        // compute size of smallest square that contains dot
        double diameter = 2 * r ;

        Ellipse2D dot = new Ellipse2D.Double( ulx, uly, diameter, diameter ) ;
        g2d.fill( dot ) ;
    } // paintComponent( Graphics )

    public void actionPerformed(ActionEvent e) {
        this.angle = this.angle + ANGLE_INCREMENT ;
        if( this.angle > 2 * Math.PI ) {
            this.angle = this.angle - (2.0 * Math.PI) ;
        } // if
        this.repaint() ;
    } // actionPerformed( ActionEvent )

    public void stateChanged(ChangeEvent e) {
        JSlider controller = (JSlider) e.getSource();
        if( !controller.getValueIsAdjusting()) {
            this.frequency = controller.getValue() ;
            //System.out.println( "frequency = " + this.frequency ) ;
        } // if
        //this.frequency ;
    }
} // HeartbeatMonitor
