/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.facade;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import core.domain.*;

/**
 *
 * @author rcastro
 */
public class Escaner {
    
    public List<PortInfo> escanerPuertos(String ip, int modo, int puerto, int puertoInicial, int puertoFinal)
    {
        List<PortInfo> list = new ArrayList<PortInfo>();
        
        try
        {               
            int portIni = 0;
            int portFin = 0;
            
            if(modo == 1)
            {
                portIni = puerto;
                portFin = puerto;
            }           
            if(modo == 2)
            {
                portIni = puertoInicial;
                portFin = puertoFinal;
            }
            if(modo == 3)
            {
                portIni = 1;
                portFin = 10000;
            }
            
            
            final ExecutorService es = Executors.newFixedThreadPool(20);          
            final int timeout = 200;
            
            final List<Future<Puerto>> futures = new ArrayList<Future<Puerto>>();
            
            for (int port = portIni; port <= portFin; port++) {
              futures.add(verificarPuerto(es, ip, port, timeout));
            }
            
            es.shutdown();
                            
            for (final Future<Puerto> f : futures) {
              if (f.get().getIndAbierto()) {
                
                  PortInfo entity = new PortInfo();
                  entity.setPuerto(f.get().getNroPuerto());  
                  list.add(entity);                  
              }
            }    
        }
        catch(Exception e)
        {
            System.out.println(e);
        }   
        return list;
    }  
    
    public static Future<Puerto> verificarPuerto(final ExecutorService es, final String ip, final int port, final int timeout) {
        return es.submit(new Callable<Puerto>() {
          @Override public Puerto call() {
            try {
              Socket socket = new Socket();
              socket.connect(new InetSocketAddress(ip, port), timeout);
              socket.close();
              return new Puerto(port, true);
            } catch (Exception ex) {
              return new Puerto(port, false);
            }
          }
        });
    }
    
    
}
