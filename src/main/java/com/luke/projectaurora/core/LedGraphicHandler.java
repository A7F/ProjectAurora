package com.luke.projectaurora.core;

import com.luke.projectaurora.entities.Led;
import com.luke.projectaurora.entities.LedLayout;
import com.luke.projectaurora.entities.Zone;
import com.luke.projectaurora.graphics.MainPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Luke
 */
public class LedGraphicHandler {

    private static LedGraphicHandler instance;
    private ArrayList<Led> leds = new ArrayList<>();
    private ArrayList<Led> south;
    private ArrayList<Led> west;
    private ArrayList<Led> east;
    private HashMap<LedLayout, ArrayList<Zone>> layouts = new HashMap<LedLayout, ArrayList<Zone>>();
    private MainPanel mainPanel = new MainPanel();
    private LedLayout layout = LedLayout.ALL;

    LedGraphicHandler() {
        init();
    }

    public static LedGraphicHandler getInstance() {
        if (instance == null) {
            instance = new LedGraphicHandler();
        }
        return instance;
    }

    private void init() {
        for (int i = 0; i < AppConfig.getInstance().getLedNum(); i++) {
            leds.add(new Led(i + 1));
        }
        west = new ArrayList<>(leds.subList(0, AppConfig.getInstance().getLedAxisY()));
        south = new ArrayList<>(leds.subList(AppConfig.getInstance().getLedAxisY(), AppConfig.getInstance().getLedAxisX() * 2));
        east = new ArrayList<>(leds.subList(AppConfig.getInstance().getLedAxisX() * 2, AppConfig.getInstance().getLedAxisY() * 3));

        setLayouts();

        //when program starts, fill as single leds
        for (Led led : leds) {
            mainPanel.getLedPanel().add(led);
        }
    }

    public void switchLayout(LedLayout layout) {
        mainPanel.getLedPanel().removeAll();
        switch (layout) {
            case ALL:
                for (Led led : leds) {
                    mainPanel.getLedPanel().add(led);
                }
                break;
            case MONO:
                for(Zone zone : layouts.get(LedLayout.MONO)){
                    mainPanel.getLedPanel().add(zone);
                }
                mainPanel.repaint();
                break;
            case SPLIT_VERTICAL:
                for(Zone zone : layouts.get(LedLayout.SPLIT_VERTICAL)){
                    mainPanel.getLedPanel().add(zone);
                }
                mainPanel.repaint();
                break;
            case SPLIT_SIDES:
                for (Zone zone : layouts.get(LedLayout.SPLIT_SIDES)) {
                    mainPanel.getLedPanel().add(zone);
                }
                break;
            default:
                break;
        }
        mainPanel.getLedPanel().revalidate();
        mainPanel.getLedPanel().repaint();
    }

    public ArrayList<Led> getLeds() {
        return leds;
    }

    public ArrayList<Led> getSouth() {
        return south;
    }

    public ArrayList<Led> getWest() {
        return west;
    }

    public ArrayList<Led> getEast() {
        return east;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public String toString() {
        return south.toString();
    }

    private void setLayouts() {
        ArrayList<Zone> splitSides = new ArrayList<>();
        Zone westZone = new Zone(west);
        westZone.setText("West");
        Zone southZone = new Zone(south);
        southZone.setText("South");
        Zone eastZone = new Zone(east);
        eastZone.setText("East");
        splitSides.add(westZone);
        splitSides.add(southZone);
        splitSides.add(eastZone);
        layouts.put(LedLayout.SPLIT_SIDES, splitSides);

        ArrayList<Zone> splitVertical = new ArrayList<>();
        ArrayList<Led> leftSide;
        ArrayList<Led> rightSide;
        int size = (leds.size()+1)/2;
        leftSide = new ArrayList<>(leds.subList(0, size));
        rightSide = new ArrayList<>(leds.subList(size, leds.size()));
        Zone rightZone = new Zone(leftSide);
        rightZone.setText("Left area");
        Zone leftZone = new Zone(rightSide);
        leftZone.setText("Right area");
        splitVertical.add(leftZone);
        splitVertical.add(rightZone);
        layouts.put(LedLayout.SPLIT_VERTICAL, splitVertical);
        
        ArrayList<Zone> monochromatic = new ArrayList<>();
        Zone unifiedZone = new Zone(leds);
        unifiedZone.setPreferredSize(new Dimension(200,30));
        unifiedZone.setText("Click to set a unique color");
        monochromatic.add(unifiedZone);
        layouts.put(LedLayout.MONO, monochromatic);
    }
}
