import java.sql.*;

public class salesforce {

    static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    static final String USER = "root";
    static final String PASS = "aneja";
    // static final String QUERY = "SELECT * from dell";

    public static void main(String[] args) {
     
        try {
//            Class.forName("com.mysql.jdbc.Driver");
        	  Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String create_table_query = "create table Movies(id int not null auto_increment,movie_name varchar(200) not null,lead_actor varchar(100),lead_actress varchar(100),year_of_release Year not null, director_name varchar(100) not null, primary key(id));";

            int rs = stmt.executeUpdate(create_table_query);

            String insert_data = "insert into Movies (movie_name,lead_actor,lead_actress,year_of_release,director_name) values(\"Mission: Impossible\",\"Tom Cruise\",\"Vanessa Kirby\",2018,\"Christopher McQuarrie\"),(\"War\",\"Hrithik Roshan\",\"Vannni Kapoor\",2019,\"Siddharth Anand\"),(\"Kabir Singh\",\"Shahid Kapoor\",\"Kiara Advani\",2019,\"sandeep Reddy\"),(\"Baaghi 3\",\"tiger shroff\",\"Ankita\",2020,\"Ahmed khan\"),(\"Radhe\",\"Salman Khan\",\"Disha Patani\",2021,\"Pradhu Deva\"),(\"Mumbai Saga\",\"John Abraham\",\"Kajal Aggarwal\",2021,\"Sanjay\"),(\"Haider\",\"Shahid Kapoor\",\"Shraddha\",2014,\"Vishal\");";

            rs = stmt.executeUpdate(insert_data);

            System.out.println(rs+ "rows inserted");
            String get_data_query = "Select * from Movies";

            ResultSet db_data = stmt.executeQuery(get_data_query);
            // Extract data from result set
            System.out.println("Data stored in Database");
            while (db_data.next()) {
           
                System.out.print("ID: " + db_data.getInt(1));
                System.out.print(", Movie: " + db_data.getString(2));
                System.out.print(", Lead Actor: " + db_data.getString(3));
                System.out.print(", Lead Actress: " + db_data.getString(4));
                System.out.print(", Year of Release: " + db_data.getInt(5));
                System.out.println(", Director: " + db_data.getString(6));
            }
            db_data.close();
            System.out.println();
            System.out.println("Movies in which Shahid is lead actor");
            String fetch_movies_of_actor="Select movie_name,lead_actor,lead_actress,year_of_release,director_name from Movies where lead_actor=\"Shahid Kapoor\" ;";
            ResultSet res=stmt.executeQuery(fetch_movies_of_actor);
            while (res.next()) {
          
                System.out.print("Movie: " + res.getString(1));
                System.out.print(", Lead Actor: " + res.getString(2));
                System.out.print(", Lead Actress: " + res.getString(3));
                System.out.print(", Year of Release: " + res.getInt(4));
                System.out.println(", Director: " + res.getString(5));
            }
            
            res.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
