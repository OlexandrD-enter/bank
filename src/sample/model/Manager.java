package sample.model;

public class Manager {
    private Long id;
    private Integer exp;
    private String speciality;

    public Manager(Long id, Integer exp, String speciality) {
        this.id = id;
        this.exp = exp;
        this.speciality = speciality;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
