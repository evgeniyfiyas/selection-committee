package by.grsu.fiyas.datamodel;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Student extends AbstractModel implements Comparable<Student> {
	private String name;
	private Date dateOfEnrollment;
	private Faculty faculty;
	private Integer certificate;
	private List<AcademicSubjectWrapper> marks;

	public Integer getCertificate() {
		return certificate;
	}

	public void setCertificate(Integer certificate) {
		this.certificate = certificate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfEnrollment() {
		return dateOfEnrollment;
	}

	public void setDateOfEnrollment(Date dateOfEnrollment) {
		this.dateOfEnrollment = dateOfEnrollment;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public List<AcademicSubjectWrapper> getMarks() {
		return marks;
	}

	public void setMarks(List<AcademicSubjectWrapper> marks) {
		this.marks = marks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((certificate == null) ? 0 : certificate.hashCode());
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
		if (certificate == null) {
			if (other.certificate != null)
				return false;
		} else if (!certificate.equals(other.certificate))
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
		return "Student [name=" + name + ", dateOfEnrollment=" + dateOfEnrollment + ", faculty=" + faculty
				+ ", certificate=" + certificate + ", marks=" + marks + "]";
	}

	@Override
	public int compareTo(Student that) {
		Double thisAvgMark = (double)((this.marks.get(0).getMark() + this.marks.get(1).getMark() + this.marks.get(2).getMark() + this.certificate) / 4);
		Double thatAvgMark = (double)((that.marks.get(0).getMark() + that.marks.get(1).getMark() + that.marks.get(2).getMark() + that.certificate) / 4);

		return (int)(thatAvgMark - thisAvgMark);
	}

}
