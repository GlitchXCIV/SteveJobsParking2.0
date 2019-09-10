package sample;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Controller {
    @FXML
    private Pane boxIn;
    @FXML
    private Pane boxOut;
    @FXML
    private Pane bloccoPaga;
    @FXML
    private Pane boxmenu;
    @FXML
    private Label stampPopUp;
    @FXML
    private TextField input;
    @FXML
    private TextField output;
    @FXML
    private TextField payment;
    @FXML
    private Button car_button;
    @FXML
    private Button scooter_button;
    @FXML
    private Button truck_button;
    @FXML
    private Label typeStamp;
    @FXML
    private Label time;
    @FXML
    private Button back;
    @FXML
    public void initialize() {
        Timeline clock = new Timeline(
            new KeyFrame(
                Duration.ZERO,
                e -> {
                    int second = LocalDateTime.now().getSecond();
                    int minute = LocalDateTime.now().getMinute();
                    int hour = LocalDateTime.now().getHour();
                    int years = LocalDateTime.now().getYear();
                    int month = LocalDateTime.now().getMonthValue();
                    int day = LocalDateTime.now().getDayOfMonth();
                    time.setText(day + "/" + month + "/" + years + "\n" + hour + ":" + (minute) + ":" + second);
                }
            ),
            new KeyFrame(Duration.seconds(1))
        );

        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy \n HH:mm:ss");
    ParkingLot talete = new ParkingLot("Talete", 50);

    public void enter(javafx.event.ActionEvent actionEvent) {
        this.boxIn.visibleProperty().setValue(true);
        this.boxmenu.visibleProperty().setValue(false);
    }

    public void exit(javafx.event.ActionEvent actionEvent) {
        this.boxOut.visibleProperty().setValue(true);
        this.boxmenu.visibleProperty().setValue(false);
    }

    public void confirmInput(javafx.event.ActionEvent actionEvent) {
        try {
            Vehicle vehicle = new Vehicle(this.input.getText(), Vehicle.Kind.valueOf(this.typeStamp.getText()));
            Parking parking = new Parking(vehicle);
            if(talete.existVehicle(this.input.getText()) == "0") this.stampPopUp.setText(talete.addVehicle(vehicle));
            else this.stampPopUp.setText("Errore");
            this.boxIn.visibleProperty().setValue(false);
            this.boxmenu.visibleProperty().setValue(true);
            this.input.setText("");
            this.typeStamp.setText("");
            this.car_button.getStyleClass().removeAll("typeVehicle");
            this.truck_button.getStyleClass().removeAll("typeVehicle");
            this.scooter_button.getStyleClass().removeAll("typeVehicle");
        }
        catch (Exception e) {
            this.stampPopUp.setText("Errore");
        }
    }

    public void confirmOutput(javafx.event.ActionEvent actionEvent) {
        try {
            if(this.output.getText().equals("")) {
                this.stampPopUp.setText("Errore");
            }
            else if(talete.exitVehicle(this.output.getText()).equals("Errore")) {
                this.boxOut.visibleProperty().setValue(false);
                this.boxmenu.visibleProperty().setValue(true);
            }
            else {
                this.boxOut.visibleProperty().setValue(false);
                this.bloccoPaga.visibleProperty().setValue(true);
                this.stampPopUp.setText(talete.total());
                this.payment.setText(talete.getCash() + "");
                this.output.setText("");
            }
        }
        catch (Exception e) {
            this.stampPopUp.setText("Errore");
        }
        this.output.setText("");
    }

    public void restartMenu(javafx.event.ActionEvent actionEvent) {
        this.bloccoPaga.visibleProperty().setValue(false);
        this.boxmenu.visibleProperty().setValue(true);
        this.stampPopUp.setText(talete.total());
    }

    public void carButton(javafx.event.ActionEvent actionEvent) {
        this.car_button.getStyleClass().removeAll("typeVehicle");
        this.car_button.getStyleClass().add("typeVehicle");
        this.truck_button.getStyleClass().removeAll("typeVehicle");
        this.scooter_button.getStyleClass().removeAll("typeVehicle");
        this.typeStamp.setText("CAR");
    }
    public void scooterButton(javafx.event.ActionEvent actionEvent) {
        this.scooter_button.getStyleClass().removeAll("typeVehicle");
        this.scooter_button.getStyleClass().add("typeVehicle");
        this.car_button.getStyleClass().removeAll("typeVehicle");
        this.truck_button.getStyleClass().removeAll("typeVehicle");
        this.typeStamp.setText("SCOOTER");
    }
    public void truckBrutton(javafx.event.ActionEvent actionEvent) {
        this.truck_button.getStyleClass().removeAll("typeVehicle");
        this.truck_button.getStyleClass().add("typeVehicle");
        this.scooter_button.getStyleClass().removeAll("typeVehicle");
        this.car_button.getStyleClass().removeAll("typeVehicle");
        this.typeStamp.setText("TRUCK");
    }

    public void backmenu(javafx.event.ActionEvent actionEvent) {
        this.boxOut.visibleProperty().setValue(false);
        this.boxmenu.visibleProperty().setValue(true);
        this.stampPopUp.setText(talete.total());
    }

    public void backMenuIn(javafx.event.ActionEvent actionEvent) {
        this.boxIn.visibleProperty().setValue(false);
        this.boxmenu.visibleProperty().setValue(true);
        this.stampPopUp.setText(talete.total());
    }
}


