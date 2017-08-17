package org.mannayakasha.controller.command.admin;

import org.apache.log4j.Logger;
import org.mannayakasha.controller.command.ActionCommand;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.DaoFactoryImpl;
import org.mannayakasha.model.User;
import org.mannayakasha.resource.ConfigurationManager;
import org.mannayakasha.resource.LoginMessageManager;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IUserService;
import org.mannayakasha.util.validator.IValidator;
import org.mannayakasha.util.validator.factory.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Create or update some user.
 *
 * @author Pavel
 * @version 1.0 11.08.2017.
 */
public class UserSaveCommand extends ActionCommand {
    //private static final Logger LOGGER = Logger.getLogger(UserSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        String page = null;
        IValidator<User> validator = ValidatorFactory.createValidator(User.class);
        User user = validator.validate(request);

        if (user != null) {
            try {
                serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl());
                IUserService userService = serviceFactory.getService(IUserService.class);
                userService.save(user);
                serviceFactory.close();
            } catch (ServiceException | DaoException e) {
                e.printStackTrace();
            }
            request.setAttribute("successRegistration", LoginMessageManager.getProperty("message.success.registration"));
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            page = ConfigurationManager.getProperty("path.page.registration");
        }

        return page;
    }
}
