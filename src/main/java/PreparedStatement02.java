import java.sql.*;
/*
CallableStatement:SQL de geriye data return eden methodlara fonksiyon(function)
                         geriye data return etmeyenlere ise prosedür denir.
                  Connection ın prepareCall methodu ile callablestatement oluşturularak
                  Java uygulamamızda SQL fonksiyonları/prosedürleri çağırılabilir.
CallableStatement, Statement i extend eder.
 */
public class PreparedStatement02 {

    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        //ÖRNEK1:Prepared Statement kullanarak ogrenciler tablosundan Matematik bölümünde okuyanları siliniz.
        String query="DELETE FROM ogrenciler where bolum ILIKE ?";
        PreparedStatement prst=con.prepareStatement(query);
        prst.setString(1,"matematik");
        int deleted = prst.executeUpdate();
        System.out.println("Silinen veri sayısı: "+deleted);

        //ÖRNEK2:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475, kampus='Merkez') bölümünü ekleyiniz.
        String query2="INSERT INTO bolumler VALUES(?,?,?,?)";
        PreparedStatement prst2=con.prepareStatement(query2);
        prst2.setInt(1,5006);
        prst2.setString(2,"Yazılım Mühendisliği");
        prst2.setInt(3,475);
        prst2.setString(4,"Merkez");
//        int inserted = prst2.executeUpdate();
//        System.out.println("Eklenen veri sayısı: "+inserted);

        //SELECT * FROM bolumler WHERE bolum ILIKE ?

        prst2.close();
        prst.close();
        st.close();
        con.close();
    }
}
