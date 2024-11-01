package FRONTEND_GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class northPan extends JPanel
{
    JPanel fullDisp;
    // east panel
    JPanel listDisplay;
    // west panels
    JPanel artAndDanceDisp;

    northPan() throws IOException {
        statsVisuals genPanels = new statsVisuals();

        fullDisp = new JPanel();
        fullDisp.setLayout(new BorderLayout());

        listDisplay = genPanels.dispList();


        fullDisp.add(listDisplay, BorderLayout.WEST);

    }

    JPanel getMainPanel(){return fullDisp;}
    JPanel getEastSubPanel(){return listDisplay;}
    JPanel getWestSubPanel(){return artAndDanceDisp;}


}
