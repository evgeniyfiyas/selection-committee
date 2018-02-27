package by.grsu.fiyas.datamodel;

import java.util.Date;

public class Faculty extends AbstractModel {
	private String name;
	private Date selectionYear;
	private Integer selectionPlan;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getSelectionYear() {
		return selectionYear;
	}
	public void setSelectionYear(Date selectionYear) {
		this.selectionYear = selectionYear;
	}
	public Integer getSelectionPlan() {
		return selectionPlan;
	}
	public void setSelectionPlan(Integer selectionPlan) {
		this.selectionPlan = selectionPlan;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((selectionPlan == null) ? 0 : selectionPlan.hashCode());
		result = prime * result + ((selectionYear == null) ? 0 : selectionYear.hashCode());
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
		if (selectionYear == null) {
			if (other.selectionYear != null)
				return false;
		} else if (!selectionYear.equals(other.selectionYear))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Faculty [name=" + name + ", selectionYear=" + selectionYear + ", selectionPlan=" + selectionPlan + "]";
	}	
}
