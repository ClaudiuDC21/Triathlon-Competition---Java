package Repository.DatabaseRepo;

import Domain.Participant;
import Repository.IParticipantRepository;
import Utils.JDBCUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParticipantDBRepository implements IParticipantRepository {
    private final JDBCUtils databaseUtils;
    private static final Logger logger = LogManager.getLogger();

    public ParticipantDBRepository(Properties properties) {
        logger.info("Initialising ParticipantDBRepository with properties: {}", properties);
        databaseUtils = new JDBCUtils(properties);
    }

    @Override
    public Participant findOne(Integer integer) {
        logger.traceEntry("Finding task {}", integer);
        Connection connection = databaseUtils.getConnection();
        Participant participant = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from participants where id=?")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    int numberOfPoints = resultSet.getInt("numberOfPoints");
                    participant = new Participant(firstName, lastName, numberOfPoints);
                    participant.setId(id);
                }
                logger.traceExit("Found 1 instance");
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return participant;
    }

    @Override
    public List<Participant> findAll() {
        logger.traceEntry("Finding task");
        Connection connection = databaseUtils.getConnection();
        List<Participant> participants = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from participants")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var id = resultSet.getInt("id");
                    var firstName = resultSet.getString("firstName");
                    var lastName = resultSet.getString("lastName");
                    int numberOfPoints = resultSet.getInt("numberOfPoints");
                    Participant participant = new Participant(firstName, lastName, numberOfPoints);
                    participant.setId(id);
                    participants.add(participant);
                }
                logger.traceExit("Found {} instances", participants.size());
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return participants;
    }

    @Override
    public Participant save(Participant entity) {
        logger.traceEntry("Saving task {}", entity);
        Connection connection = databaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into participants (firstName, lastName, numberOfPoints) values (?, ?, ?)")) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getNumberOfPoints());
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from participants where id=?")) {
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
    public Participant update(Participant entity) {
        logger.traceEntry("Updating task {}", entity);
        Connection connection = databaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("update participants set firstName=?, lastName=?, numberOfPoints=? where id=?")) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getNumberOfPoints());
            preparedStatement.setInt(4,entity.getId());
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Updated {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return entity;
    }
}
