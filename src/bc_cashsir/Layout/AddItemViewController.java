package bc_cashsir.Layout;

import bc_cashsir.Data;
import bc_cashsir.Model;
import bc_cashsir.Transfer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;

/**
 * FXML Controller class
 *
 * @author Ahmed Rhman Hassani
 */
public class AddItemViewController implements Initializable {

    static String PTAH_OF_IMG;
    @FXML
    private AnchorPane MAIN_PARINT;
    @FXML
    private JFXListView<HBox> list_set;
    @FXML
    private JFXButton save_groub;
    @FXML
    private JFXTextField name_of_groub;
    @FXML
    private JFXTextField path_groub;

    @FXML
    private ImageView del_but;
    @FXML
    private ComboBox<String> items_del_show;
    @FXML
    private TableColumn<Data.Materil, String> type_or_set;
    @FXML
    private TableColumn<Data.Materil, String> price;
    @FXML
    private TableColumn<Data.Materil, String> name;
    @FXML
    private TableColumn<Data.Materil, String> id;
    @FXML
    private TableView<Data.Materil> tabel_view_mat;
    @FXML
    private TextField e_id;
    @FXML
    private TextField e_name;
    @FXML
    private ComboBox<String> e_type;
    @FXML
    private TextField e_price;
    @FXML
    private ComboBox<String> select_mat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        select_mat.getItems().add("الكل");
        Change();
        e_id.setDisable(false);
        try {

            ArrayList<Data.Materil> List_ordr = new Model().getMt();
            this.SetDataTable(List_ordr);
            Refetion();
        } catch (SQLException ex) {
            Logger.getLogger(AddItemViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Change() {

        select_mat.setOnAction((e) -> {
            ArrayList<Data.Materil> List_ordr = null;
            String item = select_mat.getSelectionModel().getSelectedItem().toString();
            try {
                if (item.equals("الكل".trim())) {
                    tabel_view_mat.getItems().clear();
                    List_ordr = new Model().getMt();
                } else {
                    tabel_view_mat.getItems().clear();
                    List_ordr = new Model().getMt(item);
                }
                this.SetDataTable(List_ordr);
            } catch (SQLException ex) {
                Logger.getLogger(AddItemViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    public void SetDataTable(ArrayList<Data.Materil> List_ordr) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        type_or_set.setCellValueFactory(new PropertyValueFactory<>("type"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        ObservableList data = FXCollections.observableArrayList(List_ordr);
        ObservableList<Data.Materil> get_data = tabel_view_mat.getItems();

        tabel_view_mat.setItems(data);

    }

    public void Refetion() throws SQLException {
        list_set.getItems().clear();
        items_del_show.getItems().clear();
        e_type.getItems().clear();
        select_mat.getItems().clear();
        select_mat.getItems().add("الكل");
        ArrayList<Data.Groub> list = new Model().getGroubFood();
        for (int i = 0; i < list.size(); i++) {
            items_del_show.getItems().add(list.get(i).name);
            e_type.getItems().add(list.get(i).name);
            select_mat.getItems().add(list.get(i).name);
            this.addListItem(list.get(i).img, list.get(i).name);
        }

    }

    public void addListItem(String scr, String title) {
        Image image;
        try {
            //bc_cashsir/Img/groub/234336369.jpg
            image = new Image(scr);
        } catch (Exception ex) {
            image = new Image("bc_cashsir/Img/groub/info.png");
        }
        Pane pane = new Pane();
        Circle clip = new Circle(20, 25, 25);
        clip.setStroke(Color.SEAGREEN);
        clip.setEffect(new DropShadow(+25d, 4d, +2d, Color.DARKSEAGREEN));
        clip.setFill(new ImagePattern(image));
        pane.getChildren().add(clip);
        pane.setPrefHeight(50);
        pane.setPrefWidth(50);
        Label name = new Label(title);
        name.setFont(new Font(20));
        name.setPadding(new Insets(0,22, 2, 0));
        HBox box = new HBox();
        VBox boxtitle = new VBox();
        boxtitle.getChildren().addAll(name);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(boxtitle, pane);
        list_set.getItems().add(box);
    }

    @FXML
    private void AddSet(MouseEvent event) throws IOException {

    }

    public void showInfoDialog() {

    }

    @FXML
    private void getPath(MouseEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        String currentDir = System.getProperty("user.home") + File.separator;
        File file = new File(currentDir);
        chooser.setInitialDirectory(file);
        file = chooser.showOpenDialog(new Stage());
        try {
            path_groub.setText(file.getPath());
            System.out.println("Path Selected !!!");
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    @FXML
    private void SaveGroub(MouseEvent event) throws IOException, SQLException {
        String path = path_groub.getText();
        String cop_imge = null;
        File file = new File(path);

        String name_groub = name_of_groub.getText();
        if (name_groub.equals("")) {
            Dialog dialog = new Dialog();
            dialog.setTitle("خطأ في الاداخال");
            dialog.setContentText("يجب أدخال جميع الحقول ");
            ButtonType close = new ButtonType("اغلاق");
            dialog.getDialogPane().getButtonTypes().add(close);
            dialog.show();
        } else {
            try {
                cop_imge = Transfer.copy(file, "bc_cashsir/Img/groub/");
                new Model().addGroub(name_groub, cop_imge);
                this.Refetion();
            } catch (Exception e) {
                Dialog dialog = new Dialog();
                dialog.setTitle("خطأ");
                dialog.setContentText("لم يتم تحميل الصورة");
                ButtonType close = new ButtonType("اغلاق");
                dialog.getDialogPane().getButtonTypes().add(close);
                dialog.show();
            } finally {

            }
        }

    }

    @FXML
    private void DeleteSet(MouseEvent event) {
        try {
            String item = items_del_show.getSelectionModel().getSelectedItem().toString();
            new Model().DeleteGroub(item);
            new Model().DeleteAllMat(item);
            tabel_view_mat.getItems().clear();
            ArrayList<Data.Materil> List_ordr = new Model().getMt();
            this.SetDataTable(List_ordr);
            this.Refetion();

        } catch (Exception e) {
            Dialog dialog = new Dialog();
            dialog.setTitle("خطأ");
            dialog.setContentText("لم يتم تحميل الصورة");
            ButtonType close = new ButtonType("اغلاق");
            dialog.getDialogPane().getButtonTypes().add(close);
            dialog.show();
        }
    }

    @FXML
    private void SeclectItem(MouseEvent event) {
        ObservableList<HBox> items = list_set.getSelectionModel().getSelectedItems();
        ObservableList<Node> node = items.get(0).getChildren();
        Node ff = node.get(0);

    }

    @FXML
    private void FullData(MouseEvent event) {
        try{
        ObservableList<Data.Materil> items = tabel_view_mat.getSelectionModel().getSelectedItems();
        e_name.setText(items.get(0).name);
        e_price.setText(items.get(0).price);
        e_id.setText(items.get(0).id);
        }catch(Exception e){
         System.out.println("Error in =>FullData additemviewControll !");
        }
    }

    public boolean villd() {
        boolean is_empty = true;
        String st = "-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-insets: 1;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: #ec4f39;";
        try {
            String item = e_type.getSelectionModel().getSelectedItem().toString();
        } catch (Exception ex) {
            this.myDia("خطا", "يجب اختار الصنف او المجموعة ");
            is_empty = false;
        }

        if (e_name.getText().equals("")) {
            e_name.setStyle(st);
            is_empty = false;
        }
        if (e_price.getText().equals("")) {
            e_price.setStyle(st);
            is_empty = false;
        }

        return is_empty;
    }

    @FXML
    private void AddMatValues(MouseEvent event) throws SQLException {
        if (this.villd()) {
            int count = new Model().getMt(e_type.getSelectionModel().getSelectedItem().toString()).size();
            if (count > 16) {
                this.myDia("خطا", "عنصر" + "16" + "عدد الصنف او المجوعة يجب لا يتجاوز");
            } else {
                new Model().SetMat(e_name.getText(), e_price.getText(), e_type.getSelectionModel().getSelectedItem().toString(), "");
                myDia("تم", "تم  الحفظ المادة ");
                tabel_view_mat.getItems().clear();
                ArrayList<Data.Materil> List_ordr = new Model().getMt();
                this.SetDataTable(List_ordr);
            }
        }
    }

    public void myDia(String title, String msg) {
        Dialog dialog = new Dialog();
        dialog.setTitle(title);
        dialog.setContentText(msg);
        ButtonType close = new ButtonType("اغلاق");
        dialog.getDialogPane().getButtonTypes().add(close);
        dialog.show();

    }

    @FXML
    private void DeleteRowMat(MouseEvent event) throws SQLException {

        if (this.villd()) {
            new Model().DeleteRowMat(e_id.getText());
            myDia("تم ", "تم  حذف المادة ");
            tabel_view_mat.getItems().clear();
            ArrayList<Data.Materil> List_ordr = new Model().getMt();
            this.SetDataTable(List_ordr);
        }

    }

    @FXML
    private void Up_Data(MouseEvent event) {
    }

    @FXML
    private void UpData(MouseEvent event) throws SQLException {
        if (this.villd()) {
            new Model().UpadeMat(e_id.getText(), e_name.getText(), e_price.getText(), e_type.getSelectionModel().getSelectedItem().toString());
            myDia("تم ", "تم تعديل  المادة ");
            tabel_view_mat.getItems().clear();
            ArrayList<Data.Materil> List_ordr = new Model().getMt();
            this.SetDataTable(List_ordr);
        }
    }

    @FXML
    private void clear(MouseEvent event) {
        e_id.setText("");
        e_name.setText("");
        e_price.setText("");
    }
}
