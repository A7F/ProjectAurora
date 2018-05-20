package com.luke.projectaurora.Tests;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import java.util.List;

/**
 *
 * @author Luke
 */
public class TestJsensor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Components components = JSensors.get.components();

        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus) {
                System.out.println("Found CPU component: " + cpu.name);
                if (cpu.sensors != null) {
                    System.out.println("Sensors: ");

                    //Print temperatures
                    List<Temperature> temps = cpu.sensors.temperatures;
                    
                    for (final Temperature temp : temps) {
                        System.out.println(temp.name + ": " + temp.value + " C");
                    }

                    //Print cpu loads
                    List<Load> loads = cpu.sensors.loads;
                    
                    for (final Load load : loads) {
                        System.out.println(load.name+": "+load.value);
                    }
                }
            }
        }
        
        List<Gpu> gpus = components.gpus;
        if(gpus!=null){
            for(final Gpu gpu : gpus){
                System.out.println(gpu.name);
                List<Load> loads = gpu.sensors.loads;
                    
                    for (final Load load : loads) {
                        System.out.println(load.name+": "+load.value);
                    }
            }
        }
    }

}
