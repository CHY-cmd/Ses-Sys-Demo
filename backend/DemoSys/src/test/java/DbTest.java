import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {

	public static void main(String[] args) {
		// 数据库连接信息
		String url = "jdbc:mysql://ses-sys-chcmd.c.aivencloud.com:27153/defaultdb";
		String user = "avnadmin";
		String password = "AVNS_L3pH58oBsdMwsvrYGet";

		// 定义查询语句
		String query = "SELECT CONTR_PROJECT_NM FROM CONTRACT_TBL";

		// JDBC 连接和查询
		try (Connection conn = DriverManager.getConnection(url, user, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			// 打印查询结果
			while (rs.next()) {
				String projectName = rs.getString("CONTR_PROJECT_NM");
				System.out.println("项目名称: " + projectName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
