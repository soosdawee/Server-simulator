package models;

import java.util.List;

public class Scheduler {
    private List<Queue> queues;
    private Integer maxNumberOfQueues;
    private Integer time;

    public Scheduler(List<Queue> queues, Integer maxNumberOfQueues) {
        this.queues = queues;
        this.maxNumberOfQueues = maxNumberOfQueues;
        this.time = -1;
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }

    public Integer getMaxNumberOfQueues() {
        return maxNumberOfQueues;
    }

    public void setMaxNumberOfQueues(Integer maxNumberOfQueues) {
        this.maxNumberOfQueues = maxNumberOfQueues;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getTime() {
        return time;
    }

    public void incrementTime() {
        time++;
    }

    public String showQueues() {
        StringBuilder toReturn = new StringBuilder();

        for (int i = 0; i < queues.size(); i++) {
            toReturn.append("Queue " + (i + 1) + ": "+ queues.get(i).toString());
        }

        return toReturn.toString();
    }

    public void dispatchClients(List<Client> clients) {
        for (Client c : clients) {
            if (time < c.getArrivalTime()) {
                break;
            }

            queues.get(findMinQueueIndex()).addNewClient(c);
            clients.remove(c);
        }
    }

    public Integer findMinQueueIndex() {
        Integer min = Integer.MAX_VALUE;
        Integer id = 0;

        for (Queue q : queues) {
            try {
                if (q.getClients().isEmpty()) {
                    return q.getId() - 1;
                }

                if (q.addProcessingTimes() < min) {
                    min = q.addProcessingTimes();
                    id = q.getId() - 1;
                }
            } catch (NullPointerException e) {
                return q.getId() - 1;
            }
        }

        return id;
    }

    public Boolean queuesEmpty() {
        for (int i = 0; i < queues.size(); i++) {
            if (!queues.get(i).getClients().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public void stopQueues() {
        for (Queue q : queues) {
            if (q.getRunning().get()) {
                q.stop();
            }
        }
    }

    public Integer clientsInQueues() {
        Integer s = 0;

        for (Queue q : queues) {
            s += q.getClients().size();
        }

        return s;
    }

    public Float averageWaitingTime(Integer numberOfClients) {
        Float s = 0f;

        for (Queue q : queues) {
            s += q.getTotalWaitingTime().get();
        }

        s /= numberOfClients;

        return s;
    }

    public Float averageServiceTime() {
        Float s = 0f;

        for (Queue q : queues) {
            s += q.getTotalServiceTime().get();
//            System.out.println("Service time: " + q.getTotalServiceTime().get());
        }

        s /= maxNumberOfQueues;

        return s;
    }
}
