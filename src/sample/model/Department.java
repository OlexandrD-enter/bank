package sample.model;

public class Department {
    private Long id;
    private String name;
    private Long managerId;

    public Department() {
    }

    public Department(Long id, String name, Long managerId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerId=" + managerId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
