/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.facade;


import core.dao.*;
import java.io.*;
import java.net.*;
import java.util.*;
import core.domain.*;

/**
 *
 * @author rcastro
 */
public class Servicio {        
    
    IRequestDao dao = null;
    
    public Servicio()
    {
        dao = new RequestDao();
    }
    
    public List<PortInfo> obtenerServicios(String ip, List<PortInfo> list) throws IOException
    {           
        List<Comando> comandos = this.dao.getAllCommandos();
        
        for(PortInfo bean : list)
        {
            bean.setListaComando(this.runComando(ip, bean.getPuerto(), comandos));
        }
                
        return list;
    }
    
    
    public List<Comando> runComando(String server, int port, List<Comando> comandos) 
    {
        List<Comando> listaComando = new ArrayList<Comando>();
        
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

	BufferedReader 	is = null; 
	BufferedOutputStream os = null;
        //String comando =null;
	try {
            os = new BufferedOutputStream(socket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));			
	   
            String cmd = "";
            String resultado = "";
            int count = 1;
            //verificar por tipo de servicio
            for(Comando comando:comandos){
                
                Comando bean = new Comando();                             
                cmd = comando.getCommand().replaceAll("%SERVER%", server);                                
                bean.setCommand(cmd);                
                
                resultado = commandTCP(cmd,is,os);                
                bean.setRespuesta(resultado);
                bean.setId(count);                
                listaComando.add(bean);
                count++;
            }
                
	}catch (Exception e) {
            System.out.println(e);
	}

	try {
	    socket.close();
	}catch (IOException e) {
	    System.out.println(e);
	}
	System.out.println("===============================================================");
	System.out.println("Closed ");
        
        return listaComando;
    }
    
    
    public String commandTCP(String comando, BufferedReader is, BufferedOutputStream os) 
    {     
      
       StringBuffer str = new StringBuffer("");
       
       Map<String,String> headers = null;
       try {

           System.out.println("===========================================================");

           os.write(comando.getBytes());
           System.out.print(comando);
           os.flush();
           headers = new HashMap<String,String>();
           int i=0;
                    
           for (String line; (line = is.readLine()) != null;) {
               if (line.isEmpty()) break; // Stop when headers are completed. We're not interested in all the HTML.
               System.out.println(line);
               str.append(line);
               if(i>0){
                    int index =line.indexOf(":");
                    headers.put(line.substring(0,index).trim(), line.substring(index+1).trim());                	 
               }
               i++;
           }      
       } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
       } catch (IOException e) {
                System.err.println("IOException:  " + e);
       } catch (Exception e) {
                System.err.println("Exception:  " + e);
       }	
       
       return str.toString();
    } 
}
