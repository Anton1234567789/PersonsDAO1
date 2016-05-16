package org.sourceit.entities;

public class Teacher extends Person{

    protected int salary;
    protected String subject;

    public Teacher(int salary, String subject) {
        this.salary = salary;
        this.subject = subject;
    }

    public Teacher(){
        this("Ivan",50,'M', 5000, "Math");
    }
    public Teacher(String name, int age, char gender, int salary, String subject) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "salary=" + salary +
                ", subject='" + subject + '\'' +
                '}';
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        if(salary > 1500 && salary <=10000) {
            this.salary = salary;
        }
        else if (salary>10000){
            System.out.println("преподу невозможно заработать больше декана");
        }else if (salary<1500){
            System.out.println("невозможно выжить на такую зарплату");
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
