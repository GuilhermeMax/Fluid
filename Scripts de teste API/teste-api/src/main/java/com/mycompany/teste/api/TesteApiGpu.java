/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.teste.api;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;

import java.util.List;

/**
 *
 * @author fehig
 */
public class TesteApiGpu {

    public static void main(String[] args) {
        Components components = JSensors.get.components();

        List<Gpu> gpus = components.gpus;

        List<Cpu> cpus = components.cpus;

        // Exibir informações da GPU
        if (cpus != null) {
            System.out.println("---- GPU ----");
            for (final Gpu gpu : gpus) {
                System.out.println("Found GPU component: " + gpu.name);
                if (gpu.sensors != null) {
                    System.out.println("Sensors: ");

                    //Print temperatures
                    List<Temperature> temps = gpu.sensors.temperatures;
                    System.out.println(temps.get(temps.size() - 1).name + ": " + temps.get(temps.size() - 1).value + " C");
                }
            }
        }
        
        // Exibir informações da CPU
        if (gpus != null) {
            System.out.println("\n---- CPU ----");
            for (final Cpu cpu : cpus) {
                System.out.println("Found CPU component: " + cpu.name);
                if (cpu.sensors != null) {
                    System.out.println("Sensors: ");

                    //Print temperatures
                    List<Temperature> temps = cpu.sensors.temperatures;

                    System.out.println(temps.get(temps.size() - 1).name + ": " + temps.get(temps.size() - 1).value + " C");
                }
            }
        }

    }

}
