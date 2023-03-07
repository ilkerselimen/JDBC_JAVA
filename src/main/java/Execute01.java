import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. ADIM: Driver'ı kaydet
        Class.forName("org.postgresql.Driver");//Java7 ile birlikte kullanımına gerek kalmadı

        //2. ADIM: Database e bağlanma
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");

        //3. ADIM:Statement oluştur
        Statement st = con.createStatement();

        //System.out.println("Connection Success");

        //4. ADIM:Query(sorgu) çalıştırma

        //ÖRNEK 1:"workers" adında bir tablo oluşturup "worker_id,worker_name,salary" sütunlarını ekleyiniz.

        boolean sql1 = st.execute("CREATE TABLE workers(worker_id INT,worker_name VARCHAR(50),salary REAL)");
        System.out.println("sql1 sonuç: " + sql1);

        //execute():DDL veya DQL için kullanılabilir
        //DQL için kullanılmışsa: ResultSet nesnesi alırsa geriye "true" döndürür aksi halde "false" döndürür.
        //DDL için kullanılmışsa: geriye boolean değer olarak "false" döndürür.

        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.
        String query2 = "ALTER TABLE workers ADD COLUMN city varchar(20)";
        st.execute(query2);

        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.
        String query3 = "DROP TABLE workers";
        st.execute(query3);

        //5. ADIM:bağlantı ve statementı kapatma
        st.close();
        con.close();
    }

}
