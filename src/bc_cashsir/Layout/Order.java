/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc_cashsir.Layout;

/**
 *
 * @author ahmed
 */
public class Order {

    public String name_of_matreal;
    public String number_of_matreal;
    public String cost_of_matreal;

    public Order(String name_of_matreal, String number_of_matreal, String cost_of_matreal) {
        this.name_of_matreal = name_of_matreal;
        this.number_of_matreal = number_of_matreal;
        this.cost_of_matreal = cost_of_matreal;
    }

    public String getName_of_matreal() {
        return name_of_matreal;
    }

    public String getNumber_of_matreal() {
        return number_of_matreal;
    }

    public String getCost_of_matreal() {
        return cost_of_matreal;
    }

    public void setName_of_matreal(String name_of_matreal) {
        this.name_of_matreal = name_of_matreal;
    }

    public void setNumber_of_matreal(String number_of_matreal) {
        this.number_of_matreal = number_of_matreal;
    }

    public void setCost_of_matreal(String cost_of_matreal) {
        this.cost_of_matreal = cost_of_matreal;
    }
    

}
