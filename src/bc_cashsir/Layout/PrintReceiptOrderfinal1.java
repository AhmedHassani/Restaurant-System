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
import javax.print.PrintService;
import javax.swing.JOptionPane;

public class PrintReceiptOrderfinal1 implements Printable {

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

    public PrintReceiptOrderfinal1(String name) throws PrinterException {
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

        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("          " + "  " + "العاب و حدائق زيونة" + "", 0, 10);
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        g2d.drawString(CashirControlController.TarinData.Time + " : " + "الوقت", 110, 30);
        g2d.drawString(CashirControlController.TarinData.Date + " : " + "التاريخ", 15, 30);
        g2d.drawString("__________________________________________________________________________________________________________", 0, 40);
        int k = 60;
        for (int i = 0; i < CashirControlController.TarinData.DataTik.size(); i++) {
            g2d.drawString(CashirControlController.TarinData.DataTik.get(i).price + "....................................." + CashirControlController.TarinData.DataTik.get(i).num + "  " + CashirControlController.TarinData.DataTik.get(i).mat, 4, k);
            k = k + 15;
        }
        g2d.drawString("__________________________________________________________________________________________________________", 0, k + 15);
        g2d.drawString("    " + CashirControlController.TarinData.addPrice + " : " + "خدمة طاولة", 100, k + 30);
        g2d.drawString(Double.parseDouble(CashirControlController.TarinData.Price) + Double.parseDouble(CashirControlController.TarinData.addPrice) + " : " + "السعر الكلي", 100, k + 45);
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.drawString("__________________________________________________________________________________________________________", 0, k + 55);
        g2d.drawString("بابلون كود للحلول البرمجية", 10, k + 75);
        g2d.drawString("07831503506", 10, k + 90);
        g2d.drawString("07803630686", 10, k + 100);
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
