import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;
    
    public DatabaseManager(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }
    
    public void createStudentsTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                     "name VARCHAR(100) NOT NULL, " +
                     "indexNo VARCHAR(20) UNIQUE NOT NULL, " +
                     "email VARCHAR(100) UNIQUE NOT NULL, " +
                     "combination VARCHAR(100) NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
    
    public boolean addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, indexNo, email, combination) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getIndexNo());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getCombination());
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("indexNo"),
                    rs.getString("email"),
                    rs.getString("combination")
                ));
            }
        }
        return students;
    }
    
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("indexNo"),
                        rs.getString("email"),
                        rs.getString("combination")
                    );
                }
            }
        }
        return null;
    }
    
    public Student getStudentByIndexNo(String indexNo) throws SQLException {
        String sql = "SELECT * FROM students WHERE indexNo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, indexNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("indexNo"),
                        rs.getString("email"),
                        rs.getString("combination")
                    );
                }
            }
        }
        return null;
    }
    
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name = ?, indexNo = ?, email = ?, combination = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getIndexNo());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getCombination());
            pstmt.setInt(5, student.getId());
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}