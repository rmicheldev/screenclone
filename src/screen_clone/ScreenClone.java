package screen_clone;

import javax.swing.*;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Toolkit;

public class ScreenClone extends JFrame {
    private final JLabel tela = new JLabel();
    private Image image;

    private void build(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        super.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        tela.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.getContentPane().add(tela);}

    public void capture() {
        try {
            Robot robot = new Robot();
            //get the screen
            this.image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            Image scaledImg = image.getScaledInstance((int) tela.getSize().getWidth(), (int) tela.getSize().getHeight(), Image.SCALE_FAST);
            //show the image as a icon from a label
            tela.setIcon(new ImageIcon(scaledImg));
            tela.validate();
        } catch (Exception e) {
            //
        }
    }

    public static void main(String args[]) throws InterruptedException {
        ScreenClone screenclone = new ScreenClone();
        screenclone.build();
        screenclone.setVisible(true);
        
        int i = 0;
        //can be infinite
        while(i++ < 10){
            screenclone.capture();
            
            screenclone.toFront();
            Thread.sleep(1000);
        }
    }
}
