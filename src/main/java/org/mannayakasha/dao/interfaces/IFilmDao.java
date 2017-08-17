package org.mannayakasha.dao.interfaces;

import org.mannayakasha.dao.IDao;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.model.Film;

import java.util.List;
import java.util.Map;

/**
 * Dao interface for {@link org.mannayakasha.model.Film}.
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public interface IFilmDao extends IDao<Film> {
    List<Film> findAll() throws DaoException;

    List<Film> findAllByName(String name) throws DaoException;
}
