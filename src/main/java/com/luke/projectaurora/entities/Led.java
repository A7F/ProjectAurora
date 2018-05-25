package com.luke.projectaurora.entities;

import com.luke.projectaurora.core.SerialHandler;
import com.luke.projectaurora.graphics.ColorPickerFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author Luke
 */
public class Led extends JLabel {

    private int ledId;
    private Color ledColor;
    private int brightness = 150;
    ColorPickerFrame colorPicker = new ColorPickerFrame();

    public Led() {
        init();
    }

    public Led(Color color) {
        this.ledColor = color;
        setBackground(color);
        init();
    }

    public Led(int ledId) {
        this.ledId = ledId;
        super.setText(String.valueOf(ledId));
        init();
    }

    public int getLedId() {
        return ledId;
    }

    public void setLedId(int ledId) {
        this.ledId = ledId;
        super.setText(String.valueOf(ledId));
    }

    @Override
    public void setText(String text) {
        if (text.isEmpty()) {
            this.ledId = 0;
        } else {
            this.ledId = Integer.valueOf(text);
        }
        super.setText(text);
    }

    public Color getColor() {
        return ledColor;
    }

    public void setLedColor(Color color) {
        this.ledColor = color;
        double luminance = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();
        if (luminance > 128) {
            setForeground(Color.BLACK);
        } else {
            setForeground(Color.WHITE);
        }
        DataVector data = DataVector.getInstance();
        data.setLedId(ledId - 1);
        data.setBrightness(brightness);
        data.setRGB(color.getRed(), color.getGreen(), color.getBlue());
        SerialHandler.getInstance().sendData(data.getByteVector());
        setBackground(color);
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    private void addListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                colorPicker.setVisible(true);
            }
        });
    }

    private void init() {
        setOpaque(true);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        addListener();
        colorPicker.getPanel().setTargetLed(this);
        if (ledColor == null) {
            ledColor = Color.BLACK;
            setBackground(ledColor);
            setForeground(Color.white);
        }
        this.setPreferredSize(new Dimension(50, 30));
    }

}
