package bc_cashsir;

/**
 *
 * @author ahmed
 */
public class Data {
    
    
    public static class Table{
       public String id, table;

        public String getId() {
            return id;
        }

        public Table(String id, String table) {
            this.id = id;
            this.table = table;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }
       
    }

    public static class Admin {

        public String name;
        public String user;
        public int isadmin;
        public String password;

        public Admin(String name, String user, int isadmin, String password) {
            this.name = name;
            this.user = user;
            this.isadmin = isadmin;
            this.password = password;
        }

    }

    public static class Order {
       public String id,id_order,id_table,name,date,time;

        public Order(String id, String id_order, String id_table, String name, String date, String time) {
            this.id = id;
            this.id_order = id_order;
            this.id_table = id_table;
            this.name = name;
            this.date = date;
            this.time = time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId_order() {
            return id_order;
        }

        public void setId_order(String id_order) {
            this.id_order = id_order;
        }

        public String getId_table() {
            return id_table;
        }

        public void setId_table(String id_table) {
            this.id_table = id_table;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
       

    }
    
    public static class Menu{
    public String id,num,mat,price;

        public Menu(String id, String num, String mat, String price) {
            this.id = id;
            this.num = num;
            this.mat = mat;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getMat() {
            return mat;
        }

        public void setMat(String mat) {
            this.mat = mat;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    public static class Groub {
        public String name;
        public String img;
        public String printer;
        public Groub(String name, String img,String printer) {
            this.name = name;
            this.img = img;
            this.printer=printer;
        }

    }
   

    public static class Materil {

        public String name, price, type, img, id;

        public Materil(String name, String price, String type, String id, String img) {
            this.name = name;
            this.price = price;
            this.type = type;
            this.img = img;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
    
   public static class  JsonSender{
    String name,type,price;

        public JsonSender(String name, String type, String price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }
    
    }

}
