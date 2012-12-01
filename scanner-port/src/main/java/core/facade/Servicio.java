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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rcastro
 */
public class Servicio {        
    
    IRequestDao dao = null;
    ProtocoloService protocoloService;
    public static void main(String arg[]){
//        http
//        String patron="^HTTP/1.[01] \\d{3} .*";
//        String mensaje="HtTp/1.1 500 OKrergdfger    ";
        
        //ftp
        String patron="^\\d{3}[ -].*";
        String mensaje="200-rergdfger    ";
        
        Pattern pattern = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(mensaje);
        if(matcher.find()){        
            System.out.println("match");
        }else{
            System.out.println("no match");
        }
            
    }
    public Servicio()
    {
        dao = new RequestDao();
        protocoloService = new ProtocoloService();
    }
    
    public List<PortInfo> obtenerServicios(String ip, List<PortInfo> list) throws IOException
    {           
        //comandos para verificar servicio
        List<Comando> comandos = this.dao.getAllCommandos();
        for(PortInfo portInfo : list)//itera cada puerto para identificar servicio
        {
            for(Comando comando:comandos){//por cada puerto se verifica con una lista de comandos para identificar el protocolo del servicio
                verificaServicio(ip, portInfo, comando);
                if(portInfo.getProtocolo()!=null){
                    break;
                }
            }
            
            portInfo.setListaComando(new ArrayList<Comando>());
            if(portInfo.getProtocolo()!=null){//ya se tiene el protocolo del puerto 
                //comandos para determinar que aplicación está corriendo   
                List<Comando> comandosPorProtocolo = this.dao.getComandosPorProtocolo(portInfo.getProtocolo());                
                for(Comando comando:comandosPorProtocolo){
                    //se obtiene las posibles respuestas por tipo de request
                    List<PatronRespuesta> patronesRespuesta = this.dao.getPatronesPorComando(comando);
                    verificaAplicacion(ip, portInfo, comando,patronesRespuesta);
                }

            }else{
             //como no se tiene el protocolo se asume protocolo standar según IANA(puertos conocidos)
            }

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
                
//                resultado = commandTCP(cmd,is,os);                
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
    
    public void verificaServicio(String server, PortInfo portInfo, Comando comando) 
    {
        Socket socket = null;
	int ERROR = 1;
				
	// connect to server
	try {
            socket = new Socket(server, portInfo.getPuerto());
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

	try {
            //verificar por tipo de servicio
            protocoloService.commandTCP(comando,socket,portInfo);
	}catch (Exception e) {
            System.out.println(e);
	}

	try {
	    socket.close    ();
	}catch (IOException e) {
	    System.out.println(e);
	}
    }    

    private void verificaAplicacion(String ip, PortInfo portInfo, Comando comando, List<PatronRespuesta> patrones) {
        Socket socket = null;
	int ERROR = 1;
				
	// connect to server
	try {
            socket = new Socket(ip, portInfo.getPuerto());
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

	try {
            //verificar aplicacion
                if(Constantes.HTTP.equals(portInfo.getProtocolo())){
                    comando.setCommand(comando.getCommand().replace("%SERVER%", ip));
                    protocoloService.commandHttp(comando,socket,portInfo,patrones);
                }else if(Constantes.FTP.equals(portInfo.getProtocolo())){            
                    protocoloService.commandFtp(comando,socket,portInfo,patrones);
                }else{
                    
                }
            
	}catch (Exception e) {
            System.out.println(e);
	}

	try {
	    socket.close    ();
	}catch (IOException e) {
	    System.out.println(e);
	}

    }

}
