package models;

public class Validator {
    public static Integer clientId = 1;
    public static Integer queueId = 1;

    public Boolean validateInput(Integer numberOfClients, Integer numberOfQueues, Integer simulationInterval,
                                 Integer minArrivalTime, Integer maxArrivalTime, Integer minServiceTime, Integer maxServiceTime) {
        return true;
    }

    public static Integer generateClientId() {
        return clientId++;
    }

    public static Integer generateQueueId() {
        return queueId++;
    }
}
