/**
 * This program shows you how to get started
 * with image processing.
 * Must execute in directory with the other class.
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This is an image processing application.
 *
 * <a href="../../launch.html">Run the program.</a>
 *
 * Also, see <a href="imagereadwrite.pdf">slides (PDF)</a>
 */
public class ImageReadWrite extends JFrame {

    private final static int FRAME_WIDTH = 512;
    private final static int FRAME_HEIGHT = 512;
    private final static String TITLE = "Image Read and Write";

    public ImageReadWrite() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(TITLE);

        Container pane = this.getContentPane();
        ImageReadWritePanel panel = new ImageReadWritePanel();
        panel.setBackground(Color.GRAY);
        pane.add(panel);

        this.setVisible(true);

        // Create a pop-up window that displays files
        // and directories from which we can select an
        // image file.
        JFileChooser chooser = new JFileChooser();

        // Stipulate that we are only interested in looking
        // at files with the extensions ",jpg", ".jpeg", and ".png".
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("JPG and PNG files",
                "jpg", "jpeg", "png");
        chooser.setFileFilter(filter);

        // Stipulate that we want to begin the search
        // in the directory "./test".
        File directory = new File("./test");
        chooser.setCurrentDirectory(directory);

        // Pop up the file chooser window and wait for
        // user to select a file.
        int status = chooser.showOpenDialog(this);
        if (status == chooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                // Read image file and create a BufferedImage from
                // the data.
                BufferedImage image = ImageIO.read(file);

                // Add image to panel and repaint panel to make
                // the image visible.
                panel.addImage(image);
            } // try
            catch (IOException e) {
            } // catch( IOException )
        } // if
    } // ImageReadWrite()

    public static void main(String[] args) {
        ImageReadWrite rw = new ImageReadWrite();
    } // main( String [] )
} // ImageReadWrite

