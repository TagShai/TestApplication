package org.mannayakasha.controller.command.admin;

import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.DaoFactoryImpl;
import org.mannayakasha.model.User;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Find some user.
 *
 * @author Pavel
 * @version 1.0 10.08.2017.
 */
public class UserFindCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        try {
            Integer id = (Integer)request.getAttribute("id");
            if(id == null) {
                id = Integer.parseInt(request.getParameter("id"));
            }

            IUserService userService = null;
            User user = null;

            try {
                serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl());
                userService = serviceFactory.getService(IUserService.class);
                user = userService.findById(id);
            } catch (ServiceException | DaoException e) {
                e.printStackTrace();
            }

            if(user != null) {
                request.setAttribute("someUser", user);
            }
        } catch(NumberFormatException e) {
            // TODO: 10.08.2017 Empty catch
        }

        //request.setAttribute("products", products);
        page = ConfigurationManager.getProperty("path.page.main");
        serviceFactory.close();
        return page;
    }
}
