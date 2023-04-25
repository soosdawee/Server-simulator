package models;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulationManager {
    private List<Client> clients;

    public SimulationManager() {}

    public List<Client> generateRandomClients(Integer nrOfClients) {
        List<Client> clients = new CopyOnWriteArrayList<>();
        int a = 20, b = 10;

        Random random = new Random();

        for (int i = 0; i < nrOfClients; i++) {
            Integer arrivalTime = random.nextInt(a - b + 1) + 10;
            Integer serviceTime = random.nextInt(a - b + 1) + 10;

            Client client = new Client(arrivalTime, serviceTime);

            clients.add(client);
        }

        clients.sort(Client::compareTo);
        return clients;
    }
}
