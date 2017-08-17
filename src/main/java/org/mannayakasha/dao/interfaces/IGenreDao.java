package org.mannayakasha.dao.interfaces;

import org.mannayakasha.dao.IDao;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.model.Genre;

import java.util.List;

/**
 * Dao interface for {@link org.mannayakasha.model.Genre}.
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public interface IGenreDao extends IDao<Genre> {
    List<Genre> findAll() throws DaoException;
}
