package com.luke.projectaurora.graphics;

import com.luke.projectaurora.entities.Led;
import com.luke.projectaurora.entities.Zone;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Luke
 */
public class ColorPickerPanel extends JPanel implements ChangeListener{
    
    JPanel previewPanel = new JPanel();
    JPanel confirmPanel = new JPanel();
    JLabel colorSelectedLabel = new JLabel();
    JButton confirmButton = new JButton("Select");
    Color colorSelected;
    JColorChooser colorChooser = new JColorChooser();
    Led targetLed;
    Zone targetZone;
    
    public ColorPickerPanel(){
        init();
    }

    private void init() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(targetZone==null){
                    targetLed.setLedColor(colorSelected);
                }else{
                    targetZone.setZoneColor(colorSelected);
                }
            }
        });
        
        confirmButton.setPreferredSize(new Dimension(80,20));
        confirmPanel.add(confirmButton);
        colorSelectedLabel.setOpaque(true);
        colorSelectedLabel.setBackground(Color.BLACK);
        colorSelectedLabel.setPreferredSize(new Dimension(400, 30));
        previewPanel.setLayout(new GridLayout(2,1));
        previewPanel.add(colorSelectedLabel,BorderLayout.CENTER);
        previewPanel.add(confirmPanel);
        colorChooser.getSelectionModel().addChangeListener(this);
        colorChooser.setPreviewPanel(previewPanel);
        this.add(colorChooser);
    }
    
    @Override
    public void stateChanged(ChangeEvent e){
        colorSelected = colorChooser.getColor();
        colorSelectedLabel.setBackground(colorSelected);
    }

    public Color getColorSelected() {
        return colorSelected;
    }
    
    public void setTargetLed(Led label){
        this.targetLed = label;
    }

    public void setTargetZone(Zone targetZone) {
        this.targetZone = targetZone;
    }
}
