package sample.model;

public class Account {
    private Long id;
    private Long clientId;
    private Long contributionId;
    private String startDate;
    private String endDate;
    private Integer sum;
    private Long departmentId;

    public Account() {
    }

    public Account(Long id, Long clientId, Long contributionId, String startDate, String endDate, Integer sum, Long departmentId) {
        this.id = id;
        this.clientId = clientId;
        this.contributionId = contributionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sum = sum;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", contributionId=" + contributionId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", sum=" + sum +
                ", departmentId=" + departmentId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getContributionId() {
        return contributionId;
    }

    public void setContributionId(Long contributionId) {
        this.contributionId = contributionId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
