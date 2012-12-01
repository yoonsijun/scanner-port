/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

/**
 *
 * @author rcastro
 */
public class Comando {
    
    private int id;
    private String protocolo;
    private String command;
    private String patronRespuestaComando;
    private int indMensajeBienvenida;
    private String respuesta;
    private PatronRespuesta patronRespuesta;

    public int getIndMensajeBienvenida() {
        return indMensajeBienvenida;
    }

    public void setIndMensajeBienvenida(int indMensajeBienvenida) {
        this.indMensajeBienvenida = indMensajeBienvenida;
    }

    public String getPatronRespuestaComando() {
        return patronRespuestaComando;
    }

    public void setPatronRespuestaComando(String patronRespuestaComando) {
        this.patronRespuestaComando = patronRespuestaComando;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

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
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command = command;
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

    public PatronRespuesta getPatronRespuesta() {
        return patronRespuesta;
    }

    public void setPatronRespuesta(PatronRespuesta patronRespuesta) {
        this.patronRespuesta = patronRespuesta;
    }


    
}
