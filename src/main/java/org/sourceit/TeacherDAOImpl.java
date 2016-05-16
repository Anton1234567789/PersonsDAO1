package org.sourceit;

import org.sourceit.db.DAO.DAO;
import org.sourceit.entities.Person;
import org.sourceit.entities.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements DAO<Teacher>{

    private Connection connection;

    public TeacherDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Teacher entity) {
        boolean created = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO teacher(salary, subject) VALUES(?, ?)")){
            preparedStatement.setInt(1, entity.getSalary());
            preparedStatement.setString(2, entity.getSubject());

            created = preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return created;
    }

    @Override
    public boolean update(Teacher entity) {
        boolean update = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE teacher SET salary=?, subject=? "+
                "WHERE teacher_id=?" )) {
            preparedStatement.setInt(1,entity.getSalary());
            preparedStatement.setString(2,entity.getSubject());
            preparedStatement.setInt(3,(int)entity.getId());

            update = preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return update;
    }

    @Override
    public List<Teacher> list() {
        List<Teacher> teachers = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet  = statement.executeQuery("SELECT * FROM teacher");
            while (resultSet.next()){
                Teacher teacher = new Teacher();
                teacher.setId(resultSet.getInt("teacher_id"));
                teacher.setSalary(resultSet.getInt("salary"));
                teacher.setSubject(resultSet.getString("subject"));
                teachers.add(teacher);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public boolean remove(Teacher entity) throws SQLException {
        boolean removed = false;
        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM teacher WHERE teacher_id=?")) {
            statement.setInt(1, (int)entity.getId());

            removed = statement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return removed;
    }
}
