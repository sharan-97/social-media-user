package com.social.media.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.social.media.exception.UserNotFoundException;

@Service
public class UserDAOService {


	private static List<User> user = new ArrayList<>();
	private static Integer userCount = 0;

	static {

		user.add(new User(++userCount,"sharan",LocalDate.of(1997, 02, 23)));
		user.add (new User(++userCount,"silsu",LocalDate.of(1997, 12, 29)));
		user.add (new User(++userCount,"dad",LocalDate.of(1964, 07, 30)));

	}

	public List<User> findAll(){

		return user;
	}

	public User findById(Integer id) {

		Predicate<User> predicate = x->x.getId()==id;

		return user.stream().filter(predicate).findFirst().orElseThrow(()->new UserNotFoundException("The user is not present"));

	}

	public void deleteById(Integer id) {

		Predicate<User> predicate = x->x.getId()==id;
		user.remove(user.stream().filter(predicate).findFirst().orElseThrow(()->new UserNotFoundException("The user is not present")));
		
		/**you can aslo use as shown below, if the predicate matches then it'll remove from the list this is easier actually**/
		//user.removeIf(predicate);
		

	}


	public User saveUser( User newUser) {
		newUser.setId(++userCount);
		user.add(newUser);
		return newUser;

	}





}
