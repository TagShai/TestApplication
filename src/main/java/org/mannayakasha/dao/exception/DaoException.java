package org.mannayakasha.dao.exception;

/**
 * Exceptions, that can occur in Dao`s.
 *
 * @author Pavel
 * @version 1.0 03.08.2017.
 */
public class DaoException extends Exception {
    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
