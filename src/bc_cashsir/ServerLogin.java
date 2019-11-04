package bc_cashsir;

import bc_cashsir.Model;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Ahmed Rhman Hassani
 */
class ServerLogin {

    int port;
    ServerSocket server = null;
    Socket client = null;
    ExecutorService pool = null;
    int clientcount = 0;

    ServerLogin(int port) {
        this.port = port;
        pool = Executors.newFixedThreadPool(5);
    }

    public void startServer() throws IOException {

        server = new ServerSocket(7000);
        System.out.println("Server Booted");
        System.out.println("Any client can stop the server by sending -1");
        while (true) {
            client = server.accept();
            clientcount++;
            ServerThread runnable = new ServerThread(client, this);
            pool.execute(runnable);
        }
    }

    public void CloseSever() {
        try {
            this.server.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class ServerThread implements Runnable {

        Notifications noty;

        //  
        //System.out.println();
        double r = Math.random() * 100000;
        String ID_Order = ((int) r) + "";
        ServerLogin server = null;
        Socket client = null;
        BufferedReader cin;
        PrintStream cout;
        ObjectInputStream cin2;
        private String MASSAGE;
        Model model;
        ObjectOutputStream outlogon;

        ServerThread(Socket client, ServerLogin server) throws IOException {

            this.client = client;
            this.server = server;

            System.out.println("Connection established with client " + client);
            //cin = new BufferedReader(new InputStreamReader(client.getInputStream()));
            cin2 = new ObjectInputStream(client.getInputStream());
            model = new Model();
            //ObjectInputputStream out = new ObjectInputputStream(sk.getOutputStream());
            cout = new PrintStream(client.getOutputStream());
            outlogon = new ObjectOutputStream(client.getOutputStream());
        }

        public void ControlPrinter(JSONArray arr, String Table) throws JSONException, SQLException, PrintException, PrinterException {
            HashMap<String, ArrayList<Tran>> map = new HashMap();
            Model m = new Model();
            for (int j = 0; j < arr.length(); j++) {
                System.out.println(arr.getJSONObject(j).getString("name") + "====>" + m.Type(arr.getJSONObject(j).getString("name")));
                String item = m.Type(arr.getJSONObject(j).getString("name"));
                if (!map.containsKey(item)) {
                    ArrayList<Tran> list = new ArrayList();
                    list.add(new Tran(arr.getJSONObject(j).getString("name"), arr.getJSONObject(j).getString("num")));
                    map.put(item, list);
                } else {
                    ArrayList<Tran> list = map.get(item);
                    list.add(new Tran(arr.getJSONObject(j).getString("name"), arr.getJSONObject(j).getString("num")));
                    map.replace(item, list);
                }
            }
            Set<String> key = map.keySet();
            for (String k : key) {
                ArrayList<Tran> wsal = map.get(k);
                this.MakePrint(wsal, Table,k); 
            }
        }

        public static PrintService getServer(String namep) {
            PrintService[] sv = null;
            sv = PrinterJob.lookupPrintServices();
            for (int i = 0; i < sv.length; i++) {
                if (sv[i].getName().equalsIgnoreCase(namep)) {

                    return sv[i];
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

        public void MakePrint(ArrayList<Tran> k, String Table, String namep) throws PrintException, PrinterException {
            TranData.printer_name = namep;
            TranData.table = Table;
            TranData.list = k;
            TranData.i_order = ID_Order;
            PrintReceiptfinal2 control1 = new PrintReceiptfinal2(TranData.printer_name);
        }

        @Override
        public void run() {
            JSONObject obj;
            try {
                obj = new JSONObject();
                while (true) {

                    Object i = cin2.readUnshared();
                    JSONObject or;
                    JSONArray arrobj;
                    JSONObject object = new JSONObject(i.toString());
                    String prossing = object.getString("PROSSING");

                    switch (prossing) {
                        case "UPDATA": {
                            String value = model.SentUpadta();
                            obj.put("UPDATA_MAT", value);
                        }
                        break;
                        case "TABLE": {
                            String value = model.SentUpadtaTable();
                            System.out.println(value);
                            obj.put("TDATA", value);
                        }
                        break;
                        case "NEW": {
                            long value = model.SetOder("", "", "", "1", "1");
                            System.out.println(value);
                            obj.put("ID", value);
                        }
                        break;
                        default: {
                            System.out.println(prossing);
                            or = new JSONObject(prossing);
                            //BGIN ORDER 
                            String ID = or.getString("IDORDER");
                            String TATBLE = or.getString("TATBLE");
                            String TIME = or.getString("TIME");
                            String NAME = or.getString("NAME");

                            //END ORDER 
                            String arr = or.getString("DATA");
                            arrobj = new JSONArray(arr);
                            for (int j = 0; j < arrobj.length(); j++) {
                                new Model().SetTikect(ID,
                                        arrobj.getJSONObject(j).getString("name"),
                                        arrobj.getJSONObject(j).getString("price"),
                                        arrobj.getJSONObject(j).getString("num"));
                            }
                            DateFormat date_now = new SimpleDateFormat("yyy:MM:dd");
                            Date date = new Date();
                            new Model().UpdateOrder(ID, NAME, TIME, TATBLE, date_now.format(date));
                            new Model().setNotFinshOrder(ID);
                            ControlPrinter(arrobj, TATBLE);
                            obj.put("RESILT", "Ok");

                        }

                    }
                    outlogon.writeObject(obj.toString());
                    cin2.close();
                }

            } catch (IOException ex) {

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrintException ex) {
                Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrinterException ex) {
                Logger.getLogger(ServerLogin.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        private void ArrayList() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static class TranData {

        public static String printer_name;
        public static ArrayList<Tran> list;
        public static String table;
        public static String i_order;

    }

    public static class Tran {

        String name;
        String number;

        public Tran(String name, String number) {
            this.name = name;
            this.number = number;
        }

    }

}
