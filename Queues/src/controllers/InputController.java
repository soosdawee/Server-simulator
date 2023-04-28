package controllers;

import exceptions.WrongInputException;
import models.Client;
import models.SimulationManager;
import models.Validator;
import views.InputView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.*;

public class InputController {
    private InputView view;

    public InputController(InputView view) {
        this.view = view;

        this.view.addStartListener(new StartListener());
    }

    public class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try  {
                PrintStream out = new PrintStream("test3.txt");
                System.setOut(out);

                Integer numberOfClients = Integer.parseInt(view.getNrOfClients());
                Integer numberOfQueues = Integer.parseInt(view.getNrOfQueues());
                Integer simulationInterval = Integer.parseInt(view.getSimulationInterval());
                Integer minArrivalTime = Integer.parseInt(view.getMinArrivalTime());
                Integer maxArrivalTime = Integer.parseInt(view.getMaxArrivalTime());
                Integer minServiceTime = Integer.parseInt(view.getMinServiceTime());
                Integer maxServiceTime = Integer.parseInt(view.getMaxServiceTime());

                Validator.validateInput(numberOfClients, numberOfQueues, simulationInterval, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);

                SimulationManager simulationManager = new SimulationManager(numberOfClients, numberOfQueues, simulationInterval, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, view);

                Thread t = new Thread(simulationManager);
                t.start();

            } catch (IOException ex) {
                System.out.println("Unable to print to file");
            } catch (WrongInputException wr) {
                view.showErrorMessage("Wrong Input!");
            } catch (NumberFormatException nr) {
                view.showErrorMessage("All the fields must be filled out!");
            }
        }
    }
}
