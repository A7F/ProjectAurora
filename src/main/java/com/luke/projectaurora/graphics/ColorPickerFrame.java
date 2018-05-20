package com.luke.projectaurora.graphics;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Luke
 */
public class ColorPickerFrame extends JFrame{
    
    ColorPickerPanel panel = new ColorPickerPanel();
    
    public ColorPickerFrame(){
        init();
    }

    private void init() {
        try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ColorPickerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setTitle("choose one");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    public ColorPickerPanel getPanel() {
        return panel;
    }
}
