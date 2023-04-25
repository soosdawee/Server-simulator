import controllers.InputController;
import views.InputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        InputController inputController = new InputController(inputView);
    }
}
