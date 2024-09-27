package co.edu.unbosque.apeh.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "horario")
@Data
public class HorarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // For auto-increment
    private Long id;
    
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer credits;

    @Column(nullable = false)
    private String day;

    @Column(nullable = false)
    private String hourStart;

    @Column(nullable = false)
    private String hourFinish;

    @Column(nullable = false)
    private String modality;

    @Column(nullable = false)
    private String schedule;

    @ManyToOne
    @JoinColumn(name = "userThatInserted", nullable = false)
    private UserModel userThatInserted;

    @Column(nullable = false)
    private boolean status;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHourStart() {
		return hourStart;
	}

	public void setHourStart(String hourStart) {
		this.hourStart = hourStart;
	}

	public String getHourFinish() {
		return hourFinish;
	}

	public void setHourFinish(String hourFinish) {
		this.hourFinish = hourFinish;
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public UserModel getUserThatInserted() {
		return userThatInserted;
	}

	public void setUserThatInserted(UserModel userThatInserted) {
		this.userThatInserted = userThatInserted;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
   
}
