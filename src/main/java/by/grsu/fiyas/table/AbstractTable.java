package by.grsu.fiyas.table;

import java.util.List;

public abstract class AbstractTable<E> {

	public abstract List<E> getRows();

	public abstract void setRows(final List<E> rows);
}
