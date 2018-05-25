package com.luke.projectaurora.core;

import com.luke.projectaurora.graphics.MainFrame;
import java.util.Timer;

public class Main {

    public static void main(String[] args) {
        
        Timer timer = new Timer();
        timer.schedule(new DisplayService(), 0, 1500);
        
        MainFrame frame = new MainFrame();
        LedGraphicHandler ledHandler = LedGraphicHandler.getInstance();
        
        frame.add(ledHandler.getMainPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
