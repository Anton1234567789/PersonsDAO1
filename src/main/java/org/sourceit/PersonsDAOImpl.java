package org.sourceit;

import org.sourceit.db.DAO.DAO;
import org.sourceit.entities.Person;

import javax.swing.text.html.HTMLDocument;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonsDAOImpl implements DAO<Person>{

    private Connection connection;

    public PersonsDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public boolean remove(Person entity)  {
        boolean removed = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PERSONS WHERE PERSONS_ID =?")) {
            preparedStatement.setInt(1,(int)entity.getId());
            removed = preparedStatement.execute();

            }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return removed;
    }

    public List<Person> list() {

        List<Person> persons = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT *FROM PERSONS");

            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("persons_id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setGender(resultSet.getString("gender").charAt(0));
                persons.add(person);
                /*
                System.out.println(resultSet.getInt("persons_id"));
                System.out.println(resultSet.getString("name"));
                System.out.println(resultSet.getInt("age"));
                System.out.println(resultSet.getString("gender"));*/

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    public boolean update(Person entity) {
        boolean updated = false;

        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE persons SET name=?, age=?, gender=? " +
                            "WHERE persons_id=?")) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
            preparedStatement.setString(3, entity.getGender() + "");
            preparedStatement.setInt(4, (int)entity.getId());

            updated = preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    public boolean create(Person entity) {
        boolean created = false;
        try(PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO persons(name, age, gender) VALUES(?, ?, ?)")) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getAge());
            preparedStatement.setString(3, "" + entity.getGender());

            created = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return created;
    }
}
