package org.mannayakasha.controller.command;

import org.mannayakasha.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
/**
 * Command wasn't specified.
 *
 * @author Pavel
 * @version 1.0 11.08.2017.
 */
public class EmptyCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.login"); // path.page.error
        //request.getSession().invalidate();
        return page;
    }
}