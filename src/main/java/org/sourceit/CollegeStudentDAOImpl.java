package org.sourceit;

import org.sourceit.db.DAO.DAO;
import org.sourceit.entities.CollegeStudent;
import org.sourceit.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollegeStudentDAOImpl implements DAO<CollegeStudent> {

    private Connection connection;

    public CollegeStudentDAOImpl(Connection connection){
        this.connection = connection;
    }


    @Override
    public boolean create(CollegeStudent entity) {
        boolean created = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO college_student(year, major) VALUES(?, ?)"))
        {
            preparedStatement.setInt(1, entity.getYear());
            preparedStatement.setString(2, entity.getMajor());

            created = preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return created;
    }

    @Override
    public boolean update(CollegeStudent entity) {
        boolean updated = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE college_student SET year=?, major=? " +
                        "WHERE college_student_id=?")) {
            preparedStatement.setInt(1, entity.getYear());
            preparedStatement.setString(2, entity.getMajor());
            preparedStatement.setInt(3, (int) entity.getId());

            updated = preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public List<CollegeStudent> list() {
        List<CollegeStudent> collegeStudents = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM college_student");
            while (resultSet.next()){
                CollegeStudent collegeStudent = new CollegeStudent();
                collegeStudent.setId(resultSet.getInt("college_student_id"));
                collegeStudent.setYear(resultSet.getInt("year"));
                collegeStudent.setMajor(resultSet.getString("major"));
                collegeStudents.add(collegeStudent);


            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return collegeStudents;
    }

    @Override
    public boolean remove(CollegeStudent entity) throws SQLException {
        boolean removed = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM college_student WHERE college_student_id=?")) {
            preparedStatement.setInt(1, (int) entity.getId());

            removed = preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return removed;
    }
}
