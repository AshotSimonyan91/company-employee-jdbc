import model.Company;
import model.Employee;

import java.sql.*;
import java.util.Scanner;

public class Secretary implements Commands {
    private boolean isTrue = true;
    private final Scanner scanner;
    private final Connection connection;

    public Secretary(Scanner scanner, Connection connection) {
        this.scanner = scanner;
        this.connection = connection;
    }

    public void working() {
        while (isTrue) {
            printCommands();
            switch (scanner.nextLine()) {
                case EXIT -> isTrue = false;
                case ADD_EMPLOYEE -> addEmployee();
                case ADD_COMPANY -> addCompany();
                case PRINT_EMPLOYEE -> printAllEmployee();
                case SEARCH_EMPLOYEE_BY_ID -> searchEmployeeByID();
                case SEARCH_EMPLOYEE_BY_COMPANY_ID -> searchEmployeeByCompanyID();
                case PRINT_ALL_COMPANIES -> printAllCompany();
            }
        }
    }

    private void printAllEmployee() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from employee;");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int companyId = resultSet.getInt("company_id");
                System.out.printf("[id: %d , name: %s , surname: %s ,  email: %s , company_id: %d] \n", id, name, surname, email, companyId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCompany() {
        Company company = new Company();

        System.out.println("\u001B[33m" + "Please input company name : " + "\u001B[0m");
        company.setName(scanner.nextLine());
        System.out.println("\u001B[33m" + "Please input company country : " + "\u001B[0m");
        company.setCountry(scanner.nextLine());
        String query = "insert into company (name,country) values (?,?);";

        try (PreparedStatement addCompanyStatement = connection.prepareStatement(query)) {
            addCompanyStatement.setString(1, company.getName());
            addCompanyStatement.setString(2, company.getCountry());
            addCompanyStatement.executeUpdate();
            System.err.println("Company was added");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void addEmployee() {
        Employee employee = new Employee();

        System.out.println("\u001B[33m" + "Please input employee name: " + "\u001B[0m");
        employee.setName(scanner.nextLine());

        System.out.println("\u001B[33m" + "Please input employee surname: " + "\u001B[0m");
        employee.setSurname(scanner.nextLine());

        System.out.println("\u001B[33m" + "Please input employee email: " + "\u001B[0m");
        employee.setEmail(scanner.nextLine());


        System.out.println("\u001B[33m" + "Please input employee company by company ID: " + "\u001B[0m");

        printAllCompany();

        employee.setCompanyId(Integer.parseInt(scanner.nextLine()));

        String query = "insert into employee (name,surname,email,company_id) values (?,?,?,?);";

        try (PreparedStatement addEmployeeStatement = connection.prepareStatement(query)) {
            addEmployeeStatement.setString(1, employee.getName());
            addEmployeeStatement.setString(2, employee.getSurname());
            addEmployeeStatement.setString(3, employee.getEmail());
            addEmployeeStatement.setInt(4, employee.getCompanyId());
            addEmployeeStatement.executeUpdate();

            System.err.println("Employee added");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void printAllCompany() {
        try (Statement statement1 = connection.createStatement()) {
            ResultSet resultSet = statement1.executeQuery("select * from company");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                System.out.printf("[id: %d ,  name: %s ,  country: %s] \n", id, name, country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchEmployeeByID() {
        printAllEmployee();
        System.out.println("\u001B[33m" + "Please input employee ID for search" + "\u001B[0m");
        String str = scanner.nextLine();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from employee where id = " + str + " ;");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int companyId = resultSet.getInt("company_id");
                System.out.printf("[id: %d , name: %s , surname: %s ,  email: %s , company_id: %d] \n", id, name, surname, email, companyId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchEmployeeByCompanyID() {
        printAllCompany();
        System.out.println("\u001B[33m" + "Please input  company_id for search employee" + "\u001B[0m");
        String str = scanner.nextLine();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from employee where company_id = " + str + " ;");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int companyId = resultSet.getInt("company_id");
                System.out.printf("[id: %d , name: %s , surname: %s ,  email: %s , company_id: %d] \n", id, name, surname, email, companyId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
