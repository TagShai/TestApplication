package org.mannayakasha.controller.command;

import org.mannayakasha.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Command, that switch user between Russian and English interfaces
 *
 * @author Pavel
 * @version 1.0 10.08.2017.
 */
public class LanguageCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String command = request.getParameter("language");
        Locale locale = null;
        switch (command.toUpperCase()) {
            case "EN":
                locale = new Locale("en");
                System.out.println(command);
                break;
            case "RU":
                locale = new Locale("ru");
                System.out.println(command);
                break;
        }
        session.setAttribute("locale", locale);

        String  page;
        page = ConfigurationManager.getProperty("path.page.login");
        //page = request.getQueryString();

        return page;
    }
}
