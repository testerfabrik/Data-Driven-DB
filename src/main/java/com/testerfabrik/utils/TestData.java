package com.testerfabrik.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class TestData {
    private Properties dbObjects = (new ReadDBProp()).getDataBaseProp();
    String scriptFilePath = System.getProperty("user.dir")+"/resources/UserInfo.sql";
    BufferedReader reader = null;
    private Connection conn = null;
    private Statement stmt;
    private ResultSet resultSet;

    @DataProvider(name = "MySQL-provider")
    public Object[][] mySQL_Data()
    {
        int rowCount = 0;
        int columnCount;
        String myData[][] = null;

        try{
            // Carga la clase driver de MySQL
            Class.forName(dbObjects.getProperty("DB_Driver")).newInstance();
            // Crea una conexión
            conn = DriverManager.getConnection(dbObjects.getProperty("DB_Host"),dbObjects.getProperty("DB_User"), dbObjects.getProperty("DB_Password"));
            // Crear el objeto Statement
            stmt = conn.createStatement();
            // Inicializar el lector del archivo sql
            reader = new BufferedReader(new FileReader(scriptFilePath));

            String line = null;
            // Leer script linea por linea
            while ((line = reader.readLine()) != null) {
                // Ejecutar el query y almacenarlo en ResultSet
                resultSet = stmt.executeQuery(line);
            }

            // Obteniendo Informacion sobre una consulta con un ResultSet
            ResultSetMetaData rsMeta = resultSet.getMetaData();
            // Obtener el total de columnas
            columnCount = rsMeta.getColumnCount();

            // Obtener el total de filas
            while (resultSet.next())
                rowCount++;

            // Darle un tamaño al arreglo basado en el número de columnas y filas de a ejecución del query
            myData = new String [rowCount][columnCount];

            // Mueva el cursor al comienzo, antes de la primera fila
            resultSet.beforeFirst();

            // Iterar sobre las columnas y filas y guardar el valor en el arreglo myData
            for (int row = 0; row<rowCount;row++){
                resultSet.next();
                for(int col = 1; col<columnCount;col++){
                    myData[row][col] = resultSet.getString(col+1);
                }
            }

            // Cerrar el lector de archivo
            reader.close();
            //Cerrar el objeto Statement
            stmt.close();
            // Cerrar la conexión con la base de datos
            conn.close();

        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return myData;
    }
}
