/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import core.domain.Comando;
import core.domain.PatronRespuesta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class RequestDao implements IRequestDao{

    public List<Comando> getComandosPorProtocolo(String protocolo) {
        ResultSet rs = null;
        List<Comando> lista= null;
        try{
            Connection con =ConnectionManager.getConnection();
            rs = con.prepareStatement("SELECT ID, PROTOCOLO,COMANDO FROM REQUEST_PROTOCOL RE WHERE RE.PROTOCOLO='"+protocolo+"' AND ESTADO='1';").executeQuery();
            
            lista = new ArrayList<Comando>();
            while(rs.next()){
                Comando objComando = new Comando();
                objComando.setId(rs.getInt(1));
                objComando.setProtocolo(rs.getString(2));
                objComando.setCommand(rs.getString(3));
                lista.add(objComando);                
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
            rs = con.prepareStatement("SELECT ID, PROTOCOLO,REQUEST,RESPONSE_PATTERN,IND_MSG_WELCOME FROM REQUEST WHERE ESTADO='1' ORDER BY ORDEN ASC;").executeQuery();
            
            lista = new ArrayList<Comando>();
            while(rs.next()){
                
                Comando bean = new Comando();
                bean.setId(rs.getInt(1));
                bean.setProtocolo(rs.getString(2));
                bean.setCommand(rs.getString(3));
                bean.setPatronRespuestaComando(rs.getString(4));
                bean.setIndMensajeBienvenida(rs.getInt(5));
                lista.add(bean);                
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public List<PatronRespuesta> getAllPatrones() {
        ResultSet rs = null;
        List<PatronRespuesta> lista= null;
        try{
            Connection con =ConnectionManager.getConnection();
            rs = con.prepareStatement("SELECT ID,PROTOCOLO,PATRON FROM PATRON_RESPONSE WHERE ESTADO='1';").executeQuery();
            
            lista = new ArrayList<PatronRespuesta>();
            while(rs.next()){
                
                PatronRespuesta bean = new PatronRespuesta();
                bean.setId(rs.getInt(1));
                bean.setProtocolo(rs.getString(2));
                bean.setPatron(rs.getString(3));                
                lista.add(bean);                
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public List<PatronRespuesta> getPatronesPorComando(Comando comando) {
        ResultSet rs = null;
        List<PatronRespuesta> lista= null;
        try{
            Connection con =ConnectionManager.getConnection();
            rs = con.prepareStatement("SELECT ID,PATRON,NOMBRE_APLICACION FROM PATRON_APP WHERE ID_COMANDO= "+comando.getId()+" AND ESTADO='1';").executeQuery();
            
            lista = new ArrayList<PatronRespuesta>();
            while(rs.next()){
                
                PatronRespuesta bean = new PatronRespuesta();
                bean.setId(rs.getInt(1));
                bean.setPatron(rs.getString(2));
                bean.setNombreAplicacion(rs.getString(3));
                lista.add(bean);                
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return lista;
    }
    
}
