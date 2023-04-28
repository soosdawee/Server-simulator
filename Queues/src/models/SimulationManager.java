package models;

import views.InputView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulationManager implements Runnable{
    private Integer numberOfClients;
    private Integer numberOfQueues;
    private Integer simulationInterval;
    private Integer minArrivalTime;
    private Integer maxArrivalTime;
    private Integer minServiceTime;
    private Integer maxServiceTime;
    private Scheduler scheduler;
    private List<Client> clients;
    private InputView inputView;
    private Integer averageWaitingTime;

    public SimulationManager(Integer numberOfClients, Integer numberOfQueues, Integer simulationInterval,
                             Integer minArrivalTime, Integer maxArrivalTime, Integer minServiceTime, Integer maxServiceTime, InputView inputView) {
        this.numberOfClients = numberOfClients;
        this.numberOfQueues = numberOfQueues;
        this.simulationInterval = simulationInterval;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.scheduler = new Scheduler(createQueues(numberOfQueues), numberOfQueues);
        this.clients = generateRandomClients(numberOfClients, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
        this.inputView = inputView;
        this.averageWaitingTime = 0;
    }

    public List<Client> generateRandomClients(Integer nrOfClients, Integer minArrivalTime, Integer maxArrivalTime,
                                              Integer minServiceTime, Integer maxServiceTime) {
        List<Client> clients = new CopyOnWriteArrayList<>();
        int a = 20, b = 10;

        Random random = new Random();

        for (int i = 0; i < nrOfClients; i++) {
            Integer arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            Integer serviceTime = random.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;

            Client client = new Client(arrivalTime, serviceTime);

            clients.add(client);
        }

        clients.sort(Client::compareTo);
        return clients;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public List<Queue> createQueues(Integer numberOfQueues) {
        List<Queue> toReturn = new LinkedList<>();

        for (int i = 0; i < numberOfQueues; i++) {
            toReturn.add(new Queue());
        }

        return toReturn;
    }

    @Override
    public void run() {
        Integer max = 0, peakTime = 0;

        while (scheduler.getTime() < simulationInterval && (!clients.isEmpty() || !scheduler.queuesEmpty())) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Sleep not working");
            }

            scheduler.incrementTime();

            scheduler.dispatchClients(clients);

            if (scheduler.clientsInQueues() > max) {
                max = scheduler.clientsInQueues();
                peakTime = scheduler.getTime();
            }

            inputView.setSituation("Time " + scheduler.getTime()  + ":\nWaiting: " + showWaitingClients() + scheduler.showQueues());
            System.out.println("Time " + scheduler.getTime()  + ":\nWaiting: " + showWaitingClients() + scheduler.showQueues());
            System.out.println("-----------------------------\n");
        }

        averageWaitingTime /= numberOfClients;

        inputView.setSituation("Time " + scheduler.getTime()  + ":\nWaiting: " + showWaitingClients() + scheduler.showQueues()
            + "\nAvg waiting time: " + scheduler.averageWaitingTime(numberOfClients) + "\nAvg service time: " + scheduler.averageServiceTime() +
                "\nPeak time: " + peakTime);

        System.out.println("Time " + scheduler.getTime()  + ":\nWaiting: " + showWaitingClients() + scheduler.showQueues()
                + "\nAvg waiting time: " + scheduler.averageWaitingTime(numberOfClients) + "\nAvg service time: " + scheduler.averageServiceTime() +
                "\nPeak time: " + peakTime);

        scheduler.stopQueues();
    }

    public String showWaitingClients() {
        StringBuilder toReturn = new StringBuilder();

        for (Client client : clients) {
            toReturn.append("(");
            toReturn.append(client.getId());
            toReturn.append(", ");
            toReturn.append(client.getArrivalTime());
            toReturn.append(", " + client.getProcessingTime());
            toReturn.append("); ");
        }

        toReturn.append("\n");
        return toReturn.toString();
    }
}


