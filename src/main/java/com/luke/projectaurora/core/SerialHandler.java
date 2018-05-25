package com.luke.projectaurora.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;

/**
 *
 * @author Luke
 */
public class SerialHandler {

    private ArrayList<String> ports = new ArrayList<>();
    private String portName;
    private SerialPort serialPort;
    private static SerialHandler instance;
    private AppConfig appConfig = AppConfig.getInstance();
    
    private SerialHandler(){
        scanSerialPort();
    }
    
    public static SerialHandler getInstance(){
        if(instance==null){
            instance = new SerialHandler();
        }
        return instance;
    }

    public final void scanSerialPort() {
        ports.clear();
        Collections.addAll(ports, SerialPortList.getPortNames());
    }

    public void sendData(String text) {

        if (portName == null) {
            //System.out.println("SENDING DATA: " + text);
            System.err.println("NESSUNA PORTA COM IMPOSTATA");
        } else {
            try {
                System.out.println("SENDING DATA: " + text);
                serialPort.writeString(text);

            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }

    }

    public void sendData(int[] array) {
        if (portName == null) {
            System.err.println("NESSUNA PORTA COM IMPOSTATA");
        } else {
            try {
                System.out.println("SENDING: " + Arrays.toString(array));
                serialPort.writeIntArray(array);
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void sendData(byte[] array) {
        if (portName == null) {
            System.err.println("NESSUNA PORTA COM IMPOSTATA");
        } else {
            try {
                System.out.println("SENDING: " + Arrays.toString(array));
                serialPort.writeBytes(array);
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }

    public ArrayList<String> getPorts() {
        return ports;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
        System.out.println("SET PORT: " + this.portName);
        openPort(portName);
    }

    public void openPort(String portName) {
        serialPort = new SerialPort(portName);
        
        try {
            System.out.println("===OPENING PORT===");
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE, false, true);
        } catch (SerialPortException ex) {
            System.out.println(ex.toString());
        }
        
        try {
            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
            serialPort.addEventListener(new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent spe) {
                    if(spe.isRXCHAR() && spe.getEventValue() > 0){
                        try {
                            String text = serialPort.readString();
                            if(text.contains(AppConfig.getInstance().getMagicWord())){
                                System.out.println("RICONOSCIUTO!");
                            }
                        } catch (SerialPortException ex) {
                            Logger.getLogger(SerialHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closePort() {
        try {
            serialPort.closePort();
            System.out.println("===SERIAL COM CLOSED===");
        } catch (SerialPortException ex) {
            System.out.println(ex.toString());
        }
    }
}
