// by Leon Tabak

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.Timer;

public class Heartbeat extends JFrame {

    private static final int WINDOW_WIDTH = 512;
    private static final int WINDOW_HEIGHT = 512;
    private static final String TITLE = "Heartbeat";

    public Heartbeat() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setTitle(TITLE);

        Container pane = this.getContentPane() ;
        HeartbeatMonitor monitor = new HeartbeatMonitor( 4 ) ;
        pane.add( monitor ) ;

        JSlider slider = new JSlider() ;
        slider.setMinimum(1) ;
        slider.setMaximum(8) ;
        slider.setOrientation(JSlider.VERTICAL);
        slider.addChangeListener( monitor ) ;
        pane.add( slider, BorderLayout.EAST);

        // create a timer that will send an ActionEvent
        // to the monitor object every 50/1000 of a second
        // (that's 20 times a second)
        Timer timer = new Timer( 50, monitor) ;
        timer.start() ;

        this.setVisible(true);
    } // Heartbeat()

    public static void main(String[] args) {
        Heartbeat heartbeat = new Heartbeat();
    } // main( String [] )
} // Heartbeat
