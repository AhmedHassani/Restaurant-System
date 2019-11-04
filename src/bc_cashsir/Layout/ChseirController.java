/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_cashsir.Layout;

import bc_cashsir.Data;
import bc_cashsir.Model;
import bc_cashsir.PrintReceipt;
import com.jfoenix.controls.JFXListView;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.print.PrintService;
import javax.swing.event.DocumentEvent;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ChseirController implements Initializable {

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private GridPane grid_food;
    @FXML
    private TableView<Order> TABLE_ORDER;
    @FXML
    private TableColumn<Order, String> cost_m;
    @FXML
    private TableColumn<Order, String> number_m;
    @FXML
    private TableColumn<Order, String> name_m;
    public ArrayList<Order> list;
    @FXML
    private AnchorPane MAIN_PARINT;
    private Pane PANE_TABLE;
    @FXML
    private JFXListView<String> slistview;
    @FXML
    private Label t_price;
    @FXML
    private TextField add_price;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            list = new ArrayList<Order>();
            ArrayList<Data.Groub> list_set = new Model().getGroubFood();
            for (int i = 0; i < list_set.size(); i++) {
                slistview.getItems().addAll(list_set.get(i).name);
            }
            AddChMat(list_set.get(0).name);
        } catch (SQLException ex) {
            Logger.getLogger(ChseirController.class.getName()).log(Level.SEVERE, null, ex);
        }
        slistview.setOnMouseClicked((e) -> {
            String item = slistview.getSelectionModel().getSelectedItem().toString();
            try {
                grid_food.getChildren().clear();
                this.AddChMat(item);
            } catch (SQLException ex) {
                Logger.getLogger(ChseirController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void AddChMat() throws SQLException {
        ArrayList<Data.Materil> mat = new Model().getMt();
        int k = 0;
        int index = 0;
        for (int i = 0; i < mat.size(); i++) {
            AddNewFoodOrder(mat.get(i).name, Double.parseDouble(mat.get(i).price), k, index);
            k = k + 1;
            if (k == 4) {
                index = index + 1;
                k = 0;
            }
        }

    }

    public void AddChMat(String type) throws SQLException {
        ArrayList<Data.Materil> mat = new Model().getMt(type);
        int k = 0;
        int index = 0;
        for (int i = 0; i < mat.size(); i++) {
            AddNewFoodOrder(mat.get(i).name, Double.parseDouble(mat.get(i).price), k, index);
            k = k + 1;
            if (k == 4) {
                index = index + 1;
                k = 0;
            }
        }

    }

    public void TableFoodOrder(ArrayList<Order> List_ordr) {
        name_m.setCellValueFactory(new PropertyValueFactory<>("name_of_matreal"));
        number_m.setCellValueFactory(new PropertyValueFactory<>("number_of_matreal"));
        cost_m.setCellValueFactory(new PropertyValueFactory<>("cost_of_matreal"));
        ObservableMap v = name_m.getProperties();
        ObservableList data = FXCollections.observableArrayList(List_ordr);
        ObservableList<Order> get_data = TABLE_ORDER.getItems();
        TABLE_ORDER.setItems(data);

    }

    public void AddNewFoodOrder(String name_food, double cost, int i, int j) {
        VBox box = new VBox();

        Label name = new Label(name_food);
        name.setPadding(new Insets(10));
        name.setFont(new Font(28));
        Label price = new Label(cost + "");
        price.setFont(new Font(16));
        ObjectProperty<Pos> Align = box.alignmentProperty();
        Align.setValue(Pos.CENTER);
        box.getChildren().addAll(name, price);
        box.setStyle("    -fx-border-color:#008443;\n"
                + "    -fx-border-width: 1,1,1,1;\n"
                + "    -fx-border-style: solid;\n"
                + "");
        int number = 1;
        box.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent envet) {
                if (isFoundItem(list, name_food) != -1) {
                    int index = isFoundItem(list, name_food);
                    int old_number = Integer.parseInt(list.get(index).number_of_matreal);
                    double old_cost = Double.parseDouble(list.get(index).cost_of_matreal);
                    list.remove(index);
                    list.add(new Order(name_food, number + old_number + "", "" + (old_cost + cost)));
                    t_price.setText(sumorder() + "");
                } else {
                    list.add(new Order(name_food, number + "", "" + cost));
                    t_price.setText(sumorder() + "");
                }

                TableFoodOrder(list);
            }
        });

        grid_food.add(box, i, j);

    }

    public double sumorder() {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + Double.parseDouble(list.get(i).cost_of_matreal);
        }
        return sum;
    }

    public ArrayList<Order> Filter_order_List(ArrayList<Order> list, String name, int number, String Cost) {
        for (int i = 0; i < list.size(); i++) {
            if (name.equals(list.get(i).cost_of_matreal)) {
                int temp_number = Integer.parseInt(list.get(i).number_of_matreal);
                int new_number = temp_number + number;
                list.remove(i);
                list.add(new Order(name, new_number + "", Cost));
            }
            return list;

        }
        return list;

    }

    public int isFoundItem(ArrayList<Order> order, String Item) {
        for (int i = 0; i < order.size(); i++) {
            if (Item.equals(order.get(i).name_of_matreal)) {
                return i;
            }
        }
        return -1;
    }

    @FXML
    private void DeleteAllOrder(MouseEvent event) {
        list.clear();
        TABLE_ORDER.getItems().clear();
        System.out.println("Clear");
    }

    @FXML
    private void getSelectItem(MouseEvent event) {
        int item = TABLE_ORDER.getSelectionModel().getSelectedIndex();
        System.out.println(item);
        ObservableList<Order> listItem = TABLE_ORDER.getItems();

        this.MyMnue(item);
    }

    public void Edit(int item, String name_of_mtreal, String number_of_matreal, String cos_of_matreal) {

        Dialog dialog = new Dialog();
        dialog.setTitle("edit");
        ButtonType Editing = new ButtonType("حفظ التعديل", ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().add(Editing);

        GridPane grid = new GridPane();
        TextField name = new TextField();
        name.setPrefHeight(35);
        name.setText(name_of_mtreal);
        name.setStyle(""
                + "-fx-padding:5px;"
                + "-fx-border-insets:5px;"
                + "-fx-background-insets:5px");
        TextField number = new TextField();
        number.setPrefHeight(35);
        number.setText(number_of_matreal);
        number.setStyle(""
                + "-fx-padding:5px;"
                + "-fx-border-insets:5px;"
                + "-fx-background-insets:5px");
        TextField cost = new TextField();
        cost.setPrefHeight(35);
        cost.setText(cos_of_matreal);
        cost.setStyle(""
                + "-fx-padding:5px;"
                + "-fx-border-insets:5px;"
                + "-fx-background-insets:5px");

        grid.add(new Label("الاسم"), 0, 0);
        grid.add(name, 1, 0);

        grid.add(new Label("العدد"), 0, 1);
        grid.add(number, 1, 1);

        grid.add(new Label("السعر"), 0, 2);
        grid.add(cost, 1, 2);
        dialog.getDialogPane().setContent(grid);

        dialog.show();
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == Editing) {
                list.remove(item);
                list.add(new Order(name.getText(), number.getText(), cost.getText()));
                TABLE_ORDER.getItems().remove(item);
                TABLE_ORDER.getItems().add(new Order(name.getText(), number.getText(), cost.getText()));
            }

            return true;
        });

    }

    public void MyMnue(int item) {
        //Stage root = (Stage) MAIN_PARINT.getScene().getWindow();

        ContextMenu menuItem = new ContextMenu();
        menuItem.setStyle(""
                + "-fx-border-color:#008443;"
                + "-fx-border-width: 1,1,1,1;"
                + " -fx-border-style: solid;");

        menuItem.setAutoHide(true);

        MenuItem delete = new MenuItem("حذف العنصر");

        MenuItem Eedit = new MenuItem("تعديل العنصر");
        MenuItem close = new MenuItem("اغلاق");

        menuItem.getItems().addAll(delete, Eedit, close);
        menuItem.show(PANE_TABLE, Side.RIGHT, 0, 0);
        delete.setOnAction(event -> {
            list.remove(item);
            TABLE_ORDER.getItems().remove(item);
        });

        Eedit.setOnAction(event -> {

            this.Edit(item, TABLE_ORDER.getItems().get(item).name_of_matreal,
                    TABLE_ORDER.getItems().get(item).number_of_matreal,
                    TABLE_ORDER.getItems().get(item).cost_of_matreal);

        });

    }

    public PrintService getServer(String namep) {
        PrintService[] sv = null;
        sv = PrinterJob.lookupPrintServices();
        for (int i = 0; i < sv.length; i++) {
            if (sv[i].getName().equalsIgnoreCase(namep)) {

                return sv[i];
            }
        }
        return null;
    }

    @FXML
    private void PrintFinalOrder(MouseEvent event) throws PrinterException {

        Date date = new Date();
        DateFormat date_now = new SimpleDateFormat("yyy:MM:dd");

        String pattern = "hh:mm";
        LocalTime now = LocalTime.now();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        ChseirController.TarinData.DataTik = this.list;
        TarinData.Time = now.format(DateTimeFormatter.ofPattern(pattern));
        TarinData.Date = date_now.format(date);
        TarinData.t_price = sumorder() + "";
        TarinData.addprice = AddPrice();
        PrintReceiptOrderfinal print = new PrintReceiptOrderfinal("XP-80C (copy 7)");
       
      
        //jp.print();
    }

    public String AddPrice() {
        try {
            if (add_price.getText() == "") {
                return "0";
            } else {

                return add_price.getText();

            }
        } catch (Exception ex) {
            return "0";
        }
    }

  

    public static class TarinData {

        public static ArrayList<Order> DataTik;
        public static String t_price;
        public static String Date;
        public static String Time;
        public static String Table;
        public static String addprice;

    }
}
