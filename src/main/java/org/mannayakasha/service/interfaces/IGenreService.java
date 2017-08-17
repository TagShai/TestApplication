package org.mannayakasha.service.interfaces;

import org.mannayakasha.model.Genre;
import org.mannayakasha.service.IService;
import org.mannayakasha.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for {@link org.mannayakasha.model.Genre}.
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public interface IGenreService extends IService {
    List<Genre> findAll() throws ServiceException;

    Genre findById(int id) throws ServiceException;

    void save(Genre genre) throws ServiceException;

    void delete(int id) throws ServiceException;
}
