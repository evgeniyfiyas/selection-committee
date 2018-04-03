package by.grsu.fiyas.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends AbstractModel {
	private String name;
	private Integer selectionPlan;
	private List<AcademicSubject> subjects;
	private Boolean isEnabled;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSelectionPlan() {
		return selectionPlan;
	}

	public void setSelectionPlan(Integer selectionPlan) {
		this.selectionPlan = selectionPlan;
	}

	public List<AcademicSubject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<AcademicSubject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((isEnabled == null) ? 0 : isEnabled.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((selectionPlan == null) ? 0 : selectionPlan.hashCode());
		result = prime * result + ((subjects == null) ? 0 : subjects.hashCode());
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
		Faculty other = (Faculty) obj;
		if (isEnabled == null) {
			if (other.isEnabled != null)
				return false;
		} else if (!isEnabled.equals(other.isEnabled))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (selectionPlan == null) {
			if (other.selectionPlan != null)
				return false;
		} else if (!selectionPlan.equals(other.selectionPlan))
			return false;
		if (subjects == null) {
			if (other.subjects != null)
				return false;
		} else if (!subjects.equals(other.subjects))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [name=" + name + ", selectionPlan=" + selectionPlan + ", subjects=" + subjects + ", isEnabled="
				+ isEnabled + "]";
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}	
}
