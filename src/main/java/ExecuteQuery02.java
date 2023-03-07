import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        System.out.println("---------------ORNEK1---------------");
        //ÖRNEK1:bolumler tablosunda taban puanı en yüksek 2. bölümün ismini ve puanını yazdırınız.
        String query1="select bolum,taban_puanı from bolumler order by taban_puanı desc offset 1 limit 1";
        ResultSet rs1=st.executeQuery(query1);
        while (rs1.next()){
            System.out.println(rs1.getString("bolum")+"--"+rs1.getInt("taban_puanı"));
        }
        //subquery çözümü
        String query1Diff="select bolum,taban_puanı from bolumler where taban_puanı="+
                "(select max(taban_puanı) from bolumler where taban_puanı<(select max(taban_puanı) from bolumler))";

        System.out.println("---------------ORNEK2---------------");
        //ÖRNEK2:Bölüm isimlerini, kampüslerini ve
        //her bölümde okuyan öğrencilerin en yüksek puanlarını listeleyiniz.
        String query2="select bolum,kampus,(select max(puan) from ogrenciler where bolumler.bolum=ogrenciler.bolum) max_puan from bolumler";
        ResultSet rs2=st.executeQuery(query2);
        while (rs2.next()){
            System.out.println(rs2.getString("bolum")+"--"+rs2.getString("kampus")+"--"+rs2.getInt("max_puan"));
        }
        st.close();
        con.close();
    }
}
