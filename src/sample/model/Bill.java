package sample.model;

public class Bill {
    private Long id;
    private String name;
    private Integer term;
    private Integer percentage;

    public Bill(Long id, String name, Integer term, Integer percentage) {
        this.id = id;
        this.name = name;
        this.term = term;
        this.percentage = percentage;
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

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
