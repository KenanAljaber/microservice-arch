package com.kruger.microservice.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="football_player")
@XmlRootElement

  @NamedQueries({
  
  @NamedQuery(name="FootBallPlayer.findAll", query=
  "SELECT f FROM FootBallPlayer f") })
 
public class FootBallPlayer implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@NotNull
	@Size(min=1, max=50)
	@Column(name="name")
	private String name;
	
	@NotNull
	@Size(min=1, max=50)
	@Column(name="surname")
	private String surname;
	
	@Basic(optional = false)
	@NotNull
	@Column(name="age")
	private int age;
	
	@NotNull
	@Size(min=1, max=50)
	@Column(name="team")
	private String team;
	
	@NotNull
	@Size(min=1, max=50)
	@Column(name="position")
	private String position;
	@Column(name="price")
	private BigInteger price;
	
	public FootBallPlayer() {
	}
	
	public FootBallPlayer(Integer id, @NotNull @Size(min = 1, max = 50) String name,
			@NotNull @Size(min = 1, max = 50) String surname, @NotNull int age,
			@NotNull @Size(min = 1, max = 50) String team, @NotNull @Size(min = 1, max = 50) String position,
			BigInteger price) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.team = team;
		this.position = position;
		this.price = price;
	}
	
	

	public FootBallPlayer(@NotNull @Size(min = 1, max = 50) String name,
			@NotNull @Size(min = 1, max = 50) String surname, @NotNull int age,
			@NotNull @Size(min = 1, max = 50) String team, @NotNull @Size(min = 1, max = 50) String position,
			BigInteger price) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.team = team;
		this.position = position;
		this.price = price;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "FootBallPlayer [id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", team="
				+ team + ", position=" + position + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, name, position, price, surname, team);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FootBallPlayer other = (FootBallPlayer) obj;
		return age == other.age && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(position, other.position) && Objects.equals(price, other.price)
				&& Objects.equals(surname, other.surname) && Objects.equals(team, other.team);
	}
	
	
	
	}

	
