package by.grsu.fiyas.datamodel;

import java.util.Date;
import java.util.Map;

public class Student extends AbstractModel {
	private String name;
	private Faculty faculty;
	private Integer averageMark; 
	private Date dateOfEnrollment; 	
	private Map<AcademicSubject, Integer> marks;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Faculty getFaculty() {
		return faculty;
	}
	
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	public Integer getAverageMark() {
		return averageMark;
	}
	
	public void setAverageMark(Integer averageMark) {
		this.averageMark = averageMark;
	}
	
	public Date getDateOfEnrollment() {
		return dateOfEnrollment;
	}
	
	public void setDateOfEnrollment(Date dateOfEnrollment) {
		this.dateOfEnrollment = dateOfEnrollment;
	}
	
	public Map<AcademicSubject, Integer> getMarks() {
		return marks;
	}
	
	public void setMarks(Map<AcademicSubject, Integer> marks) {
		this.marks = marks;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((averageMark == null) ? 0 : averageMark.hashCode());
		result = prime * result + ((dateOfEnrollment == null) ? 0 : dateOfEnrollment.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + ((marks == null) ? 0 : marks.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (averageMark == null) {
			if (other.averageMark != null)
				return false;
		} else if (!averageMark.equals(other.averageMark))
			return false;
		if (dateOfEnrollment == null) {
			if (other.dateOfEnrollment != null)
				return false;
		} else if (!dateOfEnrollment.equals(other.dateOfEnrollment))
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (marks == null) {
			if (other.marks != null)
				return false;
		} else if (!marks.equals(other.marks))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", faculty=" + faculty + ", averageMark=" + averageMark + ", dateOfEnrollment="
				+ dateOfEnrollment + ", marks=" + marks + "]";
	}	
}
