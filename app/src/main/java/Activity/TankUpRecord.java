package Activity;


import java.io.Serializable;
import java.util.Date;

public class TankUpRecord implements Serializable {

    private Date tankUpDate;
    private Integer mileage;
    private Integer liters;
    private Integer costPLN;

    public TankUpRecord(Date tankUpDate, Integer mileage, Integer liters, Integer costPLN) {
        this.tankUpDate = tankUpDate;
        this.mileage = mileage;
        this.liters = liters;
        this.costPLN = costPLN;
    }

    public Date getTankUpDate() {
        return tankUpDate;
    }

    public void setTankUpDate(Date tankUpDate) {
        this.tankUpDate = tankUpDate;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getLiters() {
        return liters;
    }

    public void setLiters(Integer liters) {
        this.liters = liters;
    }

    public Integer getCostPLN() {
        return costPLN;
    }

    public void setCostPLN(Integer costPLN) {
        this.costPLN = costPLN;
    }
}
