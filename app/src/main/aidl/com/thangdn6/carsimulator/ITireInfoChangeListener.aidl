package com.thangdn6.carsimulator;
import com.thangdn6.carsimulator.model.Tire;


interface ITireInfoChangeListener {
    void onChange();
    void notify(String mes);
}