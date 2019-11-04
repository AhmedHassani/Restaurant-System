/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_cashsir.Layout;

import bc_cashsir.Data;
import bc_cashsir.Model;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.print.PrintService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class SettingController implements Initializable {

    private ListView<String> printList;
    @FXML
    private TableView<?> list;
    @FXML
    private TextField num_table;
    @FXML
    private CheckBox select;
    @FXML
    private TextField end;
    @FXML
    private TextField bgin;
    @FXML
    private TableColumn<?, ?> id_i;
    @FXML
    private TableColumn<?, ?> table_i;
    @FXML
    private TableColumn delete_i;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.SetTableData();

    }

    @FXML
    private void Accont(MouseEvent event) throws SQLException, PrinterException {
        ArrayList<Data.Order> order = new Model().getOrder();
        ArrayList<Data.Order> all = new Model().getOrderFinal();
        Double account = 0.0;
        if (order.size() > 0) {
            System.out.println("" + order.size());
        }
        for (int i = 0; i < all.size(); i++) {
            account = account + getTotelPrice(all.get(i).id_order);

        }
        TarnAccuont.num = all.size() + "";
        TarnAccuont.t_price = account + "";
        TarnAccuont.data=DayAccount(all);
        PrintAccont paccount = new PrintAccont("XP-80C (copy 7)");
        
        
    }

    public  HashMap<String, ArrayList<Data.Menu>>  DayAccount(ArrayList<Data.Order> li) throws SQLException {
        HashMap<String, ArrayList<Data.Menu>> map = new HashMap();
        for (int i = 0; i < li.size(); i++) {
            ArrayList<Data.Menu> list = new Model().getMenu(li.get(i).id_order);
          
            for ( int j=0;j<list.size();j++) {
                 String name = list.get(j).mat;
                if (map.containsKey(name)) {
                    
                    ArrayList<Data.Menu> list1 = map.get(name);
                    list1.add(new Data.Menu("", list.get(j).num, name, list.get(j).price));
                    map.put(name, list1);
                
   
                } else {
                    ArrayList<Data.Menu> list1 = new ArrayList();
                    list1.add(new Data.Menu("", list.get(j).num, name, list.get(j).price));
                    map.put(name, list1);
                  
                }
            }
        }
         return map;
    }
        
//     Set<String>  l=  map.keySet();
//     for (String k : l){
//         ArrayList<Data.Menu> list1 =map.get(k);
//         for (int m =0;m<list1.size();m++){
//            System.out.println(list1.get(m).mat+" =>"+list1.get(m).price+"  "+list1.get(m).num);
//         }
//     }
//    System.out.println("__________________________________________");
//        
    

    public Double getTotelPrice(String id) throws SQLException {
        Double item = 0.0;
        ArrayList<Data.Menu> list = new Model().getMenu(id);
        for (int i = 0; i < list.size(); i++) {
            item = item + (Double.parseDouble(list.get(i).price));
        }

        return item;
    }

    private void Delete(MouseEvent event) {
        boolean formatGotIt;
        ButtonType yes = new ButtonType("نعم", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("رجوع", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.NONE, "هل تريد حذف البيانات  ؟",
                yes,
                no);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(no) == yes) {
            try {
                new Model().ClearAllOrder();
                new Model().ClearAllMnue();
                Dialog dialog = new Dialog();
                dialog.setContentText("تم حذف البيانات");
                dialog.getDialogPane().getButtonTypes().add(yes);
                dialog.show();
            } catch (Exception ex) {
                Dialog dialog = new Dialog();
                dialog.setContentText("خطأ");
                dialog.getDialogPane().getButtonTypes().add(yes);
                dialog.show();
            }

        }

    }

    public void SetTableData() {
        list.getItems().clear();
        table_i.setCellValueFactory(new PropertyValueFactory<>("table"));
        id_i.setCellValueFactory(new PropertyValueFactory<>("id"));
        //delete_i.setCellValueFactory(new PropertyValueFactory<>("type"));
        ObservableList data = FXCollections.observableArrayList(new Model().getTable());

        Callback<TableColumn<Data.Table, String>, TableCell<Data.Table, String>> callpuls = (e) -> {
            final TableCell<Data.Table, String> cell = new TableCell<Data.Table, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button but = new Button("حذف");
                        but.setPrefWidth(100);
                        but.setStyle("-fx-background-color:red;"
                                + "  -fx-text-fill: white ;"
                                + " -fx-font-size: 20;");
                        setText(null);
                        setGraphic(but);
                        setText(null);
                        but.setOnAction(e -> {
                            String idd = getTableView().getItems().get(getIndex()).id;
                            new Model().DeleteTable(idd);
                            SetTableData();

                        });

                    }
                }

            };

            return cell;
        };
        this.delete_i.setCellFactory(callpuls);
        list.setItems(data);
    }

    private void searchPrinter(MouseEvent event) {
        PrintService[] arrprinter = PrinterJob.lookupPrintServices();
        for (PrintService ps : arrprinter) {
            printList.getItems().add(ps.getName());
        }

    }

    @FXML
    private void AddTable(MouseEvent event) {
        boolean is = select.selectedProperty().get();
        if (is) {
            int i = Integer.parseInt(bgin.getText());
            int j = Integer.parseInt(end.getText());
            for (int k = i; k <= j; k++) {
                new Model().SetTable(k + "");
                this.SetTableData();
            }

        } else {
            if (!num_table.getText().equals("")) {
                new Model().SetTable(num_table.getText());
                this.SetTableData();
            }
        }
    }

    @FXML
    private void MakeShow(MouseEvent event) {
        boolean is = select.selectedProperty().get();
        if (is == true) {
            num_table.setEditable(false);
            this.bgin.setEditable(true);
            this.end.setEditable(true);
        } else {
            num_table.setEditable(true);
            this.bgin.setEditable(false);
            this.end.setEditable(false);
        }
    }

    public static class TarnAccuont {

        public static String num;
        public static String t_price;
        public static  HashMap<String, ArrayList<Data.Menu>>  data;

    }

    public static class MA {

        public int num;
        public double price;

        public MA(int num, double price) {
            this.num = num;
            this.price = price;
        }

    }

}
