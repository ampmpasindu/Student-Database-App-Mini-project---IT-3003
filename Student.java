public class Student {
    private int id;
    private String name;
    private String indexNo;
    private String email;
    private String combination;
    
    public Student(int id, String name, String indexNo, String email, String combination) {
        this.id = id;
        this.name = name;
        this.indexNo = indexNo;
        this.email = email;
        this.combination = combination;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIndexNo() { return indexNo; }
    public void setIndexNo(String indexNo) { this.indexNo = indexNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCombination() { return combination; }
    public void setCombination(String combination) { this.combination = combination; }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Index No: " + indexNo + ", Email: " + email + ", Combination: " + combination;
    }
}