/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vcisneros
 */
public class ManejadorArchivos {

    public static boolean eliminarDirectorio(String path) {
        File logo = new File(path);
        if (!logo.isDirectory()) {
            return false;
        }
        if (!logo.exists()) {
            return false;
        }
        borraArchivos(logo);
        logo.deleteOnExit();
        return logo.delete();
    }

    private static void borraArchivos(File directortioLogo) {
        File[] ficheros = directortioLogo.listFiles();

        for (int i = 0; i < ficheros.length; i++) {
            if (ficheros[i].isDirectory()) {
                borraArchivos(ficheros[i]);
            } else {
                ficheros[i].delete();
            }
        }

    }

    public static byte[] ImagenToArreglo(String path) throws Exception {
        File logo = new File(path);
        FileInputStream fis = null;
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(logo);
            bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            bytes = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger("ByteArrayImageSource").log(Level.SEVERE, null, ex);
        } finally {
            fis.close();
            bos.close();
            logo = null;
            fis = null;
            bos = null;
        }
        return bytes;
    }

    public static String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public static void escribirArchivo(File f, String contenido) {

        try {
            FileWriter w = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            wr.write(contenido);//escribimos en el archivo 
            //wr.append(" - y aqui continua"); //concatenamos en el archivo sin borrar lo existente
            //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
            //de no hacerlo no se escribirá nada en el archivo
            wr.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            f = null;
        }
    }

    public static boolean escribirArchivo(File file, byte[] contenido) {
        boolean correcto = false;
        try {
            OutputStream out = new FileOutputStream(file);
            out.write(contenido);
            out.close();
            correcto = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return correcto;

    }

    public static boolean crearArchivoContenido(String path, byte[] contenido) {
        boolean correcto = false;
        File file = new File(path);
        try {
            file.createNewFile();
            OutputStream out = new FileOutputStream(file);
            out.write(contenido);
            out.close();
            correcto = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return correcto;

    }

//
//    public static byte[] archivoToArray(String pathFile) {
//        Path path;        
//        path = Paths.get(pathFile);
//        try {
//            return Files.readAllBytes(path);
//        } catch (IOException ex) {
//            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }
    public static byte[] convierteArchivoToArregloBytes(File file) {
        FileInputStream fis;
        byte[] archivo = null;
        try {
            fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.

            }
            archivo = bos.toByteArray();
            bos.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        return archivo;
    }

}
