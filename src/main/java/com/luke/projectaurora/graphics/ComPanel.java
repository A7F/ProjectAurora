package com.luke.projectaurora.graphics;

import com.luke.projectaurora.core.SerialHandler;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * first panel, set and open connection + settings button
 * @author Luca
 */
public class ComPanel extends JPanel{
    SerialHandler handler = SerialHandler.getInstance();
    JPanel comPanel = new JPanel();
    JComboBox box = new JComboBox();
    JButton refresh = new JButton("scan");
    JButton set = new JButton("set");
    JButton settings = new JButton("settings");
    SettingsFrame settingsFrame = new SettingsFrame();
    
    public ComPanel(){
        init();
    }
    
    private void init(){
        addListeners();
        refresh.setPreferredSize(new Dimension(70,25));
        set.setPreferredSize(new Dimension(70,25));
        this.setBorder(BorderFactory.createTitledBorder("Serial COM"));
        this.setPreferredSize(new Dimension(600,60));
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(comPanel());
        this.add(settings);
    }
    
    /**
     * il pannello contenente l'elenco delle porte seriali, il set ed il refresh dell' elenco
     * @return JPanel
     */
    private JPanel comPanel(){
        JLabel scrollDesc = new JLabel("Port (COM): ");
        comPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        box.setSize(new Dimension(40,10));
        box.setPrototypeDisplayValue("COM....");
        box.setMaximumSize(box.getPreferredSize());
        //refresh.setPreferredSize(new Dimension(30,10));
        //set.setPreferredSize(new Dimension(30,10));
        comPanel.add(scrollDesc);
        comPanel.add(box);
        comPanel.add(refresh);
        comPanel.add(set);
        return comPanel;
    }
    
    private void addListeners(){
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                populateComboBox();
            }
        });
        
        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(box.getSelectedItem() == null){
                    System.out.println("===SERIAL PORT NOT SELECTED===");
                }else{
                    handler.setPortName(box.getSelectedItem().toString());
                }
            }
        });
        
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsFrame.setVisible(true);
            }
        });
    }
    
    private void populateComboBox(){
        box.removeAll();
        box.repaint();
        handler.scanSerialPort();
        for(String port : handler.getPorts()){
            box.addItem(port);
        }
        this.repaint();
    }
}
