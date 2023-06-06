package com.social.media.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name ="sharan_details")
public class User {
	
	public User() {
		super();
	}
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, message = "name cannot be blank or less than 2 characters")
//	@JsonProperty("User_name")
	@Column(name="user_name")
	private String name;
	
	@Past(message="DOB cant be in the future")
	/**the below annotation can be used to change the name of the field when sending the response back as JSON **/
	@JsonProperty("Birth_date")
	private LocalDate birthDate;
	
	public User(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
//	}
	
}
