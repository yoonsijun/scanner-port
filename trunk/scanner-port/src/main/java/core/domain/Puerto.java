/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.domain;

/**
 *
 * @author rcastro
 */
public class Puerto {
    
    private int nroPuerto;
    private Boolean indAbierto;
    
    public Puerto()
    {
    }
    
    public Puerto(int nroPuerto, Boolean indAbierto)
    {
        this.nroPuerto = nroPuerto;
        this.indAbierto = indAbierto;
    }
            

    /**
     * @return the nroPuerto
     */
    public int getNroPuerto() {
        return nroPuerto;
    }

    /**
     * @param nroPuerto the nroPuerto to set
     */
    public void setNroPuerto(int nroPuerto) {
        this.nroPuerto = nroPuerto;
    }

    /**
     * @return the indAbierto
     */
    public Boolean getIndAbierto() {
        return indAbierto;
    }

    /**
     * @param indAbierto the indAbierto to set
     */
    public void setIndAbierto(Boolean indAbierto) {
        this.indAbierto = indAbierto;
    }
}
