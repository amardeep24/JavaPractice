import java.util.*;

public class ComparatorEight {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Amardeep","Bhowmick",26,1000d,7044475857l);
        Employee emp2 = new Employee("Amardeep","Champa",29,1200d,7044475857l);
        List<Employee> empList1 = new ArrayList(Arrays.asList(emp2,emp1,null));
        empList1.sort(Comparator.nullsFirst(Comparator.comparing(Employee::getFirstName)));
        empList1.forEach(System.out::println);
        List<Employee> empList2 = new ArrayList(Arrays.asList(emp2,emp1,null));
        empList2.sort(Comparator.nullsLast(Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName)));
        empList2.forEach(System.out::println);
        List<Employee> empList3 = new ArrayList(Arrays.asList(emp2,emp1,null));
        empList3.sort(Comparator.nullsLast(Comparator.comparing(Employee::getAge, (age1, age2) -> age2 > age1 ? -1: 1).thenComparing(Employee::getLastName)));
        empList3.forEach(System.out::println);
        empList3.stream().filter(Objects::nonNull).max(Comparator.comparing(Employee::getAge)).ifPresent(System.out::println);
    }
}
class Employee implements Comparable<String>{

    private String firstName;
    private String lastName;
    private int age;
    private double salary;
    private long cellphone;

    public Employee(String firstName, String lastName, int age, double salary, long cellphone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
        this.cellphone = cellphone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public long getCellphone() {
        return cellphone;
    }

    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }

    @Override
    public int compareTo(String firstName) {
        return this.firstName.compareTo(firstName);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", cellphone=" + cellphone +
                '}';
    }
}
