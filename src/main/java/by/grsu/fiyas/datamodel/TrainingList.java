package by.grsu.fiyas.datamodel;

import java.util.Date;
import java.util.Map;

public class TrainingList extends AbstractModel {
	private Date selectionYear;
	private Student student;
	private Integer averageMark;
	private Faculty faculty;
	
	public Date getSelectionYear() {
		return selectionYear;
	}
	
	public void setSelectionYear(Date selectionYear) {
		this.selectionYear = selectionYear;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public Integer getAverageMark() {
		return averageMark;
	}
	
	public void setAverageMark(Integer averageMark) {
		this.averageMark = averageMark;
	}
	
	public Faculty getFaculty() {
		return faculty;
	}
	
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((averageMark == null) ? 0 : averageMark.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + ((selectionYear == null) ? 0 : selectionYear.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		TrainingList other = (TrainingList) obj;
		if (averageMark == null) {
			if (other.averageMark != null)
				return false;
		} else if (!averageMark.equals(other.averageMark))
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (selectionYear == null) {
			if (other.selectionYear != null)
				return false;
		} else if (!selectionYear.equals(other.selectionYear))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainingList [selectionYear=" + selectionYear + ", student=" + student + ", averageMark=" + averageMark
				+ ", faculty=" + faculty + "]";
	}

}
