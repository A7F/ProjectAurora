package com.luke.projectaurora.graphics;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Luke
 */
public class MyJFrame extends JFrame{
    ArrayList<Image> icons = new ArrayList<>();
    
    public MyJFrame(){
        init();
    }
    
    private void init() {
        setUILook();
        loadIcons();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setIconImages(icons);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        pack();
    }
    
    private void setUILook() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadIcons() {
        icons.add(new ImageIcon("src/main/resources/trayIcons/16.png").getImage());
        icons.add(new ImageIcon("src/main/resources/trayIcons/32.png").getImage());
        icons.add(new ImageIcon("src/main/resources/trayIcons/64.png").getImage());
        icons.add(new ImageIcon("src/main/resources/trayIcons/128.png").getImage());
    }

    
    
}
