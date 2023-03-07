import java.sql.*;

public class PreparedStatement01 {
/*
PreparedStatement Statement interface'ini extend eder.
Statement ile oluşturduğumuz sorgu için database'de bellekte sorgunun bir örneği oluşturulur.
Sorgu her çalıştırıldığında yeni bir örneği oluşturulur.
PreparedStatement; birden fazla kez çalıştırılabilen parametrelendirilmiş SQL sorgularını temsil eder.
PreparedStatement ile sorgu oluşturduğumuzda bu sorgunun örneği DB de bellekte bir kere tutulur,
    aynı sorgu her çalıştırıldığında aynı önceki örneği kullanılır.
 */
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        //ÖRNEK1:(Prepared Statement kullanarak) bolumler tablosunda Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
//      String query="UPDATE bolumer SET taban_puanı=475 WHERE bolum ILIKE 'matematik'";

        //prepared statement için parametreli queryi yaz
        String query="UPDATE bolumler SET taban_puanı=? WHERE bolum ILIKE ?";
        //prepared statement oluştur
        PreparedStatement prst=con.prepareStatement(query);
        //parametrelerin değerlerini gir
        prst.setInt(1,475);
        prst.setString(2,"matematik");
        //prepared statement ile queryi çalıştır
        int updated = prst.executeUpdate();
        System.out.println("Etkilenen kayıt sayısı: "+updated);

        ResultSet rs=st.executeQuery("SELECT * FROM bolumler");
        while (rs.next()){
            System.out.println(rs.getInt("bolum_id")+"--"+rs.getString("bolum")+"--"+rs.getInt("taban_puanı"));
        }

        System.out.println("---------------ORNEK2---------------");
        //ÖRNEK2:Prepared Statement kullanarak bolumler tablosunda Edebiyat bölümünün taban_puanı'nı 455 olarak güncelleyiniz.
        prst.setInt(1,455);
        prst.setString(2,"edebiyat");
        int updated2=prst.executeUpdate();
        System.out.println("Etkilenen kayıt sayısı: "+updated2);

        prst.close();
        st.close();
        con.close();
    }
}
