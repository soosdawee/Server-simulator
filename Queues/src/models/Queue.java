package models;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{
    private Integer id;
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingTime;
    private AtomicInteger totalWaitingTime;
    private AtomicInteger totalServiceTime;
    private AtomicInteger clientsProcessed;
    private AtomicBoolean running;

    public Queue () {
        this.id = Validator.generateQueueId();
        this.clients = new LinkedBlockingQueue<>();
        this.waitingTime = new AtomicInteger(0);
        this.totalWaitingTime = new AtomicInteger(0);
        this.clientsProcessed = new AtomicInteger(0);
        this.running = new AtomicBoolean(false);
        this.totalServiceTime = new AtomicInteger(0);
    }

    public AtomicInteger getTotalServiceTime() {
        return totalServiceTime;
    }

    public void setTotalServiceTime(AtomicInteger totalServiceTime) {
        this.totalServiceTime = totalServiceTime;
    }

    public Integer getId() {
        return id;
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void setClients(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(AtomicInteger waitingTime) {
        this.waitingTime = waitingTime;
    }

    public AtomicInteger getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public void setTotalWaitingTime(AtomicInteger totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }

    public AtomicInteger getClientsProcessed() {
        return clientsProcessed;
    }

    public void setClientsProcessed(AtomicInteger clientsProcessed) {
        this.clientsProcessed = clientsProcessed;
    }

    public AtomicBoolean getRunning() {
        return running;
    }

    public void setRunning(AtomicBoolean running) {
        this.running = running;
    }

    public void run() {
        while (running.get()) {
            try {
                if (clients.isEmpty()) {
                    continue;
                }

                Thread.sleep(1000);

                clients.peek().setProcessingTime(clients.peek().getProcessingTime() - 1);
                totalServiceTime.getAndIncrement();

                if (clients.peek().getProcessingTime().intValue() == 0) {
                    clientsProcessed.getAndIncrement();
                    clients.remove();
                }

                if (clients.size() > 1) {
                    totalWaitingTime.set(totalWaitingTime.get() + clients.size() - 1);
                }

            } catch (InterruptedException e) {
                System.out.println("Queue " + id + " interrupted");
            }
        }

        totalServiceTime.set(totalServiceTime.get() / clientsProcessed.get());
    }

    public void start() {
        running.set(true);
        Thread t = new Thread(this);
        t.start();
    }

    public void stop() {
        running.set(false);
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        if (clients.isEmpty()) {
            return "closed\n";
        }

        for (Client c : clients) {
            toReturn.append("( " + c.getId() + ", " + c.getArrivalTime() + ", " + c.getProcessingTime() + "); ");
        }

        toReturn.append("\n");

        return toReturn.toString();
    }

    public synchronized void addNewClient(Client client) {
        if (!this.running.get()) {
            start();
        }

        clients.add(client);

        clientsProcessed.getAndIncrement();
        waitingTime.addAndGet(client.getProcessingTime());
    }

    public Integer addProcessingTimes() {
        Integer s = 0;

        for (Client c : clients) {
            s += c.getProcessingTime();
        }

        return s;
    }
}
