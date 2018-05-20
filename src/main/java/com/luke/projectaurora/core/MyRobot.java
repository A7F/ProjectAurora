package com.luke.projectaurora.core;

import java.awt.AWTException;
import java.awt.Robot;

/**
 *
 * @author Luke
 */
public class MyRobot{
    private Robot robot;
    
    public MyRobot(){
        try{
            robot = new Robot();
        }catch(AWTException ex){
            ex.toString();
        }
    }

    public void type(int keycode) {
        robot.keyPress(keycode);
        robot.keyRelease(keycode);
    }

    public void type(String s) {
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            int code = b;
            // gestisci solo tasti [A-Z] (ovvero ASCII dec[65-90])
            if (code > 96 && code < 123) {
                code = code - 32;
            }
            robot.delay(20);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }
    
    public Robot getRobot(){
        return robot;
    }
}
