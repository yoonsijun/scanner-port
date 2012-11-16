/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.facade;

import core.domain.PortInfo;
import core.domain.Puerto;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 *
 * @author rcastro
 */
public class Escaner {
    
    public List<PortInfo> escanerPuertos(String host, String modo, int puerto, int puertoInicial, int puertoFinal)
    {
        List<PortInfo> list = new ArrayList<PortInfo>();
        /*
        try
        {                
            final ExecutorService es = Executors.newFixedThreadPool(20);
            final String ip = "127.0.0.1";
            final int timeout = 200;
            final List<Future<Puerto>> futures = new ArrayList<Future<Puerto>>();
            for (int port = 1; port <= 80; port++) {
              futures.add(portIsOpen(es, ip, port, timeout));
            }
            es.shutdown();
            int openPorts = 0;
            for (final Future<Puerto> f : futures) {
              if (f.get().getIndAbierto()) {
                
                  PortInfo entity = new PortInfo();
                  entity.setPuerto(f.get().getNroPuerto());
                  entity.setProtocolo("protocolo");
                  entity.setServicio("servicio");
                  entity.setAplicacion("aplicacion");
                  entity.setVersion("version");
                  entity.setRespuesta("respuesta");
                  
                  list.add(entity);                  
              }
            }     
        }
        catch(Exception ex)
        {
        
        }   
        */
        
        for(int i =0;i<5;i++){
                  PortInfo entity = new PortInfo();
                  entity.setPuerto(1045);
                  entity.setProtocolo("protocolo");
                  entity.setServicio("servicio");
                  entity.setAplicacion("aplicacion");
                  entity.setVersion("version");
                  entity.setRespuesta("respuesta");
                  
                  list.add(entity);                                      
        }
            
        return list;
    }  
    
    public static Future<Puerto> portIsOpen(final ExecutorService es, final String ip, final int port, final int timeout) {
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
