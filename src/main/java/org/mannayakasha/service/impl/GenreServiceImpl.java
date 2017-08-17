package org.mannayakasha.service.impl;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IGenreDao;
import org.mannayakasha.model.Genre;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.interfaces.IGenreService;

import java.util.List;

/**
 * Implementation of {@link org.mannayakasha.service.interfaces.IGenreService} interface.
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public class GenreServiceImpl extends ServiceImpl implements IGenreService {

    private IGenreDao genreDao;

    @Override
    public List<Genre> findAll() throws ServiceException {
        try {
            genreDao = factory.createDao(IGenreDao.class);
            return genreDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 13.08.2017 it's not a good idea to return null
    }

    @Override
    public Genre findById(int id) throws ServiceException {
        try {
            genreDao = factory.createDao(IGenreDao.class);
            return genreDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 13.08.2017 It's not good to return null
    }

    @Override
    public void save(Genre genre) throws ServiceException {
        try {
            genreDao = factory.createDao(IGenreDao.class);
            if (genre.getId() != null) {
                if (genre.getName() != null) {
                    genre.setName(genre.getName());
                } else {
                    Genre oldUser = genreDao.findById(genre.getId());
                    genre.setName(oldUser.getName());
                }
                genreDao.update(genre);
            } else {
                genre.setId(genreDao.create(genre));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            genreDao = factory.createDao(IGenreDao.class);
            genreDao.delete(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
