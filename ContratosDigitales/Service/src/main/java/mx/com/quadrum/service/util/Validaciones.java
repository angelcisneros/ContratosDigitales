/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service.util;

import java.util.regex.Pattern;


/**
 *
 * @author vcisneros
 */
public class Validaciones {
    public static Pattern patRFC = Pattern.compile("^[A-Z,Ã‘,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]{3}$");
    public static boolean isNullOrEmpty(String cadena) {
        if (cadena == null) {
            return true;
        }
        return cadena.equalsIgnoreCase("");
    }

    public static boolean esRfcValido(String cadena) {
        return patRFC.matcher(cadena).matches();
    }

}
