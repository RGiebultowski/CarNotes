package Activity;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Model danych auta

public class AutoData implements Serializable {

    //Model auta, brand jako marka auta
    private String model;
    private String brand;
    private String color;
    private List<TankUpRecord> tankUpRecord;

    public AutoData(String model, String brand, String color) {
        this.model = model;
        this.brand = brand;
        this.color = color;
        tankUpRecord = new ArrayList<>();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<TankUpRecord> getTankUpRecord() {
        return tankUpRecord;
    }

    public void setTankUpRecord(List<TankUpRecord> tankUpRecord) {
        this.tankUpRecord = tankUpRecord;
    }

    @NonNull
    @Override
    public String toString() {
        return model + " " + brand + " " + color;
    }
}
