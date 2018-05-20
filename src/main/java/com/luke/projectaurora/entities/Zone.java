package com.luke.projectaurora.entities;

import com.luke.projectaurora.graphics.ColorPickerFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Luke
 */
public class Zone extends JLabel {

    ArrayList<Led> localLeds;
    ColorPickerFrame colorPicker = new ColorPickerFrame();
    Color zoneColor;

    public Zone(ArrayList<Led> zone) {
        localLeds = zone;
        init();
    }

    public void setLocalLeds(ArrayList<Led> localLeds) {
        this.localLeds = localLeds;
    }

    public void setZoneColor(Color color) {
        if (color == null) {
            color = Color.BLACK;
        }
        this.zoneColor = color;
        double luminance = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
        if (luminance > 128) {
            setForeground(Color.BLACK);
        } else {
            setForeground(Color.WHITE);
        }
        setBackground(color);
        for (Led led : localLeds) {
            led.setLedColor(color);
        }
    }

    private void init() {
        this.setPreferredSize(new Dimension(80, 30));
        this.setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        setZoneColor(Color.black);
        colorPicker.getPanel().setTargetZone(this);
        addListener();
    }

    private void addListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                colorPicker.setVisible(true);
            }
        });
    }

}
