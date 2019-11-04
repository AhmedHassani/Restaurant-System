package bc_cashsir.Layout;

import bc_cashsir.*;
import bc_cashsir.Layout.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.print.PrintService;
import javax.swing.JOptionPane;

public class PrintAccont implements Printable {

    // Attributes..
    private PrinterJob printerJob = null;
    private PageFormat pageFormat = null;
    private Paper paper;
    public String name;

    private final int MARGIN = 1;

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

    public PrintAccont(String name) throws PrinterException {
        this.name = name;
        PrintService ps = getServer(name);
        printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintService(ps);
        pageFormat = printerJob.defaultPage(); // Getting the page format.

        paper = new Paper(); // Create a new paper..

        int width = 3000;
        int height = 900000;

        // width = totalWidthOfPage - (MARGIN * 2);
        // height = numberOfLines * 10 - (MARGIN * 2);
        paper.setImageableArea(MARGIN, MARGIN, width, height);
        pageFormat.setPaper(paper);

        pageFormat.setOrientation(PageFormat.PORTRAIT);
        printerJob.setPrintable(this, pageFormat);

        try {

            printerJob.print();
            printerJob = null;

        } catch (PrinterException ex) {

            JOptionPane.showMessageDialog(null, "Printing Failed, Error: " + ex.toString());

        }

    }

    /**
     * Printing with Graphics..
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        byte SendCut[] = {0x0a, 0x0a, 0x1d, 0x56, 0x01};
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.black);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        HashMap<String, ArrayList<Data.Menu>> map = SettingController.TarnAccuont.data;
        Set<String> l = map.keySet();
         int count_page= 30;
        for (String k : l) {
            int sum_n = 0;
            double sum_p = 0;
           
            ArrayList<Data.Menu> list1 = map.get(k);
            for (int m = 0; m < list1.size(); m++) {
                sum_n = sum_n + Integer.parseInt(list1.get(m).num);
                sum_p = sum_p + Double.parseDouble(list1.get(m).price);
            }
            g2d.drawString(k + "                                        " + sum_n + "      " + sum_p, 0, count_page);
            g2d.drawString("______________________________________________________________", 0,count_page+10);
            count_page=count_page+10;
        }
      
        g2d.drawString(SettingController.TarnAccuont.num + " :      " + "عدد الطلبات", 0, count_page+20);
        g2d.drawString(SettingController.TarnAccuont.t_price + " : " + "المجموع الكلي ", 0,count_page+10);
        return PAGE_EXISTS;

    }

    /**
     * Adding spaces into the num.
     *
     * @param total spaces
     * @return all spaces in string.
     */
    public String spaces(int num) {

        String sp = "";
        for (int i = 0; i < num; i++) {
            sp += " ";
        }

        return sp;

    }

}
