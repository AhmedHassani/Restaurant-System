package bc_cashsir.Layout;

import bc_cashsir.BC_cashsir;
import bc_cashsir.Data;
import bc_cashsir.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 *
 * @author ahmed rhman hassani
 */
public class MainViewController implements Initializable {

    boolean var_full_seraen = false;
    boolean nav_state = false;
    @FXML
    private BorderPane main_layou_program;
    @FXML
    private VBox nav;
    @FXML
    private Label lab1;

    @FXML
    private Label lab2;
    ArrayList<Label> listOfLabel;
    private boolean isSiled = false;
    @FXML
    private Label lab4;
    @FXML
    private Label lab5;
    @FXML
    private Label lab6;
    @FXML
    private Label lab7;
    @FXML
    private Label lab8;
    @FXML
    private Label lab9;
    @FXML
    private HBox bar_mnue;
    @FXML
    private Pane pane_login;
    @FXML
    private TextField user;
    @FXML
    private PasswordField pass;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image2 = new Image("bc_cashsir/Img/bg.jpg");
        BackgroundSize bSize = new BackgroundSize(800, 700, true, true, true, true);
        Background background2 = new Background(new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bSize));
        bar_mnue.setVisible(false);
        nav.setVisible(false);
        listOfLabel = new ArrayList<Label>();
        listOfLabel.add(lab1);
        listOfLabel.add(lab2);
        listOfLabel.add(lab4);
        listOfLabel.add(lab5);
        listOfLabel.add(lab6);
        listOfLabel.add(lab7);
        listOfLabel.add(lab8);
        listOfLabel.add(lab9);
        main_layou_program.setBackground(background2);

        this.VisableLabel(listOfLabel, false);
    }

    @FXML
    private void Minimize(MouseEvent event) {
        Stage window = (Stage) main_layou_program.getScene().getWindow();
        window.setIconified(true);
    }

    @FXML
    private void CloseProgram(MouseEvent event) {
        boolean formatGotIt;
        ButtonType yes = new ButtonType("نعم", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("رجوع", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.NONE, "هل تريد اغلاق البرنامج ؟",
                yes,
                no);
        alert.initModality(Modality.WINDOW_MODAL);

        alert.setTitle("اغلاق البرنامج");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {

            Stage window = (Stage) main_layou_program.getScene().getWindow();
            System.exit(0);
            window.close();
        }

    }

    public void myDia(String title, String msg) {
        Dialog dialog = new Dialog();
        dialog.setTitle(title);
        dialog.setContentText(msg);

        ButtonType cansal = new ButtonType("نعم");
        dialog.getDialogPane().getButtonTypes().add(cansal);
        dialog.show();

    }

    @FXML
    private void FullScreenProgram(MouseEvent event) {
        Stage window = (Stage) main_layou_program.getScene().getWindow();
        if (var_full_seraen == false) {
            var_full_seraen = true;

            window.setFullScreen(var_full_seraen);
        } else {
            var_full_seraen = false;

            window.setFullScreen(var_full_seraen);
        }

    }

    @FXML
    private void MakeNavgeter(MouseEvent event) {
        if (nav_state == false) {
            nav_state = true;
            nav.setPrefWidth(226);
            lab1.setVisible(nav_state);
            this.VisableLabel(listOfLabel, nav_state);

        } else {
            nav_state = false;
            nav.setPrefWidth(50);
            lab1.setVisible(nav_state);
            this.VisableLabel(listOfLabel, nav_state);
        }

    }

    public void VisableLabel(ArrayList<Label> listOfLabel, boolean nav_state) {
        for (int i = 0; i < listOfLabel.size(); i++) {
            (listOfLabel.get(i)).setVisible(nav_state);
        }
    }

    @FXML
    private void setViewChseir(MouseEvent event) {

        try {
            Node rootChaseir = FXMLLoader.load(getClass().getResource("ChseirView.fxml"));

            main_layou_program.setCenter(rootChaseir);

        } catch (IOException ex) {
            System.out.println("Eroor in View Load Chasrir !");
        }
    }

    @FXML
    private void setViewAdd(MouseEvent event) {
        try {
            Node rootChaseir = FXMLLoader.load(getClass().getResource("AddItemView.fxml"));

            main_layou_program.setCenter(rootChaseir);

        } catch (IOException ex) {
            System.out.println("Eroor in View Load Chasrir !");
        }
    }

    @FXML
    private void setConterlView(MouseEvent event) {
        try {
            Node rootChaseir = FXMLLoader.load(getClass().getResource("CashirControl.fxml"));

            main_layou_program.setCenter(rootChaseir);

        } catch (IOException ex) {
            System.out.println("Eroor in View Load Chasrir !");
        }
    }

    @FXML
    private void login(MouseEvent event) throws SQLException {
        String username = user.getText();
        String password = pass.getText();
        Model model = new Model();
        ArrayList<Data.Admin> data = model.getAdmins();
        System.out.println(data.get(0).user);
        System.out.println(data.get(0).password);
        for (int i = 0; i < data.size(); i++) {
            if ((username.equals(data.get(i).user))
                    && (password.equals(data.get(i).password))) {
                bar_mnue.setVisible(true);
                nav.setVisible(true);
                pane_login.setVisible(false);
                main_layou_program.setBackground(Background.EMPTY);

            } else {

            }
        }

    }

    @FXML
    private void EXIT(MouseEvent event) {

        Stage window = (Stage) main_layou_program.getScene().getWindow();
        System.exit(0);
        window.close();
    }

    @FXML
    private void Logout(MouseEvent event) {
        Image image2 = new Image("bc_cashsir/Img/bg.jpg");
        BackgroundSize bSize = new BackgroundSize(800, 700, true, true, true, true);
        Background background2 = new Background(new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bSize));
        main_layou_program.setCenter(pane_login);
        user.setText("");
        pass.setText("");
        bar_mnue.setVisible(false);
        nav.setVisible(false);
        pane_login.setVisible(true);

        main_layou_program.setBackground(background2);

    }

    @FXML
    private void GoSetting(MouseEvent event) {
        CheckAdmin();

      

    }
    
    
        public void CheckAdmin() {

        Dialog dialog = new Dialog();
        dialog.setTitle("تاكيد دخول");
        ButtonType Editing = new ButtonType("دخول", ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().add(Editing);

        GridPane grid = new GridPane();
        TextField name = new TextField();
        name.setPrefHeight(35);
        name.setStyle(""
                + "-fx-padding:5px;"
                + "-fx-border-insets:5px;"
                + "-fx-background-insets:5px");
        PasswordField number = new PasswordField();
        number.setPrefHeight(35);
        number.setStyle(""
                + "-fx-padding:5px;"
                + "-fx-border-insets:5px;"
                + "-fx-background-insets:5px");

        grid.add(new Label("اسم المستخدم"), 1, 0);
        grid.add(name, 0, 0);
        
        grid.add(number, 0, 1);
        grid.add(new Label("رقم السري"), 1, 1);
        

        dialog.getDialogPane().setContent(grid);

        dialog.show();
        dialog.setResultConverter(dialogButton -> {
        String username = name.getText();
        String password = number.getText();
        Model model = new Model();
        ArrayList<Data.Admin> data = null;
            try {
                data = model.getAdmins();
            } catch (SQLException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println(data.get(0).user);
        System.out.println(data.get(0).password);
        for (int i = 0; i < data.size(); i++) {
            if ((username.equals(data.get(i).user))
                 && (password.equals(data.get(i).password))
                 && ("1".equals(data.get(i).isadmin+""))
                    ) {
                bar_mnue.setVisible(true);
                nav.setVisible(true);
                pane_login.setVisible(false);
                main_layou_program.setBackground(Background.EMPTY);
                Node rootChaseir;
                try {
                    rootChaseir = FXMLLoader.load(getClass().getResource("Setting.fxml"));
                    main_layou_program.setCenter(rootChaseir);
                } catch (IOException ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
             
            }
        }
            return true;
        });

    }

}
