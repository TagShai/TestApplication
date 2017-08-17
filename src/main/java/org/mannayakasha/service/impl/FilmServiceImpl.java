package org.mannayakasha.service.impl;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IFilmDao;
import org.mannayakasha.model.Film;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.interfaces.IFilmService;

import java.util.List;

/**
 * Implementation of {@link org.mannayakasha.service.interfaces.IFilmService} interface.
 *
 * @author Pavel
 * @version 1.0 14.08.2017.
 */
public class FilmServiceImpl extends ServiceImpl implements IFilmService {

    private IFilmDao filmDao;

    @Override
    public List<Film> findAll() throws ServiceException {
        try {
            filmDao = factory.createDao(IFilmDao.class);
            return filmDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 14.08.2017 it's not a good idea to return null
    }

    @Override
    public List<Film> findAllByName(String name) throws ServiceException {
        try {
            filmDao = factory.createDao(IFilmDao.class);
            return filmDao.findAllByName(name);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 15.08.2017 it's not a good idea to return null
    }

    @Override
    public Film findById(int id) throws ServiceException {
        try {
            filmDao = factory.createDao(IFilmDao.class);
            return filmDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 16.08.2017 it's not a good idea to return null
    }

    @Override
    public void save(Film film) throws ServiceException {
        try {
            filmDao = factory.createDao(IFilmDao.class);
            if (film.getId() != null) {
                if (film.getName() != null) {
                    film.setName(film.getName());
                } else {
                    Film oldFilm = filmDao.findById(film.getId());
                    film.setName(oldFilm.getName());
                }
                if (film.getDescription() != null) {
                    film.setDescription(film.getDescription());
                } else {
                    Film oldFilm = filmDao.findById(film.getId());
                    film.setDescription(oldFilm.getDescription());
                }
                if (film.getCountry() != null) {
                    film.setCountry(film.getCountry());
                } else {
                    Film oldFilm = filmDao.findById(film.getId());
                    film.setCountry(oldFilm.getCountry());
                }
                if (film.getQuality() != null) {
                    film.setQuality(film.getQuality());
                } else {
                    Film oldFilm = filmDao.findById(film.getId());
                    film.setQuality(oldFilm.getQuality());
                }
                if (film.getImage() != null) {
                    film.setImage(film.getImage());
                } else {
                    Film oldFilm = filmDao.findById(film.getId());
                    film.setImage(oldFilm.getImage());
                }
                filmDao.update(film);
            } else {
                film.setId(filmDao.create(film));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws ServiceException {

    }
}
