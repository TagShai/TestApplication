package org.mannayakasha.util.validator;

import org.mannayakasha.model.Film;
import org.mannayakasha.model.Genre;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Validator for {@link org.mannayakasha.model.Film} class.
 *
 * @author Pavel
 * @version 1.0 16.08.2017.
 */
public class FilmValidator implements IValidator<Film> {
    //private static final Logger LOGGER = Logger.getLogger(FilmValidator.class);

    @Override
    public Film validate(HttpServletRequest request) {

        Film film = new Film();

        String parameter = request.getParameter("id");
        if (parameter != null) {
            film.setId(Integer.parseInt(parameter));
        } else {
            // TODO: 16.08.2017
        }

        parameter = request.getParameter("name");
        if (parameter != null && !parameter.isEmpty()) {
            film.setName(parameter);
        } else {
            // TODO: 16.08.2017
        }

        parameter = request.getParameter("description");
        if (parameter != null && !parameter.isEmpty()) {
            film.setDescription(parameter);
        } else {
            // TODO: 16.08.2017
        }

        parameter = request.getParameter("country");
        if (parameter != null && !parameter.isEmpty()) {
            film.setCountry(parameter);
        } else {
            // TODO: 16.08.2017
        }

        parameter = request.getParameter("quality");
        if (parameter != null && !parameter.isEmpty()) {
            film.setQuality(parameter);
        } else {
            // TODO: 16.08.2017
        }

        parameter = request.getParameter("image");
        if (parameter != null && !parameter.isEmpty()) {
            film.setImage(parameter);
        } else {
            // TODO: 16.08.2017
        }

        Enumeration params = request.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = (String)params.nextElement();
            System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }

        return film;
    }
}
