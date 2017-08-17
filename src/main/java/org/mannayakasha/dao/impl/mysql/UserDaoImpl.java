package org.mannayakasha.dao.impl.mysql;

import org.mannayakasha.dao.BaseDao;
import org.mannayakasha.dao.DBConnector;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IUserDao;
import org.mannayakasha.model.AccessType;
import org.mannayakasha.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link IUserDao} interface.
 *
 * @author Pavel
 * @version 1.0 03.08.2017.
 */
public class UserDaoImpl extends BaseDao<User> implements IUserDao {

    @Override
    public List<User> findAll() throws DaoException {
        String sql = "SELECT `id`, `username`, `password` FROM `users`";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connector.getStatement();
            resultSet = statement.executeQuery(sql);

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connector.closeStatement(statement);
        }
    }

    @Override
    public Integer create(User user) throws DaoException {

        String sql = "INSERT INTO `users` (`username`, `password`, `accessType`) VALUES(?, ?, ?)";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, AccessType.USER.getAccess());
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                // TODO: 10.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 10.08.2017 Empty catch
            }
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM `users` WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 10.08.2017 Empty catch
            }
        }
    }

    @Override
    public void update(User user) throws DaoException {
        String sql = "UPDATE `users` SET `username` = ?, `password` = ? WHERE `id` = ?";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 10.08.2017 Empty catch
            }
        }
    }

    @Override
    public User findByLoginAndPassword(String username, String password) throws DaoException {
        String sql = "SELECT `id`, `accessType` FROM `users` WHERE `username` = ? AND `password` = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(username);
                user.setPassword(password);
                user.setAccessType(resultSet.getString("accessType"));
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                // TODO: 11.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch(NullPointerException e) {
                // TODO: 11.08.2017 Empty catch
            }
        }
    }

    @Override
    public User findByLogin(String username) throws DaoException {
        String sql = "SELECT `id` FROM `users` WHERE `username` = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(username);
            }
            return user;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                // TODO: 11.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch(NullPointerException e) {
                // TODO: 11.08.2017 Empty catch
            }
        }
    }

    @Override
    public User findById(int id) throws DaoException {
        String sql = "SELECT `username`, `password` FROM `users` WHERE `id` = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                // TODO: 10.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 10.08.2017 Empty catch
            }
        }
    }
}
