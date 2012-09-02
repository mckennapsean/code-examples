import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FractalPanel extends JPanel implements ActionListener{
  private BufferedImage fractalImage;

  public FractalPanel(BufferedImage im){
    super(new BorderLayout());
    JToolBar toolBar = new JToolBar("Still draggable");
    addButtons(toolBar);
    add(toolBar, BorderLayout.PAGE_START);
    fractalImage = im;
  }

  public void actionPerformed(ActionEvent e){
    System.out.println("Action has been performed.");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(fractalImage,0,0,this);
  }

  protected void addButtons(JToolBar toolBar) {
    JButton button1 = null;
    button1 = new JButton("Button 1");
    button1.setActionCommand("Button 1");
    button1.setToolTipText("This is button 1.");
    button1.addActionListener(this);
    toolBar.add(button1);
  }
}