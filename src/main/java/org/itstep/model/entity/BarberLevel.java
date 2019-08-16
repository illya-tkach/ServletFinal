package org.itstep.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


public class BarberLevel {

    private int id;


    private String levelName;


    private double serviceCostCoef;

    @JsonIgnore
    private List<Barber> barbers;

    public int getId() {
        return id;
    }

    public String getLevelName() {
        return levelName;
    }

    public double getServiceCostCoef() {
        return serviceCostCoef;
    }

    public List<Barber> getBarbers() {
        return barbers;
    }

    public static BarberLevel.Builder newBuilder() {
        return new BarberLevel().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public BarberLevel.Builder setLevelId(int barberId) {
            BarberLevel.this.id = barberId;

            return this;
        }

        public BarberLevel.Builder setLevelName(String levelName) {
            BarberLevel.this.levelName = levelName;

            return this;
        }

        public BarberLevel.Builder setServiceCostCoef(double serviceCostCoef) {
            BarberLevel.this.serviceCostCoef = serviceCostCoef;

            return this;
        }

        public BarberLevel.Builder setBarbers(List<Barber> barbers) {
            BarberLevel.this.barbers = barbers;

            return this;
        }


        public BarberLevel build() {
            return BarberLevel.this;
        }

    }
}
