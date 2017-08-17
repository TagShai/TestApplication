package org.mannayakasha.controller.command;

import org.mannayakasha.controller.command.film.FilmListCommand;
import org.mannayakasha.controller.command.genre.GenreListCommand;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.DaoFactoryImpl;
import org.mannayakasha.model.AccessType;
import org.mannayakasha.model.User;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.resource.LoginMessageManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Login user into the system.
 *
 * @author Pavel
 * @version 1.0 11.08.2017.
 */
public class LoginCommand extends ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        String username = request.getParameter("login");
        String password = request.getParameter("password");

        User user = null;

        if(username != null && password != null) {
            try {
                serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl());
                IUserService service = serviceFactory.getService(IUserService.class);
                user = service.findByLoginAndPassword(username, password);
            } catch (ServiceException | DaoException e) {
                e.printStackTrace();
            }
        }

        FilmListCommand filmListCommand = new FilmListCommand();
        filmListCommand.execute(request);

        GenreListCommand genreListCommand = new GenreListCommand();
        genreListCommand.execute(request);

        if (user != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", user.getUsername());
            httpSession.setAttribute("accessType", user.getAccessType());

            if (user.getAccessType().getAccess().equals(AccessType.ADMIN.getAccess())) {

            } else if (user.getAccessType().getAccess().equals(AccessType.USER.getAccess())) {

            }
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", LoginMessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }

        serviceFactory.close();
        return page;
    }
}