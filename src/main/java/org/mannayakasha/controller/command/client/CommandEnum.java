package org.mannayakasha.controller.command.client;

import org.mannayakasha.controller.command.*;
import org.mannayakasha.controller.command.admin.UserDeleteCommand;
import org.mannayakasha.controller.command.admin.UserFindCommand;
import org.mannayakasha.controller.command.admin.UserListCommand;
import org.mannayakasha.controller.command.admin.UserSaveCommand;
import org.mannayakasha.controller.command.film.FilmFindByNameCommand;
import org.mannayakasha.controller.command.film.FilmFindCommand;
import org.mannayakasha.controller.command.film.FilmListCommand;
import org.mannayakasha.controller.command.film.FilmSaveCommand;
import org.mannayakasha.controller.command.genre.GenreListCommand;


/**
 * Enum, that contains all commands.
 *
 * @author Pavel
 * @version 1.0 08.08.2017.
 */
public enum CommandEnum {
    LOGIN {{
        this.command = new LoginCommand();
    }}, LOGOUT {{
        this.command = new LogoutCommand();
    }}, USER_LIST {{
        this.command = new UserListCommand();
    }}, USER_FIND {{
        this.command = new UserFindCommand();
    }}, USER_SAVE {{
        this.command = new UserSaveCommand();
    }}, USER_DELETE {{
        this.command = new UserDeleteCommand();
    }}, RU {{
        this.command = new LanguageCommand();
    }}, EN {{
        this.command = new LanguageCommand();
    }}, REGISTRATION_REDIRECT {{
        this.command = new RegisterRedirectCommand();
    }}, GENRE_LIST {{
        this.command = new GenreListCommand();
    }}, FILM_LIST {{
        this.command = new FilmListCommand();
    }}, FILM_FIND_LIST_BY_NAME {{
        this.command = new FilmFindByNameCommand();
    }}, FILM_FIND {{
        this.command = new FilmFindCommand();
    }}, FILM_SAVE {{
        this.command = new FilmSaveCommand();
    }};

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}