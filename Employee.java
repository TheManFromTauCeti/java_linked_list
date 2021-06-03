public class Employee implements Comparable<Employee> {

    private final int employeeId;
    private String firstName, lastName;

    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmployeeID() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format(employeeId + " " + firstName + " " + lastName);
    }

    @Override
    public int compareTo(Employee e) {
        return Integer.compare(this.employeeId, e.employeeId);
    }
}
