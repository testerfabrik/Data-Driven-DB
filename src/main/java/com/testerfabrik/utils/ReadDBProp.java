package com.testerfabrik.utils;

import java.io.*;
import java.util.Properties;

public class ReadDBProp {
    Properties p = new Properties();

    public Properties getDataBaseProp() {
        try{
            // Lee el archivo de propiedades
            InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+ "/resources/database.properties"));
            // Carga todas las propiedades para poder manipularlas
            p.load(stream);
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
        return p;
    }
}
