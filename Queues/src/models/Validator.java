package models;

import exceptions.WrongInputException;

public class Validator {
    public static Integer clientId = 1;
    public static Integer queueId = 1;

    public static void validateInput(Integer numberOfClients, Integer numberOfQueues, Integer simulationInterval,
                                 Integer minArrivalTime, Integer maxArrivalTime, Integer minServiceTime, Integer maxServiceTime) throws WrongInputException {
        if (numberOfClients < 1 || numberOfQueues < 1 || minArrivalTime > maxArrivalTime || minServiceTime > maxServiceTime) {
            throw new WrongInputException("Wrong Input");
        }
    }

    public static Integer generateClientId() {
        return clientId++;
    }

    public static Integer generateQueueId() {
        return queueId++;
    }
}
