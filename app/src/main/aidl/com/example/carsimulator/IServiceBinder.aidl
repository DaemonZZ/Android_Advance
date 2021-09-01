package com.example.carsimulator;
import com.example.carsimulator.ITireInfoChangeListener;
import com.example.carsimulator.model.Tire;

interface IServiceBinder {

    List<Tire> getListTires();

    void saveInfor(inout Tire tire);

    Tire getTireById(int id);

    void setTireInforChangeListener(ITireInfoChangeListener listener);
}