/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import core.domain.Comando;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class RequestDao implements IRequestDao{

    public List<String> getComandosPorProtocolo(String protocolo) {
        ResultSet rs = null;
        List<String> lista= null;
        try{
            Connection con =ConnectionManager.getConnection();
            rs = con.prepareStatement("SELECT COMANDO FROM REQUEST RE WHERE RE.PROTOCOLO='http';").executeQuery();
            
            lista = new ArrayList<String>();
            while(rs.next()){
                lista.add(rs.getString(1));                
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    
    public List<Comando> getAllCommandos() {
        ResultSet rs = null;
        List<Comando> lista= null;
        try{
            Connection con =ConnectionManager.getConnection();
            rs = con.prepareStatement("SELECT ID, PROTOCOLO, COMANDO FROM REQUEST;").executeQuery();
            
            lista = new ArrayList<Comando>();
            while(rs.next()){
                
                Comando bean = new Comando();
                bean.setId(rs.getInt(1));
                bean.setProtocolo(rs.getString(2));
                bean.setCommand(rs.getString(3));                
                lista.add(bean);                
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    
}
