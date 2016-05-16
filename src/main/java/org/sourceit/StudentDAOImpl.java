package org.sourceit;

import org.sourceit.db.DAO.DAO;
import org.sourceit.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements DAO<Student>{

    private Connection connection;
    public StudentDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public boolean create(Student entity) {
        boolean created = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO student(id_number, gpa) VALUES(?, ?)"
        )) {
            preparedStatement.setInt(1,entity.getIdNumber());
            preparedStatement.setDouble(2,entity.getGpa());

            created = preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return created;
    }

    @Override
    public boolean update(Student entity) {
        boolean updated = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE student SET id_number=?, gpa=?"+
                        "WHERE student_id=?"
        )) {
            preparedStatement.setInt(1, entity.getIdNumber());
            preparedStatement.setDouble(2, entity.getGpa());

            updated = preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public List<Student> list() {
        List<Student> students =new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM student");
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("student_id"));
                student.setIdNumber(resultSet.getInt("id_number"));
                student.setGpa(resultSet.getDouble("gpa"));

                students.add(student);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean remove(Student entity){
        boolean removed = false;
        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM student WHERE student_id=?")) {

            preparedStatement.setInt(1, (int)entity.getId());
            removed = preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return removed;
    }
}
