package org.mannayakasha.util.validator;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.impl.mysql.DaoFactoryImpl;
import org.mannayakasha.model.User;
import org.mannayakasha.resource.RegistrationMessageManager;
import org.mannayakasha.service.IServiceFactory;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.impl.ServiceFactoryImpl;
import org.mannayakasha.service.interfaces.IUserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Validator for {@link org.mannayakasha.model.User} class.
 *
 * @author Pavel
 * @version 1.0 11.08.2017.
 */
public class UserValidator implements IValidator<User> {

    //private static final Logger LOGGER = Logger.getLogger(UserValidator.class);

    //private final String EMAIL_REGEX = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

    @Override
    public User validate(HttpServletRequest request) {

        User user = new User();

        boolean error = false;

        String parameter = request.getParameter("id");
        if (parameter != null) {
                user.setId(Integer.parseInt(parameter));
        } else {
            // TODO: 11.08.2017
        }

        parameter = request.getParameter("username");
        if (parameter != null && !parameter.isEmpty()) {
            user.setUsername(parameter);
            if (user.getUsername().length() < 8 || user.getUsername().length() > 16) {
                request.setAttribute("sizeUserFormUsername", RegistrationMessageManager.getProperty("message.size.userForm.username"));
                error = true;
            }
        } else {
            // TODO: 11.08.2017
        }

        parameter = request.getParameter("password");
        if (parameter != null && !parameter.isEmpty()) {
            user.setPassword(parameter);
            if (user.getPassword().length() < 8) {
                request.setAttribute("sizeUserFormPassword", RegistrationMessageManager.getProperty("message.size.userForm.password"));
                error = true;
            }
        } else {
            // TODO: 11.08.2017
        }

        parameter = request.getParameter("confirmPassword");
        if (parameter != null && !parameter.isEmpty()) {
            user.setConfirmPassword(parameter);
            if (!user.getConfirmPassword().equals(user.getPassword())) {
                request.setAttribute("differentUserFormPassword", RegistrationMessageManager.getProperty("message.different.userForm.password"));
                error = true;
                //return null;    // TODO: 13.08.2017 It's not good to return null. Application crash.
            }
        } else {
            // TODO: 11.08.2017
        }

        try {
            IServiceFactory serviceFactory = new ServiceFactoryImpl(new DaoFactoryImpl());
            IUserService userService = serviceFactory.getService(IUserService.class);
            if (userService.findByLogin(user.getUsername()) != null) {
                request.setAttribute("duplicateUserFormUsername", RegistrationMessageManager.getProperty("message.duplicate.userForm.username"));
                error = true;
            }
            serviceFactory.close();
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }

/*        if (!Pattern.matches(EMAIL_REGEX, user.getEmail())) {

        }*/

        if (error) {
            user = null;
        }

        return user;
    }
}
