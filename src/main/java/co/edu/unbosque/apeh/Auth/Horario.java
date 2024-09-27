package co.edu.unbosque.apeh.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

    String code;
    String name;
    Integer credits;
    String day;
    String hourStart;
    String hourFinish;
    String modality;
    String schedule;
    String userThatInserted;

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
	public String getUserThatInserted() {
		return userThatInserted;
	}
	public void setUserThatInserted(String userThatInserted) {
		this.userThatInserted = userThatInserted;
	}    
    
}
