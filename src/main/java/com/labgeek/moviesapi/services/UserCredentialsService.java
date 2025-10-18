package com.labgeek.moviesapi.services;

import com.labgeek.moviesapi.model.UserCredentials;
import com.labgeek.moviesapi.repository.UserCredentialsRepository;

public class UserCredentialsService {

	private UserCredentialsRepository repo;
	
	public UserCredentialsService(UserCredentialsRepository repo) {
		this.repo=repo;
	}
	
	public UserCredentials authenticateUser(String username,String password) {
		try {
			return this.repo.authenticate(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
