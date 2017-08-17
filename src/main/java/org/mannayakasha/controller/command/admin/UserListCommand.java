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
import java.util.List;

/**
 * Find all users.
 *
 * @author Pavel
 * @version 1.0 08.08.2017.
 */
public class UserListCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        String page = null;

        //IUserService userService = new UserServiceImpl();
        IUserService userService = null;
        List<User> users = null;

        try {
            serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl());
            userService = serviceFactory.getService(IUserService.class);
            System.out.println("Before");
            users = userService.findAll();
            System.out.println("After");
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }

        request.setAttribute("users", users);

        page = ConfigurationManager.getProperty("path.page.users");
        serviceFactory.close();
        return page;
    }
}
