package com.luke.projectaurora.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Luke
 */
public class DataVector {

    private int[] data;
    private int size;
    private ArrayList<Integer> dataLed = new ArrayList<>();
    private static DataVector instance;

    private DataVector(int size) {
        this.size = size;
        this.data = new int[size];
        setAllZeros();
    }
    
    public static DataVector getInstance(){
        if(instance==null){
            instance = new DataVector(8);
        }
        return instance;
    }

    private void setAllZeros() {
        dataLed.removeAll(dataLed);
        if (!(dataLed.size() == size)) {
            for (int i = 0; i < size; i++) {
                dataLed.add(i, 0);
            }
        }
    }
    
    public void setSize(int size){
        this.size = size;
        this.data = new int[size];
        setAllZeros();
    }

    public void setCommandID(int id) {
        dataLed.set(0, id);
    }

    public void setConf(int config) {
        dataLed.set(1, config);
    }

    public void setBrightness(int brightness) {
        dataLed.set(2, brightness);
    }

    public void setFx(int fx) {
        dataLed.set(3, fx);
    }

    public void setLedId(int ledId) {
        setCommandID(0);
        dataLed.set(4, ledId);
    }

    public void setRGB(int r, int g, int b) {
        setCommandID(0);
        dataLed.set(5, r);
        dataLed.set(6, g);
        dataLed.set(7, b);
    }

    public void setRed(int r) {
        setCommandID(0);
        dataLed.set(5, r);
    }

    public void setGreen(int g) {
        setCommandID(0);
        dataLed.set(6, g);
    }

    public void setBlue(int b) {
        setCommandID(0);
        dataLed.set(7, b);
    }

    public void setVal(int val1, int val2, int val3, int val4) {
        setCommandID(1);
        dataLed.set(4, val1);
        dataLed.set(5, val2);
        dataLed.set(6, val3);
        dataLed.set(7, val4);
    }

    public void setVal1(int val1) {
        setCommandID(1);
        dataLed.set(4, val1);
    }

    public void setVal2(int val2) {
        setCommandID(1);
        dataLed.set(5, val2);
    }

    public void setVal3(int val3) {
        setCommandID(1);
        dataLed.set(6, val3);
    }

    public void setVal4(int val4) {
        setCommandID(1);
        dataLed.set(7, val4);
    }

    private void setDataLedVector() {
        Iterator<Integer> iterator = dataLed.iterator();
        for (int i = 0; i < data.length; i++) {
            data[i] = iterator.next();
        }
    }

    public int[] getVector() {
        setDataLedVector();
        return this.data;
    }

    public int getSize() {
        return this.size;
    }
    
    @Override
    public String toString(){
        setDataLedVector();
        return Arrays.toString(data);
    }
}
