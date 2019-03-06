package ie.demo.service;

import org.springframework.stereotype.Service;

@Service
public interface AbstractUserFactory<T> {

	T createUser(String username, String password, Integer studentCardId, int userTypeId, String email);

}
