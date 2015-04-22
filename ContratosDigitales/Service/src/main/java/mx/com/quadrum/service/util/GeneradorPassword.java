/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.util;

/**
 *
 * @author vcisneros
 */
public class GeneradorPassword {

    public static String[] alfaNumerico = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z", "1", "2", "4", "5",
        "6", "7", "8", "9", "0", ".", "&", "$", "=", "3"
    };

    public static String generaPassword() {
        String password = "";
        for (int i = 0; i < 9; i++) {
            password+= alfaNumerico[(int) (Math.random() * 40)];
        }
        return password;

    }
}
