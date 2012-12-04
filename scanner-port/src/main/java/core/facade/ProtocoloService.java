/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.facade;

import core.domain.Comando;
import core.domain.PatronRespuesta;
import core.domain.PortInfo;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jose
 */
public class ProtocoloService {
    
    public void commandTCP(Comando comando, Socket socket,PortInfo portInfo){

        BufferedReader          is;
        BufferedOutputStream    os;
        StringBuffer            str;
        String                  linea;

        try {
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new BufferedOutputStream(socket.getOutputStream());
            str = new StringBuffer("");
            
            System.out.println("===========================================================");
            if(comando.getIndMensajeBienvenida()==0){// si no hay mensaje de bienvenida primero se envía el request
                os.write(comando.getCommand().getBytes());
                System.out.print(comando.getCommand() + "\n");
                os.flush();                
            }

            //Se obtiene la primera línea para identificar a que protocolo pertenece
            linea = is.readLine();
            System.out.println(linea);
            if(linea !=null){
                str.append(linea);
                if(comando.getIndMensajeBienvenida()==0){
                //tras obtener el response del request del comando se evalua a que protocolo pertenece
                    Pattern pattern = Pattern.compile(comando.getPatronRespuestaComando(), Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(linea);
                    if(matcher.find()){
                        portInfo.setProtocolo(comando.getProtocolo());
                        portInfo.setComando(comando);
                    }
                }else{
                    //leyendo mensaje de bienvenda
                    boolean esFinDeLinea = false;
                    do{
                        int ind =linea.indexOf("-");
                        if(ind==3){
                            linea = is.readLine();
                            System.out.println(linea);
                            str.append(linea);                        
                        }else{
                            esFinDeLinea = true;
                        }                    
                    }while(!esFinDeLinea);                    
                    //fin de leyendo mensaje de bienvenda
                    
                    //mandando primer request al servidor
                    os.write(comando.getCommand().getBytes());
                    System.out.print(comando.getCommand() + "\n");
                    os.flush();                
                    
                    //leer respuesta del request
                    linea = is.readLine();
                    System.out.println(linea);
                    esFinDeLinea = false;
                    do{
                        int ind =linea.indexOf("-");
                        if(ind==3){
                            linea = is.readLine();
                            System.out.println(linea);
                            str.append(linea);                        
                        }else{
                            esFinDeLinea = true;
                        }                    
                    }while(!esFinDeLinea);
                    //fin de leer respuesta del request
                    //validar con expresion la respuesta
                    Pattern pattern = Pattern.compile(comando.getPatronRespuestaComando(), Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(linea);
                    if(matcher.find()){
                        portInfo.setProtocolo(comando.getProtocolo());
                        portInfo.setComando(comando);
                    }                    
                }
                
                
                if(Constantes.HTTP.equals(portInfo.getProtocolo())){
                    while(!(linea = is.readLine()).isEmpty()){
                        System.out.println(linea);
                        str.append(linea);        
                    }
                }else{

                }
            }
            os.close();
            is.close();
            comando.setRespuesta(str.toString());
            System.out.println("===============================================================");
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    void commandHttp(Comando comando, Socket socket, PortInfo portInfo, List<PatronRespuesta> patrones) {

        BufferedReader          is;
        BufferedOutputStream    os;
        StringBuffer            str;
        String                  linea;

        try {
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new BufferedOutputStream(socket.getOutputStream());
            str = new StringBuffer("");
            
            System.out.println("===========================================================");
            os.write(comando.getCommand().getBytes());
            System.out.print(comando.getCommand() + "\n");
            os.flush();
            
            //Se obtiene la primera línea para identificar a que protocolo pertenece
            while(!(linea = is.readLine()).isEmpty()){
                System.out.println(linea);
                str.append(linea+Constantes.BR);        
            }
            comando.setRespuesta(str.toString());            

            for(PatronRespuesta objPatronRespuesta:patrones){
                    Pattern pattern = Pattern.compile(objPatronRespuesta.getPatron(), Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(comando.getRespuesta());
                    if(matcher.find()){
                        comando.setPatronRespuesta(objPatronRespuesta);
                        break;
                    }
            }

            portInfo.getListaComando().add(comando);
            os.close();
            is.close();

            System.out.println("===============================================================");
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }        

    }

    void commandFtp(Comando comando, Socket socket, PortInfo portInfo, List<PatronRespuesta> patrones) {

        BufferedReader          is;
        BufferedOutputStream    os;
        StringBuffer            str;
        String                  linea;

        try {
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new BufferedOutputStream(socket.getOutputStream());
            str = new StringBuffer("");
            
            System.out.println("===========================================================");            
            
                    //leyendo mensaje de bienvenda
                    linea = is.readLine();
                    System.out.println(linea);
                    str.append(linea+Constantes.BR);
                    boolean esFinDeLinea = false;
                    do{
                        int ind =linea.indexOf("-");
                        if(ind==3){
                            linea = is.readLine();
                            System.out.println(linea);
                            str.append(linea+Constantes.BR);
                        }else{
                            esFinDeLinea = true;
                        }                    
                    }while(!esFinDeLinea);                    
                    //fin de leyendo mensaje de bienvenda
                    
                    //mandando primer request al servidor
                    os.write(comando.getCommand().getBytes());
                    System.out.print(comando.getCommand() + "\n");
                    os.flush();                
                    
                    //leer respuesta del request
                    linea = is.readLine();
                    System.out.println(linea);
                    esFinDeLinea = false;
                    do{
                        int ind =linea.indexOf("-");
                        if(ind==3){
                            linea = is.readLine();
                            System.out.println(linea);
                            str.append(linea+Constantes.BR);          
                        }else{
                            esFinDeLinea = true;
                        }                    
                    }while(!esFinDeLinea);
                    //fin de leer respuesta del request            
            comando.setRespuesta(str.toString());            

            for(PatronRespuesta objPatronRespuesta:patrones){
                    Pattern pattern = Pattern.compile(objPatronRespuesta.getPatron(), Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(comando.getRespuesta());
                    if(matcher.find()){
                        comando.setPatronRespuesta(objPatronRespuesta);
                        break;
                    }
            }
            portInfo.getListaComando().add(comando);
            os.close();
            is.close();
            System.out.println("===============================================================");
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }        
        
    }
}
