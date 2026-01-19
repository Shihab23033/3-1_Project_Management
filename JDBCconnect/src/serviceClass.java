import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class serviceClass extends DBconnect{
    public boolean insert_quire(vlog v) {
        try {
            Connection con = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql= "Insert into vlog(name,statement) values(?,?)";
        try{
            ps= conn.prepareStatement(sql);
            ps.setString(1,v.getName());
            ps.setString(2, v.getStatement());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("data inserted successfully");
        return true;
    }
    public List<vlog> showResults(){
        List<vlog> list = new ArrayList<>();
        try{
            Connection con=getConnection();
            String sql="Select * from vlog";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();

            while (rs.next()){
                vlog v =new vlog();
                v.setName(rs.getString(1));
                v.setStatement(rs.getString(2));
                list.add(v);

            }
            closeAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
