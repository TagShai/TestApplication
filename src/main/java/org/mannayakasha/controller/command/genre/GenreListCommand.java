package org.mannayakasha.controller.command.genre;

import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.DaoFactoryImpl;
import org.mannayakasha.model.Genre;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IGenreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Find all genres.
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public class GenreListCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        IGenreService genreService = null;
        List<Genre> genres = null;

        try {
            serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl()); // TODO: 13.08.2017 bad practise
            genreService = serviceFactory.getService(IGenreService.class);
            genres = genreService.findAll();
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("genres", genres);

        page = ConfigurationManager.getProperty("path.page.main");
        serviceFactory.close();
        return page;
    }
}
