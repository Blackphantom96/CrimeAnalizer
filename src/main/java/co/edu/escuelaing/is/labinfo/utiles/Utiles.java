package co.edu.escuelaing.is.labinfo.utiles;
import co.edu.escuelaing.is.labinfo.entities.Denuncia;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utiles {
    private static Statement conection;
    private static Map<String,List<String>> departamentos;
    private static Map<String,String> cordsByMun;

    private static String dataBase,pass,user;
    public static void getConection(){
        dataBase="DatosALaU";
        user="DatosALaUAdmin";
        pass="password";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://51.15.97.145:3306/"+dataBase,user,pass);
//here sonoo is database name, root is username and password
            conection =con.createStatement();
        }catch(Exception e){ System.out.println("Paila no se conecto"+e);}
    }

    public static ResultSet makeQuery(String columns, String table, String condition) throws SQLException {
        getConection();
            ResultSet res = conection.executeQuery("select "+columns+" from "+table+" where "+condition);
        return res;
    }

    public static void insertCrimen(Denuncia denuncia, String table) throws SQLException {
        getConection();
        conection.executeUpdate("insert  into " +table+" "+denuncia.toString());
        conection.close();
    }

    public static Map<String,List<String>> getDepartamentos() throws SQLException {
        if(departamentos!=null){
            return departamentos;
        }
        departamentos = new HashMap<>();
        cordsByMun = new HashMap<>();
        getConection();
        ResultSet res = makeQuery("*","`DepMun`","true");
        while (res.next()){
            String departamento =res.getString("departamento");
            String municipio =res.getString("municipio");
            String cords=res.getString("cords");
            if(!departamentos.containsKey(departamento)){
                departamentos.put(departamento,new ArrayList<>());
            }
            departamentos.get(departamento).add(municipio);
            cordsByMun.put(departamento+" "+municipio,cords);
        }
        conection.close();
        return departamentos;
    }

    public static String getCordsByDepMun(String s){
        System.out.println(s);
        return cordsByMun.getOrDefault(s,"4.712466774918567,-74.07932907650894");
    }
}

