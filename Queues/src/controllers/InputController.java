package controllers;

import models.Client;
import models.SimulationManager;
import views.InputView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InputController {
    private InputView view;

    public InputController(InputView view) {
        this.view = view;

        this.view.addStartListener(new StartListener());
    }

    public class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer numberOfClients = Integer.parseInt(view.getNrOfClients());
            Integer numberOfQueues = Integer.parseInt(view.getNrOfQueues());
            Integer simulationInterval = Integer.parseInt(view.getSimulationInterval());
            Integer minArrivalTime = Integer.parseInt(view.getMinArrivalTime());
            Integer maxArrivalTime = Integer.parseInt(view.getMaxArrivalTime());
            Integer minServiceTime = Integer.parseInt(view.getMinServiceTime());
            Integer maxServiceTime = Integer.parseInt(view.getMaxServiceTime());

            SimulationManager simulationManager = new SimulationManager(numberOfClients, numberOfQueues, simulationInterval, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, view);

            Thread t = new Thread(simulationManager);
            t.start();
        }
    }
}
