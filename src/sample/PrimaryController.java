package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrimaryController {

    public static User currentUser;
    @FXML
    public TableView<Manager> man_tableView;
    @FXML
    public TableColumn<Manager, Long> man_col_num;
    @FXML
    public TableColumn<Manager, Integer> man_col_exp;
    @FXML
    public TableColumn<Manager, String> man_col_speciality;
    @FXML
    public Button managers_btn;
    @FXML
    public AnchorPane manager_form;

    @FXML
    private TableColumn<Bill, Long> bill_col_code;

    @FXML
    private TableColumn<Bill, String> bill_col_name;

    @FXML
    private TableColumn<Bill, Integer> bill_col_perc;

    @FXML
    private TableColumn<Bill, Integer> bill_col_term;

    @FXML
    private AnchorPane bill_form;

    @FXML
    private TableView<Bill> bill_tableView;

    @FXML
    private Button bills_btn;

    @FXML
    private ComboBox<Long> acc_clientIds;

    @FXML
    private TableColumn<Account, Long> acc_col_client;

    @FXML
    private TableColumn<Account, Long> acc_col_cont;

    @FXML
    private TableColumn<Account, Long> acc_col_depart;

    @FXML
    private TableColumn<Account, String> acc_col_end;

    @FXML
    private TableColumn<Account, String> acc_col_start;

    @FXML
    private TableColumn<Account, Double> acc_col_sum;

    @FXML
    private ComboBox<Long> acc_contIds;

    @FXML
    private ComboBox<Long> acc_departIds;

    @FXML
    private TextField acc_end;

    @FXML
    private TextField acc_start;

    @FXML
    private TextField acc_summ;

    @FXML
    private TableView<Account> acc_tableView;

    @FXML
    private Button account_btn;

    @FXML
    private AnchorPane account_form;

    @FXML
    private TableColumn<Department, Long> depart_col_managerId;

    @FXML
    private TableColumn<Department, String> depart_col_name;

    @FXML
    private TableColumn<Department, Long> depart_col_num;

    @FXML
    private Button department_btn;

    @FXML
    private AnchorPane department_form;

    @FXML
    private ComboBox<Long> department_managerId;

    @FXML
    private TextField department_name;

    @FXML
    private TableView<Department> department_tableView;

    @FXML
    private TextField client_address;

    @FXML
    private TextField client_birthday;

    @FXML
    private TableColumn<Client, String> client_col_address;

    @FXML
    private TableColumn<Client, String> client_col_birth;

    @FXML
    private TableColumn<Client, String> client_col_fathname;

    @FXML
    private TableColumn<Client, String> client_col_firstname;

    @FXML
    private TableColumn<Client, String> client_col_lastname;

    @FXML
    private TableColumn<Client, String> client_col_pasp;

    @FXML
    private TableColumn<Client, String> client_col_phone;

    @FXML
    private TableColumn<Client, String> client_col_place;

    @FXML
    private TextField client_fathname;

    @FXML
    private TextField client_firstname;

    @FXML
    private TextField client_lastname;

    @FXML
    private TextField client_pasp;

    @FXML
    private TextField client_phone;

    @FXML
    private TextField client_placeBirth;

    @FXML
    private TextField client_search;

    @FXML
    private TableView<Client> client_tableView;

    @FXML
    private Button clients_btn;

    @FXML
    private AnchorPane clients_form;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private Label home_totalManagers;

    @FXML
    private Label home_totalDepartments;

    @FXML
    private Button logout;

    @FXML
    private Label username;

    private ObservableList<Client> clientsData;
    private ObservableList<Department> departmentsData;
    private ObservableList<Account> accountsData;
    private ObservableList<Manager> managersData;
    private ObservableList<Bill> billsData;

    public void homeDisplayTotalEnrolledClient() {
        String sql = Queries.CLIENTS_COUNT;
        int countEnrolled = 0;

        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                countEnrolled = result.getInt("COUNT(Код_клієнта)");
            }
            home_totalEnrolled.setText(String.valueOf(countEnrolled));
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void homeDisplayTotalEnrolledManagers() {

        String sql = Queries.MANAGERS_COUNT;
        int countEnrolled = 0;

        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                countEnrolled = result.getInt("COUNT(Номер_менеджера)");
            }
            connect.close();
            home_totalManagers.setText(String.valueOf(countEnrolled));
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void homeDisplayTotalEnrolledDepartments() {

        String sql = Queries.DEPARTMENTS_COUNT;
        int countEnrolled = 0;

        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                countEnrolled = result.getInt("COUNT(Номер_відділення)");
            }
            connect.close();
            home_totalManagers.setText(String.valueOf(countEnrolled));
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void homeDisplayTotalEnrolledChart() {

        home_totalEnrolledChart.getData().clear();

        String sql = Queries.CLIENTS_PER_YEAR_COUNT;

        Connection connect = DatabaseConnection.getConnection();
        try {
            XYChart.Series chart = new XYChart.Series();
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }
            home_totalEnrolledChart.getData().add(chart);
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }

    }


    @FXML
    void clientSearch(KeyEvent event) {
        FilteredList<Client> filter = new FilteredList<>(clientsData, e -> true);

        client_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(clientData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (clientData.getFirstName().contains(searchKey)) {
                    return true;
                } else if (clientData.getLastName().contains(searchKey)) {
                    return true;
                } else if (clientData.getDate().contains(searchKey)) {
                    return true;
                } else if (clientData.getAddress().contains(searchKey)) {
                    return true;
                } else if (clientData.getPassport().contains(searchKey)) {
                    return true;
                } else if (clientData.getPhoneNumber().contains(searchKey)) {
                    return true;
                } else return clientData.getPlace().contains(searchKey);
            });
        });

        SortedList<Client> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(client_tableView.comparatorProperty());
        client_tableView.setItems(sortList);
    }


    @FXML
    void clientAdd(ActionEvent event) {
        String sql = Queries.ADD_CLIENT;

        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare;
            ResultSet result;
            Alert alert;
            if (client_lastname.getText().isEmpty()
                    || client_firstname.getText().isEmpty()
                    || client_fathname.getText().isEmpty()
                    || client_pasp.getText().isEmpty()
                    || client_phone.getText().isEmpty()
                    || client_birthday.getText().isEmpty()
                    || client_address.getText().isEmpty()
                    || client_placeBirth.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String checkData = Queries.CHECK_PASSPORT_REPEAT;
                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, client_pasp.getText());
                result = prepare.executeQuery();
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Client was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, client_lastname.getText());
                    prepare.setString(2, client_firstname.getText());
                    prepare.setString(3, client_fathname.getText());
                    prepare.setString(4, client_pasp.getText());
                    prepare.setString(5, client_phone.getText());
                    prepare.setString(6, client_address.getText());
                    prepare.setString(7, client_birthday.getText());
                    prepare.setString(8, client_placeBirth.getText());
                    prepare.executeUpdate();
                    clientsShowListData();
                    clientFieldsClear();
                }
                prepare.close();
                result.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }


    @FXML
    void clientClear(ActionEvent event) {
        clientFieldsClear();
    }

    @FXML
    void clientDelete(ActionEvent event) {
        String query = Queries.CLIENT_DELETE;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Alert alert;
            int num = client_tableView.getSelectionModel().getSelectedIndex();
            if ((num - 1) < -1) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Smth wrong, choose client and try again");
                alert.showAndWait();
            } else {
                Client client = client_tableView.getSelectionModel().getSelectedItem();
                PreparedStatement prepare = connect.prepareStatement(query);
                prepare.setLong(1, client.getId());
                prepare.executeUpdate();
                clientsShowListData();
                clientFieldsClear();
                prepare.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @FXML
    void clientUpdate(ActionEvent event) {
        String query = Queries.CLIENT_UPDATE;

        Connection connect = DatabaseConnection.getConnection();
        try {
            Alert alert;
            PreparedStatement prepare;
            if (client_lastname.getText().isEmpty()
                    || client_firstname.getText().isEmpty()
                    || client_fathname.getText().isEmpty()
                    || client_pasp.getText().isEmpty()
                    || client_phone.getText().isEmpty()
                    || client_birthday.getText().isEmpty()
                    || client_address.getText().isEmpty()
                    || client_placeBirth.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                Client client = client_tableView.getSelectionModel().getSelectedItem();
                prepare = connect.prepareStatement(query);
                prepare.setString(1, client_lastname.getText());
                prepare.setString(2, client_firstname.getText());
                prepare.setString(3, client_fathname.getText());
                prepare.setString(4, client_pasp.getText());
                prepare.setString(5, client_phone.getText());
                prepare.setString(6, client_address.getText());
                prepare.setString(7, client_birthday.getText());
                prepare.setString(8, client_placeBirth.getText());
                prepare.setLong(9, client.getId());
                prepare.executeUpdate();
                clientsShowListData();
                clientFieldsClear();
                prepare.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    @FXML
    void clientsSelect() {
        Client client = client_tableView.getSelectionModel().getSelectedItem();
        int num = client_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        client_firstname.setText(client.getFirstName());
        client_lastname.setText(client.getLastName());
        client_fathname.setText(client.getFatherName());
        client_pasp.setText(client.getPassport());
        client_phone.setText(client.getPhoneNumber());
        client_birthday.setText(client.getDate());
        client_address.setText(client.getAddress());
        client_placeBirth.setText(client.getPlace());
    }

    public void clientFieldsClear() {
        client_firstname.setText("");
        client_lastname.setText("");
        client_fathname.setText("");
        client_pasp.setText("");
        client_address.setText("");
        client_birthday.setText("");
        client_phone.setText("");
        client_placeBirth.setText("");
    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void logout(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Client> clientsListData() {

        ObservableList<Client> listClients = FXCollections.observableArrayList();

        String sql = Queries.CLIENTS_GET_ALL;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Client client;
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                client = new Client(result.getLong("Код_клієнта"),
                        result.getString("Прізвище"),
                        result.getString("Імя"),
                        result.getString("По_батькові"),
                        result.getString("Номер_паспорта"),
                        result.getString("Номер_телефона"),
                        result.getString("Адреса"),
                        result.getString("Рік_народження"),
                        result.getString("Місце_народження"));
                listClients.add(client);
            }
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return listClients;
    }


    public void clientsShowListData() {
        clientsData = clientsListData();
        client_col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        client_col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        client_col_fathname.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        client_col_pasp.setCellValueFactory(new PropertyValueFactory<>("passport"));
        client_col_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        client_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        client_col_birth.setCellValueFactory(new PropertyValueFactory<>("date"));
        client_col_place.setCellValueFactory(new PropertyValueFactory<>("place"));
        client_tableView.setItems(clientsData);
    }

    public ObservableList<Department> departmentsListData() {

        ObservableList<Department> listDepartments = FXCollections.observableArrayList();

        String sql = Queries.DEPARTMENTS_GET_ALL;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Department department;
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                department = new Department(result.getLong("Номер_відділення"),
                        result.getString("Назва_відділення"),
                        result.getLong("Номер_менеджера"));
                listDepartments.add(department);
            }
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return listDepartments;
    }


    public void departmentsShowListData() {
        departmentsData = departmentsListData();
        depart_col_num.setCellValueFactory(new PropertyValueFactory<>("id"));
        depart_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        depart_col_managerId.setCellValueFactory(new PropertyValueFactory<>("managerId"));
        department_tableView.setItems(departmentsData);
    }

    public void departmentUpdate(ActionEvent actionEvent) {
        String query = Queries.DEPARTMENT_UPDATE;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Alert alert;
            PreparedStatement prepare;
            if (department_name.getText().isEmpty()
                    || department_managerId.getSelectionModel().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                Department department = department_tableView.getSelectionModel().getSelectedItem();
                prepare = connect.prepareStatement(query);
                prepare.setString(1, department_name.getText());
                prepare.setLong(2, department_managerId.getSelectionModel().getSelectedItem());
                prepare.setLong(3, department.getId());
                prepare.executeUpdate();
                departmentsShowListData();
                departmentFieldsClear();
                prepare.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void departmentsClear(ActionEvent actionEvent) {
        department_name.setText("");
        department_managerId.setValue(null);
    }

    public void departmentAdd(ActionEvent actionEvent) {
        String query = Queries.DEPARTMENT_ADD;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Alert alert;
            PreparedStatement prepare;
            if (department_name.getText().isEmpty()
                    || department_managerId.getSelectionModel().getSelectedItem() == null
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(query);
                prepare.setString(1, department_name.getText());
                prepare.setLong(2, department_managerId.getSelectionModel().getSelectedItem());
                prepare.executeUpdate();
                departmentsShowListData();
                departmentFieldsClear();
                prepare.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    private void departmentFieldsClear() {
        department_name.setText("");
        department_managerId.setValue(null);
    }

    public void departmentDelete(ActionEvent actionEvent) {
        String query = Queries.DEPARTMENTS_DELETE;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Alert alert;
            PreparedStatement prepare;
            int num = department_tableView.getSelectionModel().getSelectedIndex();
            if ((num - 1) < -1) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Smth wrong, choose department and try again");
                alert.showAndWait();
            } else {
                Department department = department_tableView.getSelectionModel().getSelectedItem();
                prepare = connect.prepareStatement(query);
                prepare.setLong(1, department.getId());
                prepare.executeUpdate();
                departmentsShowListData();
                departmentFieldsClear();
                prepare.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void departmentSelect(MouseEvent mouseEvent) {
        Department department = department_tableView.getSelectionModel().getSelectedItem();
        int num = department_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        department_name.setText(department.getName());
        department_managerId.setValue(department.getManagerId());
    }

    public void managersList() {
        String query = Queries.MANAGERS_GET_IDS;
        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(query);
            ResultSet result = prepare.executeQuery();
            List<Long> managerIds = new ArrayList<>();
            while (result.next()) {
                managerIds.add(result.getLong(1));
            }
            ObservableList<Long> ObList = FXCollections.observableArrayList(managerIds);
            department_managerId.setItems(ObList);
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public ObservableList<Account> accountsListData() {

        ObservableList<Account> listAccounts = FXCollections.observableArrayList();

        String sql = Queries.ACCOUNTS_GET_ALL;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Account account;
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                account = new Account(result.getLong("№_рахунку"),
                        result.getLong("Код_клієнта"),
                        result.getLong("Код_внеску"),
                        result.getString("дата_відкриття_рахунку"),
                        result.getString("дата_закриття_рахунку"),
                        result.getInt("сума_вкладення"),
                        result.getLong("Номер_відділення")
                );
                listAccounts.add(account);
            }
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return listAccounts;
    }


    public void accountsShowListData() {
        accountsData = accountsListData();
        acc_col_client.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        acc_col_cont.setCellValueFactory(new PropertyValueFactory<>("contributionId"));
        acc_col_start.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        acc_col_end.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        acc_col_sum.setCellValueFactory(new PropertyValueFactory<>("sum"));
        acc_col_depart.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        acc_tableView.setItems(accountsData);
    }


    public ObservableList<Manager> managersListData() {

        ObservableList<Manager> listManagers = FXCollections.observableArrayList();

        String sql = Queries.MANAGERS_GET_ALL;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Manager manager;
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                manager = new Manager(result.getLong("Номер_менеджера"),
                        result.getInt("Стаж_роботи"),
                        result.getString("Спеціальність")
                );
                listManagers.add(manager);
            }
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return listManagers;
    }


    public void managersShowListData() {
        managersData = managersListData();
        man_col_num.setCellValueFactory(new PropertyValueFactory<>("id"));
        man_col_exp.setCellValueFactory(new PropertyValueFactory<>("exp"));
        man_col_speciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        man_tableView.setItems(managersData);
    }


    public ObservableList<Bill> billsListData() {

        ObservableList<Bill> listBills = FXCollections.observableArrayList();

        String sql = Queries.BILLS_GET_ALL;
        Connection connect = DatabaseConnection.getConnection();
        try {
            Bill bill;
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                bill = new Bill(result.getLong("Код_внеску"),
                        result.getString("Назва_внеску"),
                        result.getInt("Термін_зберігання"),
                        result.getInt("ставка_річних")
                );
                listBills.add(bill);
            }
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
        return listBills;
    }


    public void billsShowListData() {
        billsData = billsListData();
        bill_col_code.setCellValueFactory(new PropertyValueFactory<>("id"));
        bill_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        bill_col_perc.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        bill_col_term.setCellValueFactory(new PropertyValueFactory<>("term"));
        bill_tableView.setItems(billsData);
    }


    public void accAdd(ActionEvent actionEvent) {
        String query = Queries.ACCOUNT_ADD;

        Connection connect = DatabaseConnection.getConnection();
        try {
            Alert alert;
            PreparedStatement prepare;
            if (acc_clientIds.getSelectionModel().getSelectedItem() == null
                    || acc_contIds.getSelectionModel().getSelectedItem() == null
                    || acc_start.getText().isEmpty()
                    || acc_end.getText().isEmpty()
                    || acc_summ.getText().isEmpty()
                    || acc_departIds.getSelectionModel().getSelectedItem() == null
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(query);
                prepare.setLong(1, acc_clientIds.getSelectionModel().getSelectedItem());
                prepare.setLong(2, acc_contIds.getSelectionModel().getSelectedItem());
                prepare.setString(3, acc_start.getText());
                prepare.setString(4, acc_end.getText());
                prepare.setString(5, acc_summ.getText());
                prepare.setLong(6, acc_departIds.getSelectionModel().getSelectedItem());
                prepare.executeUpdate();
                accountsShowListData();
                accClear();
                prepare.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void accUpdate(ActionEvent actionEvent) {
        String query = Queries.ACCOUNT_UPDATE;

        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare;
            Alert alert;
            if (acc_clientIds.getSelectionModel().getSelectedItem() == null
                    || acc_contIds.getSelectionModel().getSelectedItem() == null
                    || acc_start.getText().isEmpty()
                    || acc_end.getText().isEmpty()
                    || acc_summ.getText().isEmpty()
                    || acc_departIds.getSelectionModel().getSelectedItem() == null
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                Account account = acc_tableView.getSelectionModel().getSelectedItem();
                prepare = connect.prepareStatement(query);
                prepare.setLong(1, acc_clientIds.getSelectionModel().getSelectedItem());
                prepare.setLong(2, acc_contIds.getSelectionModel().getSelectedItem());
                prepare.setString(3, acc_start.getText());
                prepare.setString(4, acc_end.getText());
                prepare.setInt(5, Integer.parseInt(acc_summ.getText()));
                prepare.setLong(6, acc_departIds.getSelectionModel().getSelectedItem());
                prepare.setLong(7, account.getId());
                prepare.executeUpdate();
                accountsShowListData();
                accClear();
                prepare.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void accClear() {
        acc_clientIds.setValue(null);
        acc_contIds.setValue(null);
        acc_start.setText("");
        acc_end.setText("");
        acc_summ.setText("");
        acc_departIds.setValue(null);
    }

    public void accDelete(ActionEvent actionEvent) {
        String query = Queries.ACCOUNT_DELETE;
        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare;
            Alert alert;
            int num = acc_tableView.getSelectionModel().getSelectedIndex();
            if ((num - 1) < -1) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Smth wrong, choose department and try again");
                alert.showAndWait();
            } else {
                Account account = acc_tableView.getSelectionModel().getSelectedItem();
                prepare = connect.prepareStatement(query);
                prepare.setLong(1, account.getId());
                prepare.executeUpdate();
                accountsShowListData();
                accClear();
                prepare.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void accClientsIds() {
        String query = Queries.CLIENTS_GET_IDS;
        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(query);
            ResultSet result = prepare.executeQuery();

            List<Long> clientsIds = new ArrayList<>();
            while (result.next()) {
                clientsIds.add(result.getLong(1));
            }
            ObservableList<Long> ObList = FXCollections.observableArrayList(clientsIds);
            acc_clientIds.setItems(ObList);
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void accContsIds() {
        String query = Queries.BILLS_GET_IDS;
        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(query);
            ResultSet result = prepare.executeQuery();

            List<Long> contIds = new ArrayList<>();
            while (result.next()) {
                contIds.add(result.getLong(1));
            }
            ObservableList<Long> ObList = FXCollections.observableArrayList(contIds);
            acc_contIds.setItems(ObList);
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void accDepartIds() {
        String query = Queries.DEPARTMENTS_GET_IDS;
        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(query);
            ResultSet result = prepare.executeQuery();


            List<Long> depIds = new ArrayList<>();
            while (result.next()) {
                depIds.add(result.getLong(1));
            }
            ObservableList<Long> ObList = FXCollections.observableArrayList(depIds);
            acc_departIds.setItems(ObList);
            prepare.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DatabaseConnection.closeConnection();
        }
    }

    public void accSelect(MouseEvent mouseEvent) {
        Account account = acc_tableView.getSelectionModel().getSelectedItem();
        int num = acc_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        acc_clientIds.setValue(account.getClientId());
        acc_contIds.setValue(account.getContributionId());
        acc_start.setText(account.getStartDate());
        acc_end.setText(account.getEndDate());
        acc_summ.setText(String.valueOf(account.getSum()));
        acc_departIds.setValue(account.getDepartmentId());
    }


    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            clients_form.setVisible(false);
            department_form.setVisible(false);
            account_form.setVisible(false);
            manager_form.setVisible(false);
            bill_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            clients_btn.setStyle("-fx-background-color:transparent");
            department_btn.setStyle("-fx-background-color:transparent");
            account_btn.setStyle("-fx-background-color:transparent");
            managers_btn.setStyle("-fx-background-color:transparent");
            bills_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalEnrolledClient();
            homeDisplayTotalEnrolledManagers();
            homeDisplayTotalEnrolledDepartments();
            homeDisplayTotalEnrolledChart();

        } else if (event.getSource() == clients_btn) {
            home_form.setVisible(false);
            clients_form.setVisible(true);
            department_form.setVisible(false);
            account_form.setVisible(false);
            manager_form.setVisible(false);
            bill_form.setVisible(false);

            clients_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            department_btn.setStyle("-fx-background-color:transparent");
            account_btn.setStyle("-fx-background-color:transparent");
            managers_btn.setStyle("-fx-background-color:transparent");
            bills_btn.setStyle("-fx-background-color:transparent");

            clientsShowListData();

        } else if (event.getSource() == department_btn) {
            home_form.setVisible(false);
            clients_form.setVisible(false);
            department_form.setVisible(true);
            account_form.setVisible(false);
            manager_form.setVisible(false);
            bill_form.setVisible(false);

            clients_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            department_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            account_btn.setStyle("-fx-background-color:transparent");
            managers_btn.setStyle("-fx-background-color:transparent");
            bills_btn.setStyle("-fx-background-color:transparent");

            departmentsShowListData();
            managersList();
        } else if (event.getSource() == account_btn) {
            home_form.setVisible(false);
            clients_form.setVisible(false);
            department_form.setVisible(false);
            account_form.setVisible(true);
            manager_form.setVisible(false);
            bill_form.setVisible(false);

            clients_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            department_btn.setStyle("-fx-background-color:transparent");
            account_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            managers_btn.setStyle("-fx-background-color:transparent");
            bills_btn.setStyle("-fx-background-color:transparent");

            accountsShowListData();
            accClientsIds();
            accDepartIds();
            accContsIds();
        } else if (event.getSource() == managers_btn) {
            home_form.setVisible(false);
            clients_form.setVisible(false);
            department_form.setVisible(false);
            account_form.setVisible(false);
            manager_form.setVisible(true);
            bill_form.setVisible(false);

            clients_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            department_btn.setStyle("-fx-background-color:transparent");
            account_btn.setStyle("-fx-background-color:transparent");
            managers_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            bills_btn.setStyle("-fx-background-color:transparent");

            managersShowListData();
        } else if (event.getSource() == bills_btn) {
            home_form.setVisible(false);
            clients_form.setVisible(false);
            department_form.setVisible(false);
            account_form.setVisible(false);
            manager_form.setVisible(false);
            bill_form.setVisible(true);

            clients_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            department_btn.setStyle("-fx-background-color:transparent");
            account_btn.setStyle("-fx-background-color:transparent");
            managers_btn.setStyle("-fx-background-color:transparent");
            bills_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");

            billsShowListData();
        }
    }

    @FXML
    void initialize() {
        username.setText(currentUser.getLogin());
        homeDisplayTotalEnrolledClient();
        homeDisplayTotalEnrolledManagers();
        homeDisplayTotalEnrolledDepartments();
        homeDisplayTotalEnrolledChart();
    }
}
