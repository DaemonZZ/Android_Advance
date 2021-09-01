package com.example.carsimulator;
import com.example.carsimulator.model.Tire;


interface ITireInfoChangeListener {
    void onChange();
    void notify(String mes);
}