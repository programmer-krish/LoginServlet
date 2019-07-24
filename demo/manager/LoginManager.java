package com.wisdom.demo.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wisdom.demo.util.DbUtil;
import com.wisdom.logindemo.pojo.Login;

public class LoginManager {

	public boolean doAuth(Login login) {
		boolean success = false;

		try {
			success = validateUser(login);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	private boolean validateUser(Login login) throws SQLException {

		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connection = DbUtil.getConnection();

			String sql = "SELECT count(*) FROM login WHERE username = ? AND password = ?";

			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, login.getUserName());
			pstmt.setString(2, login.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int numberOfRows = rs.getInt(1);///
				System.out.println("numberOfRows= " + numberOfRows);
				if (numberOfRows > 0)
					result = true;
			} else {
				System.out.println("error: could not get the record counts");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		} finally {
			rs.close();
			pstmt.close();
		}

		return result;
	}

}