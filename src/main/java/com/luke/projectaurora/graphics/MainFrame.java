package com.luke.projectaurora.graphics;

import java.awt.AWTException;
import java.awt.Dimension;
import static java.awt.Frame.*;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame{
    
    ArrayList<Image> icons = new ArrayList<>();

    public MainFrame() {
        
        loadIcons();
        attachSystemTray();
        init();
    }

    private void init() {
        setUILook();
        setTitle("Project Aurora");
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setIconImages(icons);
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setCloseOperation();
    }

    private void setUILook() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setCloseOperation() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(new JFrame(),
                        "Chiudendo il programma non potrai gestire il mousepad. Sei sicuro?", "Chiusura",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                            System.exit(0);
                }
            }
        });
    }

    private void attachSystemTray() {
        if (!SystemTray.isSupported()) {
            System.err.println("===TRAY DI SISTEMA NON SUPPORTATA===");
            System.exit(0);
        }
        final PopupMenu popup = new PopupMenu();
        Image img = Toolkit.getDefaultToolkit().getImage("src/main/resources/trayIcons/32.png");
        final TrayIcon trayIcon = new TrayIcon(img, "RGB Pad");
        final SystemTray tray = SystemTray.getSystemTray();

        MenuItem ripristina = new MenuItem("Ripristina");
        ripristina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                setExtendedState(JFrame.NORMAL);
            }
        });
        popup.add(ripristina);

        MenuItem esci = new MenuItem("Exit");
        ActionListener exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        esci.addActionListener(exitListener);
        popup.add(esci);

        trayIcon.setPopupMenu(popup);
        trayIcon.setImageAutoSize(true);

        this.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == ICONIFIED) {
                    try {
                        tray.add(trayIcon);
                        setVisible(false);
                    } catch (AWTException ex) {
                        System.err.println("unable to add to tray");
                    }
                }
                if (e.getNewState() == 7) {
                    try {
                        tray.add(trayIcon);
                        setVisible(false);
                    } catch (AWTException ex) {
                        System.out.println("unable to add to system tray");
                    }
                }
                if (e.getNewState() == MAXIMIZED_BOTH) {
                    tray.remove(trayIcon);
                    setVisible(true);
                }
                if (e.getNewState() == NORMAL) {
                    tray.remove(trayIcon);
                    setVisible(true);
                }
            }
        });
    }
    
    private void loadIcons() {
        icons.add(new ImageIcon("src/main/resources/trayIcons/16.png").getImage());
        icons.add(new ImageIcon("src/main/resources/trayIcons/32.png").getImage());
        icons.add(new ImageIcon("src/main/resources/trayIcons/64.png").getImage());
        icons.add(new ImageIcon("src/main/resources/trayIcons/128.png").getImage());
    }
}
