package com.thangdn6.carsimulator;
import com.thangdn6.carsimulator.ITireInfoChangeListener;
import com.thangdn6.carsimulator.model.Tire;

interface IServiceBinder {

    List<Tire> getListTires();

    void saveInfor(inout Tire tire);

    Tire getTireById(int id);

    void setTireInforChangeListener(ITireInfoChangeListener listener);
}