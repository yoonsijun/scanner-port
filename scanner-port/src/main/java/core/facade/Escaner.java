/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.facade;

import core.dao.IRequestDao;
import core.dao.RequestDao;
import core.domain.PortInfo;
import core.domain.Puerto;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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
    public IRequestDao iRequestDao; 
    
    public Escaner(){
        iRequestDao = new RequestDao();
    }
    public void test(){
        int port = 80;
	String server = "74.125.140.94";
        List<String> lista =iRequestDao.getComandosPorProtocolo("http");
        procesar(server, port, lista);
//        for(String comando: lista){
//            System.out.println("comando:"+comando);
//        }
    }
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
    
        public void procesar(String server,int port, List<String> comandos) {

		Socket socket = null;
		int ERROR = 1;
		
	
		
		// connect to server
		try {
		    socket = new Socket(server, port);
		    System.out.println("IP y pto LOCAL " +socket.getLocalAddress() +":" + socket.getLocalPort());
		    System.out.println("Connected with server " +socket.getInetAddress() +":" + socket.getPort());
		}
		catch (UnknownHostException e) {
		    System.out.println(e);
		    System.exit(ERROR);
		}
		catch (IOException e) {
		    System.out.println(e);
		    System.exit(ERROR);
		}

		BufferedReader 			is = null; 
		BufferedOutputStream 	os = null;
//		String comando =null;
		try {
        	os = new BufferedOutputStream(socket.getOutputStream());
                is = new BufferedReader(new InputStreamReader(socket.getInputStream()));			
	        //comando uno
                for(String comando:comandos){
                    comando =comando.replaceAll("%SERVER%", server);
                    commandTCP(comando,is,os);    
                }
                
		}catch (Exception e) {
		    System.out.println(e);
		}

		try {
		    socket.close();
		}catch (IOException e) {
		    System.out.println(e);
		}
		System.out.println("=============================================================================================");
		System.out.println("Closed ");
    }

	public void commandTCP(String comando, BufferedReader 			is, BufferedOutputStream 	os) {
		Map<String,String> headers = null;
        try {

        	System.out.println("=============================================================================================");
        	
        	os.write(comando.getBytes());
        	System.out.print(comando);
        	os.flush();
        	headers = new HashMap<String,String>();
        	int i=0;
        	 for (String line; (line = is.readLine()) != null;) {
                 if (line.isEmpty()) break; // Stop when headers are completed. We're not interested in all the HTML.
                 System.out.println(line);
                 if(i>0){
                   int index =line.indexOf(":");
                   headers.put(line.substring(0,index).trim(), line.substring(index+1).trim());                	 
                 }
                 i++;

             }      	                                       

//        	 System.out.println(headers.toString());
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        } catch (Exception e) {
            System.err.println("Exception:  " + e);
        }
		
	}    

    
    
}
