package org.mannayakasha.dao.interfaces;

import org.mannayakasha.dao.IDao;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.model.User;

import java.util.List;

/**
 * Dao interface for {@link org.mannayakasha.model.User}.
 *
 * @author Pavel
 * @version 1.0 03.08.2017.
 */
public interface IUserDao extends IDao<User> {
    List<User> findAll() throws DaoException;

    User findByLoginAndPassword(String username, String password) throws DaoException;

    User findByLogin(String username) throws DaoException;
}
