package org.itstep.model.entity;
import java.util.Set;

public class Barber {

    private int id;

    private String firstName;

    private String lastName;

    private String photoUrl;

    private Set<Service> services;

    private BarberLevel barberLevel;

    private Set<Record> records;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Set<Service> getServices() {
        return services;
    }

    public BarberLevel getBarberLevel() {
        return barberLevel;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public static Barber.Builder newBuilder() {
        return new Barber().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Barber.Builder setBarberId(int barberId) {
            Barber.this.id = barberId;

            return this;
        }

        public Barber.Builder setFirstName(String firstName) {
            Barber.this.firstName = firstName;

            return this;
        }

        public Barber.Builder setLastName(String lastName) {
            Barber.this.lastName = lastName;

            return this;
        }

        public Barber.Builder setPhotoUrl(String photoUrl) {
            Barber.this.photoUrl = photoUrl;

            return this;
        }

        public Barber.Builder setServices(Set<Service> services) {
            Barber.this.services = services;

            return this;
        }

        public Barber.Builder setRecords(Set<Record> records) {
            Barber.this.records = records;

            return this;
        }

        public Barber.Builder setBarberLevel(BarberLevel barberLevel) {
            Barber.this.barberLevel = barberLevel;

            return this;
        }

        public Barber build() {
            return Barber.this;
        }

    }
}
