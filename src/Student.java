import java.util.ArrayList;
import java.util.List;

public class Student {
    /** 
     * The student's name
     */
    private String name;

    /**
     * The student's grade
     */
    private int grade;

    /**
     * The student's email
     */
	private String email;

	/**
     * The student's password
     */
	
	private String password;

     /**
      * Creates a new student object
      */
    public Student(String name, String email, String password, int grade){
        this.name = name;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public int getGrade(){
        return grade;
    }

    public void setGrade(int grade){
        this.grade = grade;
    }

    //creates a list of all the students
    protected static List<Student> studentList = new ArrayList<Student>();

    //add a student to the students list
    public static void addStudent(Student student) {
        studentList.add(student);
    }

    /**
     * Unregisters an existing student account. Does nothing if the account does not exist.
     *
     * @param student The student account to remove
     */
    public static void removeStudent(Student student) {
        studentList.remove(student);
    }
    
    /**
     * Gets a list of all active student accounts.
     *
     * @return A list of students
     */
    public static List<Student> getStudents() {
        return studentList;
    }
}