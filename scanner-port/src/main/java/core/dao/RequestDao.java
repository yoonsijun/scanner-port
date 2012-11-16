/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

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
    
}
