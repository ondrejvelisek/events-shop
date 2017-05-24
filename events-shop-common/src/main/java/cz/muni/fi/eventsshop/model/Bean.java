package cz.muni.fi.eventsshop.model;

import java.util.Objects;

public abstract class Bean {

	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Bean bean = (Bean) o;
		return Objects.equals(id, bean.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
