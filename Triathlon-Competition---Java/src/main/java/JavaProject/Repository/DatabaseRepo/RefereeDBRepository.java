package JavaProject.Repository.DatabaseRepo;

import JavaProject.Domain.Referee;
import JavaProject.Repository.IRefereeRepository;
import JavaProject.Utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RefereeDBRepository implements IRefereeRepository {

    private JDBCUtils databaseUtils;
    private static final Logger logger = LogManager.getLogger();


    public RefereeDBRepository(Properties properties) {
        logger.info("Initialising RefereeDBRepository with properties: {}", properties);
        databaseUtils = new JDBCUtils(properties);

    }

    @Override
    public Referee findOne(Integer integer)  {
        logger.traceEntry("Finding task {}", integer);
        Connection connection = databaseUtils.getConnection();
        Referee referee = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from referees where id=?")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String type = resultSet.getString("type");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    referee = new Referee(firstName, lastName, type, email, password);
                    referee.setId(id);
                }
                logger.traceExit("Found 1 instance");
            } catch (SQLException ex) {
                logger.error(ex);
            }
            logger.traceExit();
            return referee;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Referee> findAll() {
        logger.traceEntry("Finding task");
        Connection connection = databaseUtils.getConnection();
        List<Referee> referees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from referees")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String type = resultSet.getString("type");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    Referee referee = new Referee(firstName, lastName, type, email, password);
                    referee.setId(id);
                    referees.add(referee);
                }
                logger.traceExit("Found {} instances", referees.size());
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return referees;
    }

    @Override
    public Referee save(Referee entity) {
        logger.traceEntry("Saving task {}", entity);
        Connection connection = databaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into referees (firstName, lastName, type, email, password) values (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getType());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getPassword());
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Saved {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return entity;
    }

    @Override
    public Integer delete(Integer integer) {
        logger.traceEntry("Deleting task {}", integer);
        Connection connection = databaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from referees where id=?")) {
            preparedStatement.setInt(1, integer);
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Deleted {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return integer;
    }


    @Override
    public Referee update(Referee entity) {
        logger.traceEntry("Updating task {}", entity);
        Connection connection = databaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("update referees set firstName=?, lastName=?, type=?, email=?, password=? where id=?")) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getType());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getPassword());
            preparedStatement.setInt(6, entity.getId());
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Updated {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return entity;
    }

    private Referee buildRefereeFromResultSet(ResultSet resultSet) throws SQLException {
        Referee referee = null;
        var id = resultSet.getInt("id");
        var firstName = resultSet.getString("firstName");
        var lastName = resultSet.getString("lastName");
        var type = resultSet.getString("type");
        var email = resultSet.getString("email");
        var password = resultSet.getString("password");
        referee = new Referee(firstName, lastName, type, email, password);
        referee.setId(id);
        return referee;
    }

    @Override
    public Referee findByEmail(String email) {
        logger.traceEntry("Finding task {}", email);
        Connection connection = databaseUtils.getConnection();
        Referee referee = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from referees where email=?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    referee = buildRefereeFromResultSet(resultSet);
                }
                logger.traceExit("Found 1 instance");
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return referee;
    }
}

