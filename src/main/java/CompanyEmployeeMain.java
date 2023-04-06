import db.DBConnectionProvider;

import java.sql.Connection;
import java.util.Scanner;

public class CompanyEmployeeMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public static void main(String[] args) {

        Secretary secretary = new Secretary(scanner,connection);
        secretary.working();
    }
}
