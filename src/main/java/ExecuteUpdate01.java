import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        //ÖRNEK1:developers tablosunda maaşı ortalama maaştan az olanların maaşını ortalama maaş ile güncelleyiniz
        String query="update developers set salary=(select avg(salary) from developers)" +
                " where salary<(select avg(salary) from developers)";
//        int updated=st.executeUpdate(query);
//        System.out.println("Guncellenen kayit sayisi: "+updated);

        ResultSet rs1=st.executeQuery("select id,name,salary from developers order by id");
        while (rs1.next()){
            System.out.println(rs1.getString("id")+"--"+rs1.getString("name")+"--"+rs1.getDouble("salary"));
        }

        //ÖRNEK2:developers tablosuna yeni bir developer ekleyiniz.
        String query2="INSERT INTO developers(name,salary,prog_lang) VALUES('İlker',5300,'React')";
//        int inserted = st.executeUpdate(query2);
//        System.out.println("Eklenen kayit sayisi: "+inserted);

        //ÖRNEK3:developers tablosundan id'si 14 olanı siliniz.
        String query3="DELETE FROM developers where id=14";
//        int deleted=st.executeUpdate(query3);
//        System.out.println("Silinen kayit sayisi: "+deleted);

        //ÖRNEK4:developers tablosundan prog_lang Css olanları siliniz.
        String query4="DELETE FROM developers WHERE prog_lang ILIKE 'css'";//buyuk kucuk harf duyarsız
//        int deleted2=st.executeUpdate(query4);
//        System.out.println("Silinen kayit sayisi:"+deleted2);
        st.close();
        con.close();
    }
}
