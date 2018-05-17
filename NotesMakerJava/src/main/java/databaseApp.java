import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;

public class databaseApp  {
    String userName = "root";
    String password = "";
    String connectionURL = "jdbc:mysql://localhost:3308/dbnote";
    int i;
    ObservableList<Note> list;

    public  databaseApp() throws ParserConfigurationException, IOException, ClassNotFoundException  {
        list = FXCollections.observableArrayList();
        //Class.forName("com.mysql.jdbs.Driver");
    }

    ObservableList<Note> getNotes () {
        String s;
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM notes");
            while (resultSet.next()) {
                list.add(new Note(resultSet.getString(2), (resultSet.getDate(3))));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public void setNote (String s) {
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password)) {
            Statement statement = connection.createStatement();

            ResultSet idSet = statement.executeQuery("SELECT max(id) FROM notes");
            while (idSet.next()) {
                i = idSet.getInt(1);
            }

            i++;
            s = "INSERT INTO notes VALUES (" + i + ", " + "'" + s + "'" + ", " + "NOW())";
            statement.executeUpdate(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
