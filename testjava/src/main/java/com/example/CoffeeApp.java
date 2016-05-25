package com.example;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

public class CoffeeApp {

    @Singleton
    @Component(modules = {DripCoffeeModule.class})
    public interface Coffee {
        CoffeeMaker make();
    }

    public static void main(String[] args){
        Coffee coffee = DaggerCoffeeApp_Coffee.builder().build();
        coffee.make().brew();
    }

}