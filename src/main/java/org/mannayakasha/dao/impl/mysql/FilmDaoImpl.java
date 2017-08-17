package org.mannayakasha.dao.impl.mysql;

import org.mannayakasha.dao.BaseDao;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IFilmDao;
import org.mannayakasha.model.Film;
import org.mannayakasha.model.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link org.mannayakasha.dao.interfaces.IFilmDao} interface.
 *
 * @author Pavel
 * @version 1.0 13.08.2017.
 */
public class FilmDaoImpl extends BaseDao<Film> implements IFilmDao {
    @Override
    public List<Film> findAll() throws DaoException {
        String sqlAll = "SELECT * FROM `films` ORDER BY `name` DESC";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connector.getStatement();
            resultSet = statement.executeQuery(sqlAll);

            List<Film> films = new ArrayList<>(); // All films

            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt("id"));
                film.setName(resultSet.getString("name"));
                film.setDescription(resultSet.getString("description"));
                film.setCountry(resultSet.getString("country"));
                film.setQuality(resultSet.getString("quality"));
                film.setImage(resultSet.getString("image"));
                films.add(film);
            }

            String sql = "SELECT genres.name AS genre FROM films, genres, film_genres "
                    + "WHERE films.id = film_genres.film_id AND genres.id = film_genres.genre_id "
                    + "AND films.name = ";

            try {
                for (Film film : films) {
                    String name = film.getName();
                    //statement = connector.getStatement();
                    resultSet = statement.executeQuery(sql + "\'" + name + "\'");

                    while (resultSet.next()) {
                        Genre genre = new Genre();
                        genre.setName(resultSet.getString("genre"));
                        film.getGenres().add(genre);
                    }
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

            return films;
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
    public Integer create(Film film) throws DaoException {

        String sql = "INSERT INTO `films` (`name`, `description`, `country`, `quality`, `image`) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, film.getName());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setString(3, film.getCountry());
            preparedStatement.setString(4, film.getQuality());
            preparedStatement.setString(5, film.getImage());
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
                // TODO: 16.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 16.08.2017 Empty catch
            }
        }
    }

    @Override
    public void delete(int id) throws DaoException {

    }

    @Override
    public void update(Film film) throws DaoException {
        String sql = "UPDATE `films` SET `name` = ?, `description` = ?, `country` = ?, `quality` = ?, `image` = ? WHERE `id` = ?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setString(1, film.getName());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setString(3, film.getCountry());
            preparedStatement.setString(4, film.getQuality());
            preparedStatement.setString(5, film.getImage());
            preparedStatement.setInt(6, film.getId());
            preparedStatement.execute();

            //String sqlUpdateGenres = "UPDATE `film_genres` SET `film_genres.genre_id` = ? WHERE `film_genres.film_id` = ? AND `film_genres.genre_id` = ?;";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 16.08.2017 Empty catch
            }
        }
    }

    @Override
    public Film findById(int id) throws DaoException {
        String sql = "SELECT * FROM `films` WHERE `id` = ?";
/*        String sql = "SELECT films.name, films.description, films.country, films.quality, films.image, genres.name AS genre" +
                " FROM films, genres, film_genres WHERE films.id = 1" +
                " AND films.id = film_genres.film_id AND genres.id = film_genres.genre_id";*/

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Film film = null;
            if (resultSet.next()) {
                film = new Film();
                film.setId(id);
                film.setName(resultSet.getString("name"));
                film.setDescription(resultSet.getString("description"));
                film.setDescription(resultSet.getString("description"));
                film.setCountry(resultSet.getString("country"));
                film.setQuality(resultSet.getString("quality"));
                film.setImage(resultSet.getString("image"));
            }

            String sqlGenres = "SELECT genres.name AS genre FROM films, genres, film_genres "
                    + "WHERE films.id = film_genres.film_id AND genres.id = film_genres.genre_id "
                    + "AND films.name = ";

            try {
                preparedStatement = connector.getPreparedStatement(sqlGenres);

                String name = film.getName();
                //statement = connector.getStatement();
                resultSet = preparedStatement.executeQuery(sqlGenres + "\'" + name + "\'");

                while (resultSet.next()) {
                    Genre genre = new Genre();
                    genre.setName(resultSet.getString("genre"));
                    film.getGenres().add(genre);
                }

            } catch (SQLException e) {
                throw new DaoException(e);
            }
            return film;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                // TODO: 16.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch (NullPointerException e) {
                // TODO: 16.08.2017 Empty catch
            }
        }
    }

    @Override
    public List<Film> findAllByName(String name) throws DaoException {
        String sql = "SELECT * FROM `films` WHERE name LIKE ? ORDER BY name";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connector.getPreparedStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            resultSet = preparedStatement.executeQuery();
            Film film = null;
            List<Film> films = new ArrayList<>();
            if(resultSet.next()) {
                film = new Film();
                film.setId(resultSet.getInt("id"));
                film.setName(resultSet.getString("name"));
                film.setDescription(resultSet.getString("description"));
                film.setCountry(resultSet.getString("country"));
                film.setQuality(resultSet.getString("quality"));
                film.setImage(resultSet.getString("image"));
                films.add(film);
            }

            return films;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {
                // TODO: 15.08.2017 Empty catch
            }
            try {
                connector.closePreparedStatement(preparedStatement);
            } catch(NullPointerException e) {
                // TODO: 15.08.2017 Empty catch
            }
        }
    }
}

/*    @Override
    public Map<Film, Map> findAll() throws DaoException {
        String sqlAll = "SELECT * FROM `films`";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connector.getStatement();
            resultSet = statement.executeQuery(sqlAll);

            List<Film> films = new ArrayList<>(); // All films

            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt("id"));
                film.setName(resultSet.getString("name"));
                film.setDescription(resultSet.getString("description"));
                film.setCountry(resultSet.getString("country"));
                film.setQuality(resultSet.getString("quality"));
                films.add(film);
            }

            String sql = "SELECT genres.name AS genre FROM films, genres, film_genres "
                    + "WHERE films.id = film_genres.film_id AND genres.id = film_genres.genre_id "
                    + "AND films.name = ";

            Map<Integer, String> genres = new HashMap<>();
            Map<Film, Map> mapMaps = new HashMap<>();

            try {
                for (Film film : films) {
                    String name = film.getName();
                    //statement = connector.getStatement();
                    resultSet = statement.executeQuery(sql + "\'" + name + "\'");

                    int i = 1;
                    while (resultSet.next()) {
                        genres.put(i, resultSet.getString("genre"));
                        i++;
                    }
                    mapMaps.put(film, genres);
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }

            System.out.println("Size of map = " + mapMaps.size());
            for (Map.Entry<Film, Map> entry : mapMaps.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }

            return mapMaps;
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
    }*/
