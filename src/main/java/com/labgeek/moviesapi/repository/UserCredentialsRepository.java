package com.labgeek.moviesapi.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.labgeek.moviesapi.config.DataBaseConnection;
import com.labgeek.moviesapi.model.Movie;
import com.labgeek.moviesapi.model.UserCredentials;

public class UserCredentialsRepository {
	UserCredentials userCridentials = null;
	private final DataBaseConnection db;

	public UserCredentialsRepository(DataBaseConnection db) {
		this.db = db;
	}
	
	public UserCredentials authenticate(String username,String password) throws Exception {
		try {
			String query="SELECT * FROM users where username=? and password_hash=?";
			PreparedStatement stmt=this.db.getConnection().prepareStatement(query);
			stmt.setString(1,username);
			stmt.setString(2,password);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				this.userCridentials=new UserCredentials();
				this.userCridentials.setUsername(username);
				this.userCridentials.setPassword(password);
			}
			return this.userCridentials;
		}
		catch(Exception e) {
			throw new Exception();
		}
	}
}
