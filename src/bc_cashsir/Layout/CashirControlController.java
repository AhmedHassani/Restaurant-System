/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_cashsir.Layout;

import bc_cashsir.BC_cashsir;
import bc_cashsir.Data;
import bc_cashsir.Model;
import bc_cashsir.PrintReceipt;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javax.print.PrintService;

/**
 * FXML Controller class
 *
 * @author Ahmed Rhman !
 */
public class CashirControlController extends Thread implements Initializable {

    @FXML
    private TableView<Data.Order> table_order;
    @FXML
    private TableView<Data.Menu> table_menu;
    @FXML
    private TableColumn<Data.Order, String> order_id;
    @FXML
    private TableColumn<Data.Order, String> order_num;
    @FXML
    private TableColumn<Data.Order, String> order_num_table;
    @FXML
    private TableColumn<Data.Order, String> order_name;
    @FXML
    private TableColumn<Data.Order, String> order_date;
    @FXML
    private TableColumn<Data.Order, String> order_time;
    @FXML
    private TableColumn<Data.Menu, String> id_mnue;
    @FXML
    private TableColumn<Data.Menu, String> number_mnue;
    @FXML
    private TableColumn<Data.Menu, String> mat_mnue;
    @FXML
    private TableColumn<Data.Menu, String> price_mnue;
    @FXML
    private TextField T_Price;
    @FXML
    private TextField T_NUM;
    @FXML
    private Button uptable;
    ArrayList<Data.Order> list_order;
    @FXML
    private TextField search;
    @FXML
    private Label inStack;
    @FXML
    private Label inFinsh;
    @FXML
    private Label inall;
    @FXML
    private TextField add_price;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            list_order = new Model().getOrder();

            inall.setText(new Model().getOrderAll().size() + "");
            inStack.setText(list_order.size() + "");
            inFinsh.setText(new Model().getOrderAll().size() - list_order.size() + "");

            TableOrder(list_order);
            table_order.setOnMouseClicked((e) -> {
                ObservableList<Data.Order> item = table_order.getSelectionModel().getSelectedItems();
                String name = item.get(0).id_order;
                ADDTK(name);

            });

        } catch (SQLException ex) {
            Logger.getLogger(CashirControlController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ADDTK(String id) {
        try {
            ArrayList<Data.Menu> list_mnue = new Model().getMenu(id);
            SetTotalPrice(list_mnue);
            this.TableMnue(list_mnue);
        } catch (SQLException ex) {
            Logger.getLogger(CashirControlController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public double SetTotalPrice(ArrayList<Data.Menu> list_mnue) {
        double sum = 0.0;
        if (list_mnue.size() == 0) {
            T_Price.setText("دنيار" + "0");
            T_NUM.setText("0");
        } else {
            for (int i = 0; i < list_mnue.size(); i++) {
                sum = sum + (Double.parseDouble(list_mnue.get(i).price));
            }
            T_Price.setText("" + sum);
            T_NUM.setText(list_mnue.size() + "");
        }
        return sum;
    }

    public void Search() {
        String q = search.getText();
        Model m = new Model();
        try {
            ArrayList<Data.Order> List_ordr = m.getOrder(q);
            this.TableOrder(List_ordr);
        } catch (SQLException ex) {
            Logger.getLogger(CashirControlController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void TableOrder(ArrayList<Data.Order> List_ordr) {
        order_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        order_num.setCellValueFactory(new PropertyValueFactory<>("id_order"));
        order_num_table.setCellValueFactory(new PropertyValueFactory<>("id_table"));
        order_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        order_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        order_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        ObservableList data = FXCollections.observableArrayList(List_ordr);
        ObservableList<Data.Order> get_data = table_order.getItems();
        System.out.println(get_data.size() + "");
        table_order.setItems(data);
    }

    public void TableMnue(ArrayList<Data.Menu> List_ordr) {
        id_mnue.setCellValueFactory(new PropertyValueFactory<>("id"));
        number_mnue.setCellValueFactory(new PropertyValueFactory<>("num"));
        mat_mnue.setCellValueFactory(new PropertyValueFactory<>("mat"));
        price_mnue.setCellValueFactory(new PropertyValueFactory<>("price"));
        ObservableList data = FXCollections.observableArrayList(List_ordr);
        table_menu.setItems(data);
    }

    @FXML
    private void upTableView(MouseEvent event) throws SQLException {
        list_order = new Model().getOrder();
        TableOrder(list_order);
        inall.setText(new Model().getOrderAll().size() + "");
        inStack.setText(list_order.size() + "");
        inFinsh.setText(new Model().getOrderAll().size() - list_order.size() + "");
    }

    private void MakeSearch(InputMethodEvent event) {

    }

    @FXML
    private void MakeSearch(KeyEvent event) {
        System.out.println("Search...!");
        Search();
    }

    public static PrintService getServer(String namep) {
        PrintService s = null;
        PrintService[] sv = PrinterJob.lookupPrintServices();
        for (int i = 0; i < sv.length; i++) {
            if (sv[i].getName().equalsIgnoreCase(namep)) {
                s = sv[i];
                return s;
            }
        }
        return null;
    }

    public String spaces(int num) {

        String sp = "";
        for (int i = 0; i < num; i++) {
            sp += " ";
        }

        return sp;

    }

    @FXML
    private void PrintFinalOrder(MouseEvent event) throws SQLException, PrinterException {

        try {

            String pattern = "hh:mm a";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            DateFormat date_now = new SimpleDateFormat("yy:mm:dd");
            LocalTime now = LocalTime.now();
            ObservableList<Data.Order> item = table_order.getSelectionModel().getSelectedItems();
            String id_order = item.get(0).id_order;
            ArrayList<Data.Menu> list_mnue = new Model().getMenu(id_order);
            TarinData.DataTik = list_mnue;
            TarinData.Price = this.SetTotalPrice(list_mnue) + "";
            TarinData.Date = item.get(0).date;
            TarinData.Time = now.format(DateTimeFormatter.ofPattern(pattern));
            TarinData.Table = item.get(0).id_table;

            TarinData.addPrice = AddPrice();
            PrintReceiptOrderfinal1 print = new PrintReceiptOrderfinal1("XP-80C (copy 7)");
            Thread.sleep(1000);
            new Model().setFinshOrder(id_order);// finsh...
            list_order = new Model().getOrder();//ref
            TableOrder(list_order);//ref
            inall.setText(new Model().getOrderAll().size() + "");
            inStack.setText(list_order.size() + "");
            inFinsh.setText(new Model().getOrderAll().size() - list_order.size() + "");

           
        } catch (Exception ex) {

            Dialog dilog = new Dialog();
            dilog.setTitle("خطأ");
            dilog.setContentText("اختار القائمة اولا");
            ButtonType Editing = new ButtonType("أغلاق", ButtonBar.ButtonData.OK_DONE);
            dilog.getDialogPane().getButtonTypes().add(Editing);
            dilog.show();
        }

    }

    public static class TarinData {

        public static ArrayList<Data.Menu> DataTik;
        public static String Price;
        public static String Date;
        public static String Time;
        public static String Table;
        public static String addPrice;

    }

}
