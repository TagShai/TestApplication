package org.mannayakasha.service.interfaces;

import org.mannayakasha.model.User;
import org.mannayakasha.service.IService;
import org.mannayakasha.service.exception.ServiceException;

import java.util.List;

/**
 * Service interface for {@link org.mannayakasha.model.User}.
 *
 * @author Pavel
 * @version 1.0 04.08.2017.
 */
public interface IUserService extends IService {
    List<User> findAll() throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    User findByLogin(String login) throws ServiceException;

    User findById(int id) throws ServiceException;

    void save(User user) throws ServiceException;

    void delete(int id) throws ServiceException;
}
