package bc_cashsir;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.print.PrintService;
import javax.swing.JOptionPane;

public class PrintReceiptfinal2 implements Printable {

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

    public PrintReceiptfinal2(String name) throws PrinterException {
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
        String pattern = "hh:mm a";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        LocalTime now = LocalTime.now();
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        byte SendCut[] = {0x0a, 0x0a, 0x1d, 0x56, 0x01};
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setColor(Color.black);

        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("                    " + ServerLogin.TranData.table + "  " + "طاولة" + "", 0, 10);
        g2d.drawString("                    " + ServerLogin.TranData.i_order + "  " + "رقم الطلب" + "", 0, 30);
        g2d.drawString("                    " + now.format(DateTimeFormatter.ofPattern(pattern)) + "  " + " الوقت" + "", 0, 50);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("__________________________________________________________________________________", 10, 60);
        int k = 80;
        for (int i = 0; i < ServerLogin.TranData.list.size(); i++) {
            g2d.drawString(ServerLogin.TranData.list.get(i).number + "....................................." + ServerLogin.TranData.list.get(i).name, 4, k);
            k = k + 15;
        }
        //
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

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
