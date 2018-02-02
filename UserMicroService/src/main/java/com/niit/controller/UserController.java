package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.*;
import com.niit.model.*;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	UserDAO userdao;
	
	//All users fetching
			@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
		    public ResponseEntity<List<UserModel>> listAllUsers() 
		    {
		        List<UserModel> users = userdao.getAllusers();
		        if(users.isEmpty())
		        {
		            return new ResponseEntity<List<UserModel>>(HttpStatus.NO_CONTENT);
		        }
		        else
		        {
		        	return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
		        }
		    }
			
			
	//Insert single user 
			@RequestMapping(value = "/insert", method = RequestMethod.POST)
		    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user)
		    {
					userdao.insert(user);
		        	return new ResponseEntity<UserModel>(user, HttpStatus.OK);
		    }
			
			
	//updation
			@PostMapping("/update")
		    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) 
		    {
		        if (user == null) 
		        {
		            System.out.println("Unable to update. User with email id " + user.getEmail() + " not found");
		            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		        }
		        else
		        {
		        	userdao.updateUser(user);
		        	return new ResponseEntity<UserModel>(user,HttpStatus.OK);
		        }
		    }
			
			
	//Delete
			@RequestMapping("/delete/{email}")
		    public ResponseEntity<UserModel> deleteUser(@PathVariable("email") String email)
			{
		       
		        UserModel user1 = userdao.getuserbyEmail(email);
		        if (user1 == null) 
		        {
		            System.out.println("Unable to delete. User with email " + user1.getEmail() + " not found");
		            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		        }
		        else
		        {
		        userdao.deleteUser(user1);
		        return new ResponseEntity<UserModel>(user1,HttpStatus.OK);
		        }
		    }
			
			
	//single user fetching
			//@RequestMapping(value = "/getSingleUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
		@PostMapping("/getSingleUser")
		    public ResponseEntity<UserModel> getUser(@PathVariable("email") String email,@RequestBody UserModel user)
		    {
		        UserModel user1=userdao.getuserbyEmail(email);
		        if (user1 == null)
		        {
		            System.out.println("User with email id " + user.getEmail() + " not found");
		            return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<UserModel>(user1, HttpStatus.OK);
		    }
			
			
}









			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

