/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import java.util.List;

/**
 *
 * @author Jose
 */
public interface IRequestDao {
    public List<String> getComandosPorProtocolo(String protocolo);
}
