/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

/**
 *
 * @author rcastro
 */
public class PortInfo {
    
    private String protocolo;
    private Integer puerto;
    private String servicio;
    private String aplicacion;  
    private String version;
    private String respuesta;

    /**
     * @return the protocolo
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * @param protocolo the protocolo to set
     */
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    /**
     * @return the puerto
     */
    public Integer getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the aplicacion
     */
    public String getAplicacion() {
        return aplicacion;
    }

    /**
     * @param aplicacion the aplicacion to set
     */
    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
}
