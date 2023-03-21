package Repository.DatabaseRepo;

import Domain.ContestType;
import Domain.Contest;
import Repository.IContestRepository;
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

public class ContestDBRepository implements IContestRepository {

    private JDBCUtils databaseUtils;
    private static final Logger logger = LogManager.getLogger();


    public ContestDBRepository(Properties properties) {
        logger.info("Initialising ContestDBRepository with properties: {}", properties);
        databaseUtils = new JDBCUtils(properties);

    }

    @Override
    public Contest findOne(Integer integer)  {
        logger.traceEntry("Finding task {}", integer);
        Connection connection = databaseUtils.getConnection();
        Contest referee = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from contest where id=?")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int type = resultSet.getInt("type");
                    int points = resultSet.getInt("points");
                    int participantID = resultSet.getInt("participantID");
                    referee = new Contest(ContestType.fromValue(type), points, participantID);
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
    public List<Contest> findAll() {
        logger.traceEntry("Finding task");
        Connection connection = databaseUtils.getConnection();
        List<Contest> contest = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from contest")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int type = resultSet.getInt("type");
                    int points = resultSet.getInt("points");
                    int participantID = resultSet.getInt("participantID");
                    Contest contest1 = new Contest(ContestType.fromValue(type), points, participantID);
                    contest1.setId(id);
                    contest.add(contest1);
                }
                logger.traceExit("Found {} instances", contest.size());
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return contest;
    }

    @Override
    public Contest save(Contest entity) {
        logger.traceEntry("Saving task {}", entity);
        Connection connection = databaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into contest ( type, points, participantID) values ( ?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getType().getValue());
            preparedStatement.setInt(2, entity.getPoints());
            preparedStatement.setInt(3, entity.getParticipantID());
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from contest where id=?")) {
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
    public Contest update(Contest entity) {
        logger.traceEntry("Updating task {}", entity);
        Connection connection = databaseUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("update contest set type=?, points=?, participantID=? where id=?")) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getPoints());
            preparedStatement.setInt(3, entity.getParticipantID());
            preparedStatement.setInt(4, entity.getId());
            int result = preparedStatement.executeUpdate();
            logger.traceExit("Updated {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return entity;
    }
}
