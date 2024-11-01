package FRONTEND_GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class mainFrame extends JFrame {

    JFrame mainGuiFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel southPanel = new JPanel();

    mainFrame() throws IOException {
        northPan genNorthPanel = new northPan();
        southPan genSouthPanel = new southPan();

        mainGuiFrame.setSize(new Dimension(650,550));
        mainGuiFrame.setVisible(true);
        mainPanel.setLayout(new BorderLayout());

        northPanel = genNorthPanel.getMainPanel();

        mainPanel.add(northPanel, BorderLayout.NORTH);
        //mainPanel.add(southPanel, BorderLayout.SOUTH);
        
        mainGuiFrame.add(mainPanel);
    }

    public static void main(String [] args) throws IOException {
        mainFrame tester = new mainFrame();
    }


}
