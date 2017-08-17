package org.mannayakasha.service.impl;

import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IUserDao;
import org.mannayakasha.model.User;
import org.mannayakasha.service.exception.ServiceException;
import org.mannayakasha.service.interfaces.IUserService;
import org.mannayakasha.util.hasher.Hasher;

import java.util.List;

/**
 * Implementation of {@link IUserService} interface.
 *
 * @author Pavel
 * @version 1.0 09.08.2017.
 */
public class UserServiceImpl extends ServiceImpl implements IUserService {

    private IUserDao userDao;

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            userDao = factory.createDao(IUserDao.class);
            return userDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 13.08.2017 it's not a good idea to return null
    }

    @Override
    public User findByLoginAndPassword(String username, String password) throws ServiceException {
        try {
            userDao = factory.createDao(IUserDao.class);
            User user = userDao.findByLoginAndPassword(username, password);//Hasher.md5(password));
            return user;
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 11.08.2017 Return null is not good
    }

    @Override
    public User findByLogin(String username) throws ServiceException {
        try {
            userDao = factory.createDao(IUserDao.class);
            User user = userDao.findByLogin(username);
            return user;
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 11.08.2017 Return null is not good
    }

    @Override
    public User findById(int id) throws ServiceException {
        try {
            userDao = factory.createDao(IUserDao.class);
            return userDao.findById(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null; // TODO: 10.08.2017 It's not good to return null
    }

/*    @Override
    public void save(User user) throws ServiceException {
        System.out.println("We are here");
        try {
            userDao = factory.createDao(IUserDao.class);
            if (user.getId() != null) {
                System.out.println("Update user");
                userDao.update(user);
            } else {
                user.setId(userDao.create(user));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void save(User user) throws ServiceException {
        try {
            userDao = factory.createDao(IUserDao.class);
            if (user.getId() != null) {
                if (user.getPassword() != null) {
                    user.setPassword(user.getPassword()); // (Hasher.md5(user.getPassword()));
                } else {
                    User oldUser = userDao.findById(user.getId());
                    user.setPassword(oldUser.getPassword());
                }
                userDao.update(user);
            } else {
                //user.setPassword(new String()); // (Hasher.md5(new String()));
                user.setId(userDao.create(user));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            userDao = factory.createDao(IUserDao.class);
            userDao.delete(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
