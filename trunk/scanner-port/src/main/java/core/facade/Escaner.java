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
    
    public List<PortInfo> escanerPuertos(String ip, int modo, int puerto, int puertoInicial, int puertoFinal, String ptos)
    {
        List<PortInfo> list = new ArrayList<PortInfo>();
        
        try
        {               
            int portIni = 0;
            int portFin = 0;
            Boolean flag = false;
            String[] puertos = null;
            
            if(modo == 1)
            {
                portIni = puerto;
                portFin = puerto;
            }
            if(modo == 2)
            {
                puertos = ptos.split(",");
                flag = true;
            }
            if(modo == 3)
            {
                portIni = puertoInicial;
                portFin = puertoFinal;
            }            
            if(modo == 4)
            {
                portIni = 1;
                portFin = 10000;
            }
            
            
            final ExecutorService es = Executors.newFixedThreadPool(20);          
            final int timeout = 200;
            
            final List<Future<Puerto>> futures = new ArrayList<Future<Puerto>>();
            
            if(!flag)
            {            
                for (int port = portIni; port <= portFin; port++) {
                  futures.add(verificarPuerto(es, ip, port, timeout));
                }
            }
            else
            {
                for(String port: puertos)
                {
                    futures.add(verificarPuerto(es, ip, Integer.parseInt(port), timeout));
                }
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
              socket.connect(new InetSocketAddress(ip, port));
              socket.close();
              return new Puerto(port, true);
            } catch (Exception ex) {
              return new Puerto(port, false);
            }
          }
        });
    }
    
    
}
