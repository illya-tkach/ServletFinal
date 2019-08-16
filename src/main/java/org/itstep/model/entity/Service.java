package org.itstep.model.entity;
import java.util.Set;

public class Service {

    private int id;

    private String serviceName;

    private int serviceCost;

    private Set<Barber> barbers;

    public int getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public Set<Barber> getBarbers() {
        return barbers;
    }

    public static Service.Builder newBuilder() {
        return new Service().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Service.Builder setId(int serviceId) {
            Service.this.id = serviceId;

            return this;
        }

        public Service.Builder setName(String serviceName) {
            Service.this.serviceName = serviceName;

            return this;
        }

        public Service.Builder setCost(int serviceCost) {
            Service.this.serviceCost = serviceCost;

            return this;
        }

        public Service.Builder setBarbers(Set<Barber> barbers) {
            Service.this.barbers = barbers;

            return this;
        }


        public Service build() {
            return Service.this;
        }

    }
}
