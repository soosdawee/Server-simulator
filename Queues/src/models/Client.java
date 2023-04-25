package models;

import java.util.Comparator;

public class Client implements Comparable<Client> {
    private Integer id;
    private Integer arrivalTime;
    private Integer processingTime;
    private Integer waitingPeriod;

    public Client(Integer arrivalTime, Integer processingTime) {
        this.id = Validator.generateClientId();
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
        this.waitingPeriod = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Integer arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Integer processingTime) {
        this.processingTime = processingTime;
    }

    public Integer getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(Integer waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public void incrementWaitingPeriod() {
        this.waitingPeriod++;
    }

    public void decrementWaitingPeriod() {
        this.waitingPeriod--;
    }

    @Override
    public int compareTo(Client client) {
        if (this.arrivalTime.equals(client.arrivalTime)) {
            return this.processingTime - client.processingTime;
        }
        return this.arrivalTime - client.arrivalTime;
    }
}
