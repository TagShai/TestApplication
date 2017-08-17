package org.mannayakasha.controller.command.film;

import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.DaoFactoryImpl;
import org.mannayakasha.model.Film;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IFilmService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Find all films.
 *
 * @author Pavel
 * @version 1.0 14.08.2017.
 */
public class FilmListCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        IFilmService filmService = null;
        List<Film> films = null;

        try {
            serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl()); // TODO: 14.08.2017 bad practise
            filmService = serviceFactory.getService(IFilmService.class);
            films = filmService.findAll();
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("films", films);

        page = ConfigurationManager.getProperty("path.page.main");
        serviceFactory.close();
        return page;
    }
}
