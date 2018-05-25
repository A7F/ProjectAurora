package com.luke.projectaurora.core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Luke
 */
public class AppConfig {
    private static AppConfig instance;
    private String magicWord;
    private JSONObject json;
    private String language;
    private Integer ledAxisX;
    private Integer ledAxisY;
    private Integer ledNum;
    private Integer baudrate;
    private Integer macroKeys;
    private String defaultComPort;
    private Integer dataArraySize;
    private Integer displayRefreshRate;
    
    private AppConfig(){
        init();
    }
    
    public static AppConfig getInstance(){
        if(instance == null){
            instance = new AppConfig();
        }
        return instance;
    }

    private void init() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/config.json"));
            fill(obj);
        } catch (IOException | ParseException ex) {
            System.out.println(ex.toString());
        }
    }

    private void fill(Object obj) {
        Long temp;
        json = (JSONObject) obj;
        language = (String) json.get("language");
        magicWord = (String) json.get("magicWord");
        temp = (Long) json.get("ledAxisX");
        ledAxisX = temp.intValue();
        temp = (Long) json.get("ledAxisY");
        ledAxisY = temp.intValue();
        temp = (Long) json.get("macroKeys");
        macroKeys = temp.intValue();
        temp = (Long) json.get("baudrate");
        baudrate = temp.intValue();
        ledNum = ledAxisX + ledAxisY*2;
        defaultComPort = (String) json.get("defaultPort");
        temp = (Long) json.get("dataArraySize");
        dataArraySize = temp.intValue();
        temp = (Long) json.get("screenRefresh");
        displayRefreshRate = temp.intValue();
    }

    public String getMagicWord() {
        return magicWord;
    }

    public void setMagicWord(String magicWord) {
        json.put("magicWord", magicWord);
        this.magicWord = magicWord;
        update();
    }
    
    public void setBaudrate(Integer baudrate) {
        json.put("baudrate", baudrate);
        this.baudrate = baudrate;
        update();
    }

    public Integer getBaudrate() {
        return baudrate;
    }

    public String getLanguage() {
        return language;
    }

    public int getMacroKeys() {
        return macroKeys;
    }

    public void setLanguage(String language) {
        json.put("language", language);
        this.language = language;
        update();
    }

    public String getDefaultComPort() {
        return defaultComPort;
    }

    public void setDefaultComPort(String defaultComPort) {
        json.put("defaultPort", defaultComPort);
        this.defaultComPort = defaultComPort;
        update();
    }

    public int getLedNum() {
        return ledNum;
    }

    public int getDataArraySize() {
        return dataArraySize;
    }

    public void setDataArraySize(Integer dataArraySize) {
        json.put("dataArraySize", dataArraySize);
        this.dataArraySize = dataArraySize;
        update();
    }
    
    public void setMacroKeys(Integer numKeys) {
        json.put("macroKeys", numKeys);
        this.macroKeys = numKeys;
        update();
    }

    public int getDisplayRefreshRate() {
        return displayRefreshRate;
    }

    public int getLedAxisX() {
        return ledAxisX;
    }

    public int getLedAxisY() {
        return ledAxisY;
    }

    public void setLedAxisX(Integer ledAxisX) {
        json.put("ledAxisX", ledAxisX);
        this.ledAxisX = ledAxisX;
        update();
    }

    public void setLedAxisY(Integer ledAxisY) {
        json.put("ledAxisY", ledAxisY);
        this.ledAxisY = ledAxisY;
        update();
    }

    public void setDisplayRefreshRate(Integer displayRefreshRate) {
        json.put("screenRefresh", displayRefreshRate);
        this.displayRefreshRate = displayRefreshRate;
        update();
    }
    
    public void update(){
        try(FileWriter file = new FileWriter("src/main/resources/config.json")){
             file.write(json.toString());
             file.flush();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return json.toString();
    }
}
