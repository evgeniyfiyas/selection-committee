package by.grsu.fiyas.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.fiyas.dataaccess.AbstractDao;
import by.grsu.fiyas.datamodel.TrainingList;
import by.grsu.fiyas.table.TrainingListTable;

public class TrainingListDao extends AbstractDao<TrainingListTable, TrainingList> {
	public TrainingListDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(TrainingList newTrainingList) {
		newTrainingList.setId(getNextId());
		final TrainingListTable trainingListTable = deserializeFromXml();
		trainingListTable.getRows().add(newTrainingList);
		serializeToXml(trainingListTable);
	}

	@Override
	public void update(TrainingList entity) {
		final TrainingListTable trainingListTable = deserializeFromXml();
		for (final TrainingList row : trainingListTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				row.setTotalMark(entity.getTotalMark());
				break;
			}
		}
		serializeToXml(trainingListTable);
	}

	@Override
	public TrainingList get(Serializable id) {
		final TrainingListTable trainingListTable = deserializeFromXml();
		for (final TrainingList row : trainingListTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<TrainingList> getAll() {
		final TrainingListTable trainingListTable = deserializeFromXml();
		return trainingListTable.getRows();
	}

	@Override
	public void delete(Serializable id) {
		final TrainingListTable trainingListTable = deserializeFromXml();
		TrainingList toBeDeleted = null;
		for (final TrainingList row : trainingListTable.getRows()) {
			if (row.getId().equals(id)) {
				toBeDeleted = row;
				break;
			}
		}
		trainingListTable.getRows().remove(toBeDeleted);
		serializeToXml(trainingListTable);
	}

	@Override
	protected Class<TrainingListTable> getTableClass() {
		return TrainingListTable.class;
	}
}
