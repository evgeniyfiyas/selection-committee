package by.grsu.fiyas.datamodel;

import java.util.Map;

public class TrainingList extends AbstractModel {
	private Map<AcademicSubject, Integer> totalMark;

	public Map<AcademicSubject, Integer> getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(Map<AcademicSubject, Integer> totalMark) {
		this.totalMark = totalMark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((totalMark == null) ? 0 : totalMark.hashCode());
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
		if (totalMark == null) {
			if (other.totalMark != null)
				return false;
		} else if (!totalMark.equals(other.totalMark))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainingList [totalMark=" + totalMark + "]";
	}
}
