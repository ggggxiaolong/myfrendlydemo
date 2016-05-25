package com.example;

import javax.inject.Inject;

import dagger.Module;

@Module
class Thermosiphon implements Pump {

    private final Heater heater;

    @Inject
    Thermosiphon(Heater heater){
        this.heater = heater;
    }

    @Override
    public void pump() {
        if (heater.isHot()){
            System.out.println("=> => pumping => =>");
        }

    }
}
