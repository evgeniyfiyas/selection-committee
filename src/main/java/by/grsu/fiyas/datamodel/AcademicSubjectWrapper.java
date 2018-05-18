package by.grsu.fiyas.datamodel;

public class AcademicSubjectWrapper extends AbstractModel {

	private AcademicSubject subject;
	private Integer mark;
	
	public AcademicSubjectWrapper() { }
	
	public AcademicSubjectWrapper(AcademicSubject subject, Integer mark) {
		this.subject = subject;
		this.mark = mark;
	}
	
	public AcademicSubject getSubject() {
		return subject;
	}
	
	public void setSubject(AcademicSubject subject) {
		this.subject = subject;
	}
	
	public Integer getMark() {
		return mark;
	}
	
	public void setMark(Integer mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "SubjectWrapper [subject=" + subject + ", mark=" + mark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcademicSubjectWrapper other = (AcademicSubjectWrapper) obj;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
	
	
}
