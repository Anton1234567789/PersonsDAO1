package org.sourceit.entities;

public class Student extends Person{

    protected int idNumber;
    protected double gpa;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", idNumber=" + idNumber +
                ", gpa=" + gpa +
                '}';
    }

    public Student(){
        this("Stepa",23,'M',1, 212.2);
    }

    public Student(String name, int age, char gender, int idNumber, double gpa) {

        super(name, age, gender);
        this.idNumber = idNumber;
        this.gpa = gpa;
    }

    public Student(int idNumber, double gpa) {
        this.idNumber = idNumber;
        this.gpa = gpa;
    }



    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa>0 && gpa<100) {
            this.gpa = gpa;
        }else {
            System.out.println("gpa введен не верно. Введите от 0 до 100");
        }
    }
}
