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

/**
 * Created by Pavel on 16.08.2017.
 */
public class FilmFindCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        try {
            Integer id = (Integer)request.getAttribute("id");
            if(id == null) {
                id = Integer.parseInt(request.getParameter("id"));
            }

            IFilmService filmService = null;
            Film film = null;

            try {
                serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl());
                filmService = serviceFactory.getService(IFilmService.class);
                film = filmService.findById(id);
            } catch (ServiceException | DaoException e) {
                e.printStackTrace();
            }

            if(film != null) {
                request.setAttribute("someFilm", film);
            }
        } catch(NumberFormatException e) {
            // TODO: 16.08.2017 Empty catch
        }

        //request.setAttribute("products", products);
        page = ConfigurationManager.getProperty("path.page.film_edit");
        serviceFactory.close();
        return page;
    }
}
