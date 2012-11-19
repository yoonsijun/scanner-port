/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.facade;

import java.net.InetAddress;
import java.net.URL;
import java.util.regex.Pattern;

/**
 *
 * @author rcastro
 */
public class Utilidades {
    
    public static String obtenerIpServer(String host)
    {
        try
        {
            if(!validarIP(host))
            {            
                InetAddress address = InetAddress.getByName(new URL(host).getHost());
                String ip = address.getHostAddress();
                return ip;
            }
            else
            {
                return host;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            return e.getMessage();
        }           
    }   
   

   public static boolean validarIP(final String ip)
   {          
      /*String IPV4_REGEX = "^(2[0-5][0-5])|(1\\d\\d)|([1-9]?\\d)\\.){3}(2[0-5][0-5])|(1\\d\\d)|([1-9]?\\d)$";
      Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
      
      return IPV4_PATTERN.matcher(ip).matches();*/
       return true;
   }
}
