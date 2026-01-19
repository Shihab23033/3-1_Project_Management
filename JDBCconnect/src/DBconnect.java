import java.sql.*;

public class DBconnect {
    private static final String url = "jdbc:mysql://localhost:3306/student_db";
    private static final String user = "root";
    private static final String pass = "shihab@123";
    public Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public DBconnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException{
        conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
    public void closeAll(){
        try {
            if(this.rs !=null)
                this.rs.close();
            if (this.ps!= null)
                this.ps.close();
            if(this.conn != null)
                this.conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
