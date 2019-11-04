/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_cashsir;

import javax.print.PrintService;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author ahmed
 */
public class Transfer {

    // copy(new File("/home/ahmed/Desktop/15462.jpg"),new File("/home/ahmed/15462.jpg"));
    public static String copy(File src, String dest) throws IOException {
        String c = src.getPath();
        String[] s = c.split(".png");
        String P_j = "";
        if (s.length < 0) {
            P_j = ".png";
        } else {
            P_j = ".jpg";
        }
        long num = (long) ((Math.random()) * 1000000000);
        String namefile = num + P_j;
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(new File("src/"+dest + namefile));

            // buffer size 1K
            byte[] buf = new byte[1024];

            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
        } finally {
            is.close();
            os.close();
        }
        return dest + namefile;
    }
    

}
