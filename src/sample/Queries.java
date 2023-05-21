package sample;

public class Queries {
    public static final String LOGIN = "SELECT * FROM users where login = ?";
    public static final String CLIENTS_COUNT = "SELECT COUNT(Код_клієнта) FROM клієнт";
    public static final String MANAGERS_COUNT = "SELECT COUNT(Номер_менеджера) FROM менеджер";;
    public static final String DEPARTMENTS_COUNT = "SELECT COUNT(Номер_відділення) FROM відділення";
    public static final String ADD_CLIENT = "INSERT INTO клієнт "
            + "(Прізвище, Імя, По_батькові,Номер_паспорта,Номер_телефона,Адреса,Рік_народження,Місце_народження) "
            + "VALUES(?,?,?,?,?,?,?,?)";
    public static final String CLIENTS_PER_YEAR_COUNT = "SELECT YEAR(Рік_народження), COUNT(Код_клієнта) FROM клієнт GROUP BY YEAR(Рік_народження) ORDER BY TIMESTAMP(Рік_народження) ASC LIMIT 5";
    public static final String CHECK_PASSPORT_REPEAT = "SELECT Номер_паспорта FROM клієнт WHERE Номер_паспорта = ?";
    public static final String CLIENT_DELETE = "Delete FROM клієнт where Код_клієнта = ?";
    public static final String CLIENT_UPDATE = "UPDATE клієнт SET Прізвище = ?, Імя = ?, По_батькові = ?,Номер_паспорта= ?," +
            "Номер_телефона= ?,Адреса = ?, Рік_народження = ?,Місце_народження = ? "
            + "where Код_клієнта = ?";
    public static final String CLIENTS_GET_ALL = "SELECT * FROM клієнт";
    public static final String DEPARTMENTS_GET_ALL = "SELECT * FROM відділення";
    public static final String DEPARTMENT_UPDATE = "UPDATE відділення SET Назва_відділення = ?, Номер_менеджера = ? where Номер_відділення = ?";
    public static final String DEPARTMENT_ADD = "INSERT INTO відділення "
            + "(Назва_відділення, Номер_менеджера) "
            + "VALUES(?,?)";
    public static final String DEPARTMENTS_DELETE = "Delete FROM відділення where Номер_відділення = ?";
    public static final String MANAGERS_GET_IDS = "SELECT Номер_менеджера from менеджер";
    public static final String ACCOUNTS_GET_ALL = "SELECT * FROM рахунок";
    public static final String MANAGERS_GET_ALL = "SELECT * FROM менеджер";
    public static final String BILLS_GET_ALL = "SELECT * FROM внески";
    public static final String ACCOUNT_ADD = "INSERT INTO рахунок "
            + "(`Код_клієнта`, `Код_внеску`, `дата_відкриття_рахунку`, `дата_закриття_рахунку`, `сума_вкладення`, `Номер_відділення`)" +
            " VALUES (?,?,?,?,?,?);";
    public static final String ACCOUNT_UPDATE = "UPDATE рахунок SET Код_клієнта = ?, Код_внеску = ?, дата_відкриття_рахунку=?," +
            "дата_закриття_рахунку=?, сума_вкладення= ?, Номер_відділення=? where №_рахунку = ?";
    public static final String ACCOUNT_DELETE = "Delete FROM рахунок where '№_рахунку' = ?";
    public static final String CLIENTS_GET_IDS = "SELECT Код_клієнта from клієнт";
    public static final String BILLS_GET_IDS = "SELECT Код_внеску from внески";
    public static final String DEPARTMENTS_GET_IDS = "SELECT Номер_відділення from відділення";
}
