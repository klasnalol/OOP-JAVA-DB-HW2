import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Application {
    public static void main(String[] args) {
        Database db = Database.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose my friend:");
        System.out.println("1 View existing records");
        System.out.println("2 Add new record");
        System.out.println("3 Update existing record");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                viewRecords(db);
                break;
            case 2:
                addNewRecord(db, scanner);
                break;
            case 3:
                updateRecord(db, scanner);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private static void viewRecords(Database db) {
        // ALLL SHIET
        ResultSet resultSet = db.executeQuery("SELECT * FROM test");
        System.out.println("Results of SELECT query:");
        try {
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("name") +
                        ", ID: " + resultSet.getString("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addNewRecord(Database db, Scanner scanner) {
        // ADD
        System.out.print("Enter the new name: ");
        String newName = scanner.next();
        System.out.print("Enter the new ID: ");
        int newId = scanner.nextInt();
        int rowsAffected = db.executeUpdate("INSERT INTO test (name, ID) VALUES ('" + newName + "', " + newId + ")");
        System.out.println("\nRows affected by INSERT query: " + rowsAffected);
    }

    private static void updateRecord(Database db, Scanner scanner) {
        // update
        System.out.print("Enter the new name: ");
        String newName = scanner.next();
        System.out.print("Enter the ID of the record to update: ");
        int idToUpdate = scanner.nextInt();
        int rowsAffected = db.executeUpdate("UPDATE test SET name = '" + newName + "' WHERE ID = " + idToUpdate);
        System.out.println("\nRows affected by UPDATE query: " + rowsAffected);
    }
}

