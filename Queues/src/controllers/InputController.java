package controllers;

import views.InputView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputController {
    private InputView view;

    public InputController(InputView view) {
        this.view = view;

        this.view.addStartListener(new StartListener());
    }

    public class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
