package org.mannayakasha.controller.command.film;

import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.DaoFactoryImpl;
import org.mannayakasha.model.Film;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IFilmService;
import org.mannayakasha.util.validator.IValidator;
import org.mannayakasha.util.validator.factory.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Create or update some film.
 * 
 * @author Pavel 
 * @version 1.0 16.08.2017.
 */
public class FilmSaveCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        
        String page = null;
        
        IValidator<Film> validator = ValidatorFactory.createValidator(Film.class);
        Film film = validator.validate(request);

        if (film != null) {
            try {
                serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl());  // TODO: 17.08.2017  
                IFilmService filmService = serviceFactory.getService(IFilmService.class);
                filmService.save(film);
                serviceFactory.close();
            } catch (ServiceException | DaoException e) {
                e.printStackTrace();
            }
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            // TODO: 16.08.2017
        }

        return page;
    }
}
