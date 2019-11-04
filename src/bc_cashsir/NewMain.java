/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_cashsir;

import bc_cashsir.Layout.ChseirController;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.print.PrintService;

/**
 *
 * @author Ahmed Rhman Hassani
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PrinterException, InterruptedException {
        for (int i = 0; i < 4; i++) {
            PrintReceiptfinal2 f1 = new PrintReceiptfinal2("XP-80C");
        }
        for (int i = 0; i < 4; i++) {
            PrintReceiptfinal2 f2 = new PrintReceiptfinal2("XP-80C (copy 2)");
        }
        for (int i = 0; i < 4; i++) {
            PrintReceiptfinal2 f2 = new PrintReceiptfinal2("XP-80C (copy 1)");
        }
        for (int i = 0; i < 4; i++) {
            PrintReceiptfinal2 f2 = new PrintReceiptfinal2("XP-80C (copy 3)");
        }
//        PrintService d;
//        d = null;
//        PrinterJob jp;
//        jp = null;
//        d = getServer("XP-80C");
//        jp = PrinterJob.getPrinterJob();
//        jp.setPrintService(d);
//        jp.setPrintable(new PrintReceiptfinal2("XP-80C"));
//        jp = null;
//        d = null;
//        ////end
//        Thread.sleep(1000);
//        PrintService d1;
//        d1 = null;
//        PrinterJob jp1;
//        jp1 = null;
//        d1 = getServer("80mm Thermal Printer");
//        jp1 = PrinterJob.getPrinterJob();
//        jp1.setPrintService(d1);
//        jp = null;
//        d = null;

//        for (int i = 0; i < 6; i++) {
//            if (i < 3) {
//            
//               // jp.print();
//            } else {
//                //MakePrinter("XP-80C (copy 1)");
//
//                PrintService d;
//                d = null;
//                PrinterJob jp;
//                jp = null;
//                d = getServer("80mm Thermal Printer");
//                jp = PrinterJob.getPrinterJob();
//                jp.setPrintService(d);
//               
//               
//                jp.setPrintable(new PrintReceiptfinal2("80mm Thermal Printer"));
//                //jp.print();
//            }
//            Thread.sleep(1000);
//        }
    }

    public static void MakePrinter(String namp) throws PrinterException {

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

}
