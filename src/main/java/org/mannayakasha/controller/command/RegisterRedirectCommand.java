package org.mannayakasha.controller.command;

import org.mannayakasha.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Redirect user to the register.jsp
 *
 * @author Pavel
 * @version 1.0 12.08.2017.
 */
public class RegisterRedirectCommand extends ActionCommand {
    //private static final Logger LOGGER = Logger.getLogger(RegisterRedirectCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        page = ConfigurationManager.getProperty("path.page.registration");
        return page;
    }
}
