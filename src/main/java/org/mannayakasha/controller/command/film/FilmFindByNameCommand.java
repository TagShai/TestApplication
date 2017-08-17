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

/**
 * Find list of films by name.
 *
 * @author Pavel
 * @version 1.0 15.08.2017.
 */
public class FilmFindByNameCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String name;
        try {
            name = (String) request.getAttribute("name");
            if (name == null) {
                name = (request.getParameter("name"));
            }
        } finally {
            // TODO: 15.08.2017 rework this
        }

        IFilmService filmService = null;
        List<Film> films = null;

        try {
            serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl()); // TODO: 15.08.2017 bad practise
            filmService = serviceFactory.getService(IFilmService.class);
            films = filmService.findAllByName(name);
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("films", films);

        page = ConfigurationManager.getProperty("path.page.main");
        serviceFactory.close();
        return page;
    }
}
