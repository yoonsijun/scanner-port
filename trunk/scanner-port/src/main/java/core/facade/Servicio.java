/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.facade;


import java.io.*;
import java.net.*;
import java.util.*;
import core.domain.PortInfo;

/**
 *
 * @author rcastro
 */
public class Servicio {
    
    private byte[] data = null;
    
    public List<PortInfo> obtenerServicios(String ip, List<PortInfo> list) throws IOException
    {       
       
        this.createSocket();
        return new ArrayList<PortInfo>();
    }
    
    
    private void sendMessage(DataOutputStream out) throws Exception
    {
        try 
        {

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userOutput;
            while ((userOutput = stdIn.readLine()) != null)
            {
                out.writeBytes(userOutput);
                out.write('\n');
            }
            out.flush();

        }
        catch(Exception ex)
        {
            System.out.println(ex.getStackTrace());

        }
        finally
        {
            out.close();
        }
    }


    private void readResponse(DataInputStream in) throws Exception
    {
        try
        {
            
            in.readFully(data);
            System.out.println("hola" + data);


        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());

        }
        finally
        {
            in.close();
        }
    }

    private void createSocket()
    {

        try
        {
            int port = 8081;
            InetAddress address = InetAddress.getByName("192.1.2.105");
            final Socket client = new Socket(address, port);
            final DataOutputStream out = new DataOutputStream(client.getOutputStream());
            final DataInputStream in = new DataInputStream(client.getInputStream());

            System.out.println("Successfully Connected");

            new Thread() {
                public void run() {
                    synchronized(client)
                    {
                        try {
                            while(true)
                            {
                            data = new byte[in.available()];
                            //sendMessage(out);
                            readResponse(in);
                            }
                        }
                        catch (Exception ex)
                        {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }.start();
        }
        catch(Exception ex)
        {
            ex.getStackTrace();
        }
    }

}
