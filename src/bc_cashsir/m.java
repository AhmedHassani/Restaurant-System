/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bc_cashsir;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 *
 * @author ahmed
 */
public class m {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Notifications builder =Notifications.create()
                .title("طلب جديد ")
                .text("حدث المواد")
                .graphic(null)
                .hideAfter(Duration.seconds(10))
                .position(Pos.CENTER)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            System.out.print("Click me !");
            }
        });
        builder.show();
               
    }
    
}
