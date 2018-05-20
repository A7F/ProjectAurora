package com.luke.projectaurora.graphics;

import com.luke.projectaurora.entities.DataVector;
import com.luke.projectaurora.core.SerialHandler;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class BrightnessPanel extends JPanel{
    private static final int MAX_BRIGHTNESS = 255;
    private static final int MIN_BRIGHTNESS = 1;
    private static final int BRIGHTNESS_INIT = 128;
    private JSlider brightness;
    int currentValue=0;
    DataVector data = DataVector.getInstance();
    
    public BrightnessPanel(){
        init();
    }

    private void init() {
        JLabel label = new JLabel("brightness: ");
        this.setLayout(new FlowLayout());
        this.add(label);
        brightness = new JSlider(JSlider.HORIZONTAL, MIN_BRIGHTNESS, MAX_BRIGHTNESS, BRIGHTNESS_INIT);
        brightness.setMajorTickSpacing(25);
        brightness.setPaintTicks(true);
        brightness.setSnapToTicks(true);
        brightness.setValue(25);
        currentValue = brightness.getValue();
        
        brightness.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(!brightness.getValueIsAdjusting()){
                    if(!(currentValue==brightness.getValue())){
                        currentValue=brightness.getValue();
                        data.setCommandID(0);
                        data.setBrightness(currentValue);
                        SerialHandler.getInstance().sendData(data.getVector());
                    }
                }
            }
        });
        
        this.add(brightness);
    }
}
