package org.mannayakasha.dao.impl.mysql;

import org.mannayakasha.dao.BaseDao;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IGenreDao;
import org.mannayakasha.model.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link IGenreDao} interface.
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public class GenreDaoImpl extends BaseDao<Genre> implements IGenreDao {
    @Override
    public List<Genre> findAll() throws DaoException {
        String sql = "SELECT `id`, `name` FROM `genres`";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connector.getStatement();
            resultSet = statement.executeQuery(sql);

            List<Genre> users = new ArrayList<>();

            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setName(resultSet.getString("name"));
                users.add(genre);
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
    public Integer create(Genre genre) throws DaoException {
        String sql = "INSERT INTO `genres` (`name`) VALUES(?)";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, genre.getName());
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
                // TODO: 13.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 13.08.2017 Empty catch
            }
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM `genres` WHERE `id` = ?";

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
                // TODO: 13.08.2017 Empty catch
            }
        }
    }

    @Override
    public void update(Genre genre) throws DaoException {
        String sql = "UPDATE `genres` SET `name` = ? WHERE `id` = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setInt(2, genre.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 13.08.2017 Empty catch
            }
        }
    }

    @Override
    public Genre findById(int id) throws DaoException {
        String sql = "SELECT `name` FROM `genres` WHERE `id` = ?";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Genre genre = null;
            if (resultSet.next()) {
                genre = new Genre();
                genre.setId(id);
                genre.setName(resultSet.getString("name"));
            }
            return genre;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                // TODO: 13.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 13.08.2017 Empty catch
            }
        }
    }
}
