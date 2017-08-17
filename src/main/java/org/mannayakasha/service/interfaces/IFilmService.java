package org.mannayakasha.service.interfaces;

import org.mannayakasha.model.Film;
import org.mannayakasha.service.IService;
import org.mannayakasha.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Service interface for {@link org.mannayakasha.model.Film}.
 *
 * @author Pavel
 * @version 1.0 14.08.2017.
 */
public interface IFilmService extends IService {
    List<Film> findAll() throws ServiceException;

    List<Film> findAllByName(String name) throws ServiceException;

    Film findById(int id) throws ServiceException;

    void save(Film film) throws ServiceException;

    void delete(int id) throws ServiceException;
}
