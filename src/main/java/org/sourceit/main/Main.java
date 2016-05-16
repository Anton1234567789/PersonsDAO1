package org.sourceit.main;


import org.sourceit.CollegeStudentDAOImpl;
import org.sourceit.PersonsDAOImpl;
import org.sourceit.StudentDAOImpl;
import org.sourceit.TeacherDAOImpl;
import org.sourceit.db.DAO.DAO;
import org.sourceit.db.DAO.connection.MySQLConnection;
import org.sourceit.entities.CollegeStudent;
import org.sourceit.entities.Person;
import org.sourceit.entities.Student;
import org.sourceit.entities.Teacher;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        MySQLConnection mySQLConnection = MySQLConnection.INSTANCE;

        DAO<Person> personDAO = new PersonsDAOImpl(mySQLConnection.getConnection());

        DAO<Teacher> teacherDAO = new TeacherDAOImpl(mySQLConnection.getConnection());

        DAO<Student> studentDAO = new StudentDAOImpl(mySQLConnection.getConnection());

        DAO<CollegeStudent> collegeStudentDAO = new CollegeStudentDAOImpl(mySQLConnection.getConnection());

        List<CollegeStudent> collegeStudents = collegeStudentDAO.list();

        for (CollegeStudent collegeStudent : collegeStudents){
            collegeStudentDAO.remove(collegeStudent);
        }

        System.out.println(collegeStudentDAO.list());

        CollegeStudent collegeStudent = new CollegeStudent(1996,"Math");
        collegeStudentDAO.create(collegeStudent);
        System.out.println(collegeStudentDAO.list());

        /*
        List<Student> students = studentDAO.list();

        System.out.println(studentDAO.list());

        for (Student student : students){
            studentDAO.remove(student);
          }
        System.out.println(studentDAO.list());

        Student student = new Student(1,99.9);
        studentDAO.create(student);
        System.out.println(studentDAO.list());*/

       /* List<Teacher> teachers = teacherDAO.list();
        System.out.println(teacherDAO.list());
        for (Teacher teacher: teachers){
            teacherDAO.remove(teacher);
        }

        System.out.println(teacherDAO.list());
        Teacher teacher1 = new Teacher(5200,"Math");
        teacherDAO.create(teacher1);
        System.out.println(teacherDAO.list());*/
        /*
        //System.out.println(personDAO.list());

        List<Person> persons = personDAO.list();

        for (Person person : persons){
            personDAO.remove(person);
        }
        System.out.println(personDAO.list());

        Person person1 = new Person("Oleg 1", 40, 'F');
       Person person2 = new Person("masha 1",34,'F');

        personDAO.create(person1);
        personDAO.create(person2);
        System.out.println(personDAO.list());

        List<Person> personsList = personDAO.list();

        personsList.get(0).setName("Oleg");
        personsList.get(0).setAge(24);
        personsList.get(0).setGender('M');
        personsList.get(1).setName("Masha");
        personsList.get(1).setAge(34);
        personsList.get(1).setGender('F');
        personDAO.update(personsList.get(0));
        personDAO.update(personsList.get(1));


        System.out.println(personDAO.list());*/

    }
}
