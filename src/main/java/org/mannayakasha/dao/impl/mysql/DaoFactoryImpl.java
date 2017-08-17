package org.mannayakasha.dao.impl.mysql;

import org.mannayakasha.dao.BaseDao;
import org.mannayakasha.dao.DBConnector;
import org.mannayakasha.dao.IDao;
import org.mannayakasha.dao.IDaoFactory;
import org.mannayakasha.dao.exception.DaoException;
import org.mannayakasha.dao.interfaces.IFilmDao;
import org.mannayakasha.dao.interfaces.IGenreDao;
import org.mannayakasha.dao.interfaces.IUserDao;
import org.mannayakasha.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Factory for getting a needed type of mysql dao.
 *
 * @author Pavel
 * @version 1.0 04.08.2017.
 */
public class DaoFactoryImpl implements IDaoFactory {
    //private static final Logger LOGGER = Logger.getLogger(DaoFactoryImpl.class);

    private static Map<Class<? extends IDao<?>>, Class<? extends BaseDao>> DAO_MAP = new ConcurrentHashMap<>();

    private DBConnector connector;

    // TODO: 04.08.2017 ConcurrentHashMap, and Class<? extends IDao<?>, and ? extends BaseDao

    static {
        DAO_MAP.put(IUserDao.class, UserDaoImpl.class);
        DAO_MAP.put(IGenreDao.class, GenreDaoImpl.class);
        DAO_MAP.put(IFilmDao.class, FilmDaoImpl.class);
    }

    public DaoFactoryImpl() throws DaoException {
        connector = new DBConnector(); //ConnectionPool.getInstance().getConnection();
/*        try {
            connection.setAutoCommit(true); // TODO: 06.08.2017 Delete this because we want to use transactions. True - without transactions. False - with transactions
        } catch(SQLException e) {
            throw new DaoException(e.getMessage(), e.getCause());
        }*/
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends IDao<?>> T createDao(Class<? extends IDao<?>> key) throws DaoException {
        Class<? extends BaseDao> value = DAO_MAP.get(key);
        if(value != null) {
            try {
                BaseDao dao = value.newInstance();
                dao.setConnector(connector);
                connector.getConnection();
                return (T)dao;
            } catch(InstantiationException | IllegalAccessException e) {
                //LOGGER.error("It is impossible to create DAO", e);
                throw new DaoException(e.getMessage(), e.getCause());
            }
        }
        return null; // TODO: 06.08.2017 Maybe better throw some exception
    }

    @Override
    public void close() { // close connection and return it into connection pool
        if (connector != null) { // TODO: 06.08.2017 Close not only connection, but also close transaction. Need to think about it
            connector.closeConnection();
        }
    }
}