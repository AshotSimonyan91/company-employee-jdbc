public interface Commands {

    String EXIT = "0";
    String ADD_EMPLOYEE = "1";
    String ADD_COMPANY = "2";
    String PRINT_EMPLOYEE = "3";
    String SEARCH_EMPLOYEE_BY_ID = "4";
    String SEARCH_EMPLOYEE_BY_COMPANY_ID = "5";
    String PRINT_ALL_COMPANIES = "6";

    default void printCommands() {
        System.out.println("Please input " + EXIT + " for exit");
        System.out.println("Please input " + ADD_EMPLOYEE + " for add employee");
        System.out.println("Please input " + ADD_COMPANY + " for add company");
        System.out.println("Please input " + PRINT_EMPLOYEE + " for print all employee");
        System.out.println("Please input " + SEARCH_EMPLOYEE_BY_ID + " for search employee by employee ID");
        System.out.println("Please input " + SEARCH_EMPLOYEE_BY_COMPANY_ID + " for search employee by company ID");
        System.out.println("Please input " + PRINT_ALL_COMPANIES + " for print all companies");
    }

}
