/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import core.domain.Comando;
import core.domain.PatronRespuesta;
import java.util.List;

/**
 *
 * @author Jose
 */
public interface IRequestDao {
    public List<Comando> getComandosPorProtocolo(String protocolo);
    public List<Comando> getAllCommandos();
    public List<PatronRespuesta> getAllPatrones();

    public List<PatronRespuesta> getPatronesPorComando(Comando comando);
}
