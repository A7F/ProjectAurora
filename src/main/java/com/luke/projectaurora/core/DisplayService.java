package com.luke.projectaurora.core;

import com.luke.projectaurora.entities.DataVector;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import java.util.List;
import java.util.TimerTask;

/**
 * questa classe è il thread che si occupa di inviare al monitor i valori da mostrare sullo schermo
 * @author Luke
 */
public class DisplayService extends TimerTask{
    

    @Override
    public void run() {
        Components components = JSensors.get.components();
        DataVector data = DataVector.getInstance();
        List<Temperature> temps = components.cpus.get(0).sensors.temperatures;
        List<Load> loads = components.cpus.get(0).sensors.loads;
        Double d = temps.get(temps.size()-1).value;     //cpu temp
        Integer value = d.intValue();
        data.setVal2(value);
        d = loads.get(loads.size()-2).value;    //cpu load
        value = d.intValue();
        data.setVal1(value);
        d = loads.get(loads.size()-1).value;   //ram load
        value = d.intValue();
        data.setVal3(value);
        
        data.setVal4(0);
        SerialHandler.getInstance().sendData(data.getVector());
    }
}
