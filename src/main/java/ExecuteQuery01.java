import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        //Database e bağlanma
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");

        //Statement oluştur; SQL komutlarını DB ye iletmek ve çalıştırmak için
        Statement st=con.createStatement();

        //ÖRNEK 1:id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.
        String query1="select country_name from countries where id between 5 and 10";
        boolean sql1 = st.execute(query1);
        System.out.println("sql1 result: "+sql1);

        //kayıtları görmek için executeQuery() methodunu kullanmalıyız.
        ResultSet resultSet = st.executeQuery(query1);
//        resultSet.next();
//        System.out.println(resultSet.getString("country_name"));
        while (resultSet.next()){
            System.out.println("Ülke ismi: "+resultSet.getString("country_name"));
            //System.out.println(resultSet.getString(1));
        }

        //ResultSet'in first(), last(), next() gibi methodları vardır.
        //ResultSet'te iterator gibi geriye dönüş yoktur.

        System.out.println("----------ORNEK2----------");

        //ÖRNEK 2: phone_code'u 600 den büyük olan ülkelerin "phone_code" ve "country_name" bilgisini listeleyiniz.

        String query2 = "select phone_code,country_name from countries where phone_code>600 order by phone_code";
        ResultSet rs2 = st.executeQuery(query2);

        while (rs2.next()){
            System.out.println(rs2.getInt("phone_code")+"--"+rs2.getString("country_name"));
        }

        System.out.println("----------ORNEK3----------");

        //ÖRNEK 3:developers tablosunda "salary" değeri en düşük olan developerların tüm bilgilerini gösteriniz.

        String query3 = "select * from developers where salary=(select min(salary) from developers)";
        ResultSet rs3 = st.executeQuery(query3);

        while (rs3.next()) {
            System.out.println(rs3.getInt(1)+ "," + rs3.getString(2)+"," + rs3.getDouble(3)+"," + rs3.getString(4));
        }

        System.out.println("----------ORNEK4----------");

        //ÖRNEK 4:Puanı taban puanlarının ortalamasından yüksek olan öğrencilerin isim ve puanlarını listeleyiniz.

        String query4= "select isim,puan from ogrenciler where puan>( select avg(taban_puanı)from bolumler)";
        ResultSet resultSet4=st.executeQuery(query4);
        while (resultSet4.next()){
            System.out.println(resultSet4.getString(1)+"--"+resultSet4.getString(2));
        }

        st.close();
        con.close();
    }
}
