package bc_cashsir;

import bc_cashsir.Data;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Model {

    // //delete from groub where groub.name='jcjj';
    public ArrayList<Data.Admin> getAdmins() throws SQLException {
        ResultSet row = null;
        Data.Admin admin = null;
        ArrayList<Data.Admin> mat = new ArrayList<Data.Admin>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from admins");
            while (row.next()) {

                admin = new Data.Admin(
                        row.getString("name"),
                        row.getString("username"),
                        row.getInt("isadmin"),
                        row.getString("password")
                );
                mat.add(admin);
            }

            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }

    public ArrayList<Data.Groub> getGroubFood() throws SQLException {
        ResultSet row = null;
        Data.Groub groub = null;
        ArrayList<Data.Groub> mat = new ArrayList<Data.Groub>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from groub");
            while (row.next()) {

                groub = new Data.Groub(
                        row.getString("name"),
                        row.getString("img"),
                        row.getString("type")
                );
                mat.add(groub);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }

    public void SetMat(String name, String pirce, String type, String img) {
        ResultSet row = null;
        Data.Admin admin = null;
        ArrayList<Data.Admin> mat = new ArrayList<Data.Admin>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("insert into material('name','price','type','img') values('" + name + "','" + pirce + "','" + type + "','" + img + "');");
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void SetTable(String id_order) {
        ResultSet row = null;
        ArrayList<Data.Admin> mat = new ArrayList<Data.Admin>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("insert into tables('table') values('" + id_order + "');");
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //tikect
    public void SetTikect(String id_order, String name, String pirce, String num) {
        ResultSet row = null;
        ArrayList<Data.Admin> mat = new ArrayList<Data.Admin>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("insert into tikect('id_order','name','price','num') values('" + id_order + "','" + name + "','" + pirce + "','" + num + "');");
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public long SetOder(String id_order, String name_cst, String time, String number_table, String date) {
        long id = 0;
        try {
            connected my = new connected();
            Connection con = my.con();
            PreparedStatement ps = con.prepareStatement("insert into 'order'('name_cst','time','table_id','date_cst') values('" + name_cst + "','" + time + "','" + number_table + "','" + date + "');");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return id;

    }

    public void UpdateOrder(String id_order, String name_cst, String time, String number_table, String date) {
        long id = 0;
        try {
            connected my = new connected();
            Connection con = my.con();
            PreparedStatement ps = con.prepareStatement("update 'order' set 'name_cst'='" + name_cst + "','time'='" + time + "','table_id'='" + number_table + "','date_cst'='" + date + "' where id_order='" + id_order + "'");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void UpadeMat(String id, String name, String pirce, String type) {
        ResultSet row = null;
        Data.Admin admin = null;
        ArrayList<Data.Admin> mat = new ArrayList<Data.Admin>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("UPDATE material SET name ='" + name + "',price='" + pirce + "',type='" + type + "' where id='" + id + "';");
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteTable(String id) {
        ResultSet row = null;
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("delete from tables where id='" + id + "'");
            con.commit();
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Data.Table> getTable() {
        ResultSet row = null;
        Data.Table table = null;
        ArrayList<Data.Table> mat = new ArrayList<Data.Table>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from tables");
            while (row.next()) {

                table = new Data.Table(
                        row.getString("id"),
                        row.getString("table")
                );
                mat.add(table);
            }

            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;

    }

    public void addGroub(String name, String img) {
        ResultSet row = null;
        Data.Admin admin = null;
        ArrayList<Data.Admin> mat = new ArrayList<Data.Admin>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("insert into groub('name','img') values('" + name + "','" + img + "');");
            con.commit();
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteGroub(String name) {
        ResultSet row = null;
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("delete from groub where groub.name='" + name + "'");
            con.commit();
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteAllMat(String name) {
        ResultSet row = null;
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("delete from material where type='" + name + "'");
            con.commit();
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void DeleteRowMat(String id) {
        ResultSet row = null;
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("delete from material where material.id='" + id + "'");
            con.commit();
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Data.Materil> getMt() throws SQLException {
        ResultSet row = null;
        Data.Materil groub = null;
        ArrayList<Data.Materil> mat = new ArrayList<Data.Materil>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from material");
            while (row.next()) {

                groub = new Data.Materil(
                        row.getString("name"),
                        row.getString("price"),
                        row.getString("type"),
                        row.getString("id"),
                        row.getString("img")
                );
                mat.add(groub);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }

    public ArrayList<Data.JsonSender> getMtSentder() throws SQLException {
        ResultSet row = null;
        Data.JsonSender groub = null;
        ArrayList<Data.JsonSender> mat = new ArrayList<Data.JsonSender>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from material");
            while (row.next()) {
                groub = new Data.JsonSender(
                        row.getString("name"),
                        row.getString("price"),
                        row.getString("type")
                );
                mat.add(groub);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }
    //get Tiket 

    /// 
    public ArrayList<Data.Order> getOrderFinal() throws SQLException {
        ResultSet row = null;
        Data.Order order = null;
        ArrayList<Data.Order> mat = new ArrayList<>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from 'order' where finsh = '1'");
            while (row.next()) {
                order = new Data.Order(
                        row.getString("id"),
                        row.getString("id_order"),
                        row.getString("table_id"),
                        row.getString("name_cst"),
                        row.getString("date_cst"),
                        row.getString("time")
                );
                mat.add(order);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }

    //get Order
    public ArrayList<Data.Menu> getTikect(String id) throws SQLException {
        ResultSet row = null;
        Data.Menu order = null;
        ArrayList<Data.Menu> mat = new ArrayList<>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from 'tikect' where id_order = '" + id + "'");
            while (row.next()) {
                order = new Data.Menu(
                        row.getString("id"),
                        row.getString("num"),
                        row.getString("name"),
                        row.getString("price")
                );
                mat.add(order);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }

    public ArrayList<Data.Order> getOrder() throws SQLException {
        ResultSet row = null;
        Data.Order order = null;
        ArrayList<Data.Order> mat = new ArrayList<>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from 'order' where finsh = '0'");
            while (row.next()) {
                order = new Data.Order(
                        row.getString("id"),
                        row.getString("id_order"),
                        row.getString("table_id"),
                        row.getString("name_cst"),
                        row.getString("date_cst"),
                        row.getString("time")
                );
                mat.add(order);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }
    // end

    public ArrayList<Data.Order> getOrderAll() throws SQLException {
        ResultSet row = null;
        Data.Order order = null;
        ArrayList<Data.Order> mat = new ArrayList<>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from 'order'");
            while (row.next()) {
                order = new Data.Order(
                        row.getString("id"),
                        row.getString("id_order"),
                        row.getString("table_id"),
                        row.getString("name_cst"),
                        row.getString("date_cst"),
                        row.getString("time")
                );
                mat.add(order);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }
//end

    public ArrayList<Data.Order> getOrder(String id_order) throws SQLException {
        ResultSet row = null;
        Data.Order order = null;
        ArrayList<Data.Order> mat = new ArrayList<>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from 'order' where id_order like '" + id_order + "%'");
            while (row.next()) {
                order = new Data.Order(
                        row.getString("id"),
                        row.getString("id_order"),
                        row.getString("table_id"),
                        row.getString("name_cst"),
                        row.getString("date_cst"),
                        row.getString("time")
                );
                mat.add(order);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }
    //

    public void setFinshOrder(String id_order) throws SQLException {
        ResultSet row = null;
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery(" update 'order' set finsh ='1' where id_order ='" + id_order + "';");
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setNotFinshOrder(String id_order) throws SQLException {
        ResultSet row = null;
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery(" update 'order' set finsh ='0' where id_order ='" + id_order + "';");
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Data.Menu> getMenu(String id) throws SQLException {
        ResultSet row = null;
        Data.Menu order = null;
        ArrayList<Data.Menu> mat = new ArrayList<>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from tikect where id_order=" + id);
            while (row.next()) {
                order = new Data.Menu(
                        row.getString("id"),
                        row.getString("num"),
                        row.getString("name"),
                        row.getString("price")
                );
                mat.add(order);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }

    public ArrayList<Data.Materil> getMt(String name) throws SQLException {
        ResultSet row = null;
        Data.Materil groub = null;
        ArrayList<Data.Materil> mat = new ArrayList<Data.Materil>();
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("select * from material where type='" + name + "';");
            while (row.next()) {

                groub = new Data.Materil(
                        row.getString("name"),
                        row.getString("price"),
                        row.getString("type"),
                        row.getString("id"),
                        row.getString("img")
                );
                mat.add(groub);
            }
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mat;
    }

    public String SentUpadta() throws SQLException, JSONException {
        ArrayList<Data.JsonSender> list = this.getMtSentder();
        String json = new Gson().toJson(list);
        return json;
    }

    public String SentUpadtaTable() throws SQLException, JSONException {
        ArrayList<Data.Table> list = this.getTable();
        String json = new Gson().toJson(list);
        return json;
    }

    public String Type(String namein) throws SQLException {
        ArrayList<Data.Materil> list = this.getMt();
        for (int i = 0; i < list.size(); i++) {
            if (namein.equals(list.get(i).name)) {
                return GetPrinter(list.get(i).type);
            }
        }
        return "";
    }

  

  

    public String GetPrinter(String set) {
        try {
            ArrayList<Data.Groub> list = this.getGroubFood();
            for (int i = 0; i < list.size(); i++) {
                if (set.equals(list.get(i).name)) {
                    return list.get(i).printer;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void ClearAllOrder() {
        ResultSet row = null;
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("delete from 'order'");
            con.commit();
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void ClearAllMnue() {
        ResultSet row = null;
        try {
            connected my = new connected();
            Connection con = my.con();
            Statement qurrey = con.createStatement();
            row = qurrey.executeQuery("delete from tikect");
            con.commit();
            con.close();
            row.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException, JSONException {
     
        //System.out.println(l);
        //System.out.print(new Model().Type("جاي"));
    }

}
