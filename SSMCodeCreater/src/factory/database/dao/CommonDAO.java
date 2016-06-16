package factory.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import factory.fileReader.propertiesReader.PropertiesReader;


/**
 * 数据库连接以及执行sql
 * 
 * @author huangkai
 * 
 */
public class CommonDAO {
	private String url;
	private String username;
	private String password;
	private String jdbcDriver;

	public CommonDAO() {
		PropertiesReader reader = new PropertiesReader("resource/jdbc.properties");
		jdbcDriver =reader.getParam("driverClassName");
		url = reader.getParam("url");
		username = reader.getParam("username");
		password = reader.getParam("password");
	}

	public Connection getConnetion() {
		Connection conn = null;
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public int executeSql(String sql) {
		PreparedStatement stmt = null;
		Connection con = this.getConnetion();
		int rs = 0;
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			System.out.println("SQL:" + sql);
			rs = stmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				con.rollback();
				if (!con.getAutoCommit()) {
					con.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			close(con, stmt, null);
		}
		return rs;
	}

	private void close(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			} finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (con != null) {
							try {
								con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
}
