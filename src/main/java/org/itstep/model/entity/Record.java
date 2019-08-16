package org.itstep.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Record {

    private Long id;

    private Barber barber;

    private LocalDate localDate;

    private LocalTime localTime;

    private Client client;

    private Service service;

    private ServiceStatus status;

    public Service getService() {
        return service;
    }

    public Long getId() {
        return id;
    }

    public Barber getBarber() {
        return barber;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public Client getClient() {
        return client;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public static Record.Builder newBuilder() {
        return new Record().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Record.Builder setId(long barberId) {
            Record.this.id = barberId;

            return this;
        }

        public Record.Builder setBarber(Barber barber) {
            Record.this.barber = barber;

            return this;
        }

        public Record.Builder setService(Service service) {
            Record.this.service = service;

            return this;
        }

        public Record.Builder setDate(LocalDate localDate) {
            Record.this.localDate = localDate;

            return this;
        }

        public Record.Builder setTime(LocalTime localTime) {
            Record.this.localTime = localTime;

            return this;
        }

        public Record.Builder setClient(Client client) {
            Record.this.client = client;

            return this;
        }

        public Record.Builder setStatus(ServiceStatus status) {
            Record.this.status = status;

            return this;
        }


        public Record build() {
            return Record.this;
        }

    }
    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", localDate=" + localDate +
                ", localTime=" + localTime +
                ", status=" + status +
                '}';
    }
}
