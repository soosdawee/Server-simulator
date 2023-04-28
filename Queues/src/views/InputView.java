package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputView extends JFrame{
    private JTextField nrOfClients;
    private JTextField nrOfQueues;
    private JTextField simulationInterval;
    private JTextField minArrivalTime;
    private JTextField maxArrivalTime;
    private JTextField minServiceTime;
    private JTextField maxServiceTime;

    private JButton startButton;

    private JTextArea situation;

    public InputView () {
        this.setBounds(100, 100, 1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel nrClientsLabel = new JLabel("Number of clients");
        nrClientsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nrClientsLabel.setBounds(30, 20, 100, 30);
        this.getContentPane().add(nrClientsLabel);

        nrOfClients = new JTextField();
        nrOfClients.setBounds(30, 50, 100, 25);
        this.getContentPane().add(nrOfClients);
        nrOfClients.setColumns(10);

        JLabel nrQueuesLabel = new JLabel("Number of queues");
        nrQueuesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        nrQueuesLabel.setBounds(150, 20, 100, 30);
        this.getContentPane().add(nrQueuesLabel);

        nrOfQueues = new JTextField();
        nrOfQueues.setBounds(150, 50, 100, 25);
        this.getContentPane().add(nrOfQueues);
        nrOfQueues.setColumns(10);

        JLabel simulationIntervalLabel = new JLabel("Simulation interval");
        simulationIntervalLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        simulationIntervalLabel.setBounds(270, 20, 100, 30);
        this.getContentPane().add(simulationIntervalLabel);

        simulationInterval = new JTextField();
        simulationInterval.setBounds(270, 50, 100, 25);
        this.getContentPane().add(simulationInterval);
        simulationInterval.setColumns(10);

        JLabel minArrivalTimeLabel = new JLabel("Min arrival time");
        minArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        minArrivalTimeLabel.setBounds(390, 20, 100, 30);
        this.getContentPane().add(minArrivalTimeLabel);

        minArrivalTime = new JTextField();
        minArrivalTime.setBounds(390, 50, 100, 25);
        this.getContentPane().add(minArrivalTime);
        minArrivalTime.setColumns(10);

        JLabel maxArrivalTimeLabel = new JLabel("Max arrival time");
        maxArrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        maxArrivalTimeLabel.setBounds(510, 20, 100, 30);
        this.getContentPane().add(maxArrivalTimeLabel);

        maxArrivalTime = new JTextField();
        maxArrivalTime.setBounds(510, 50, 100, 25);
        this.getContentPane().add(maxArrivalTime);
        maxArrivalTime.setColumns(10);

        JLabel minServiceTimeLabel = new JLabel("Min service time");
        minServiceTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        minServiceTimeLabel.setBounds(630, 20, 100, 30);
        this.getContentPane().add(minServiceTimeLabel);

        minServiceTime = new JTextField();
        minServiceTime.setBounds(630, 50, 100, 25);
        this.getContentPane().add(minServiceTime);
        minServiceTime.setColumns(10);

        JLabel maxServiceTimeLabel = new JLabel("Max service time");
        maxServiceTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        maxServiceTimeLabel.setBounds(750, 20, 100, 30);
        this.getContentPane().add(maxServiceTimeLabel);

        maxServiceTime = new JTextField();
        maxServiceTime.setBounds(750, 50, 100, 25);
        this.getContentPane().add(maxServiceTime);
        maxServiceTime.setColumns(10);

        startButton = new JButton("START");
        startButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        startButton.setBounds(870, 50, 100, 25);
        this.getContentPane().add(startButton);

        situation = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(situation);
        scrollPane.setBounds(45, 100, 900, 650);
        situation.setLineWrap(true);
        this.getContentPane().add(scrollPane);

        this.setVisible(true);
    }

    public String getNrOfClients() {
        return nrOfClients.getText();
    }

    public void setNrOfClients(JTextField nrOfClients) {
        this.nrOfClients = nrOfClients;
    }

    public String getNrOfQueues() {
        return nrOfQueues.getText();
    }

    public void setNrOfQueues(JTextField nrOfQueues) {
        this.nrOfQueues = nrOfQueues;
    }

    public String getSimulationInterval() {
        return simulationInterval.getText();
    }

    public void setSimulationInterval(JTextField simulationInterval) {
        this.simulationInterval = simulationInterval;
    }

    public String getMinArrivalTime() {
        return minArrivalTime.getText();
    }

    public void setMinArrivalTime(JTextField minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public String getMaxArrivalTime() {
        return maxArrivalTime.getText();
    }

    public void setMaxArrivalTime(JTextField maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public String getMinServiceTime() {
        return minServiceTime.getText();
    }

    public void setMinServiceTime(JTextField minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public String getMaxServiceTime() {
        return maxServiceTime.getText();
    }

    public void setMaxServiceTime(JTextField maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public String getSituation() {
        return situation.getText();
    }

    public void setSituation(String situation) {
        this.situation.setText(situation);
    }

    public void addStartListener (ActionListener action) {
        startButton.addActionListener(action);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ALERT", JOptionPane.ERROR_MESSAGE);
    }
}
