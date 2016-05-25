package com.example;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = PumpModule.class)
class DripCoffeeModule {
    @Singleton
    @Provides
    Heater provideHeater() {
        return new ElectricHeater();
    }
}
