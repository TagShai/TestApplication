package org.mannayakasha.controller.command.admin;

import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.DaoFactoryImpl;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Delete some user.
 *
 * @author Pavel
 * @version 1.0 10.08.2017.
 */
public class UserDeleteCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        Integer id = Integer.parseInt(request.getParameter("id"));

        try {
            serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl());
            IUserService userService = serviceFactory.getService(IUserService.class);
            userService.delete(id);
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }

        page = ConfigurationManager.getProperty("path.page.main");
        serviceFactory.close();
        return page;
    }
}
