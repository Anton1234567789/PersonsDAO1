package org.sourceit.entities;

public class CollegeStudent extends Person{
    protected int year;
    protected String major;

    @Override
    public String toString() {
        return "CollegeStudent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", year=" + year +
                ", major='" + major + '\'' +
                '}';
    }

    public CollegeStudent(){
        this("Petr",26,'M',2000,"Mathem");
    }
    public CollegeStudent(int year, String major) {
        this.year = year;
        this.major = major;
    }

    public CollegeStudent(String name, int age, char gender, int year, String major) {
        super(name, age, gender);
        this.year = year;
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year>1960 && year<2001) {
            this.year = year;
        }else if(year<1960){
            System.out.println("уже поздно учиться");
        }else if(year>2001){
            System.out.println("ещё рано в универ");
        }
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
