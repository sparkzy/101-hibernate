package com.revature.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {
	@Id
	@Column(name = "status_id")
	@SequenceGenerator(name = "status_id_seq", sequenceName = "status_id_seq")
	@GeneratedValue(generator = "status_id_seq", strategy = GenerationType.AUTO)
	private int statusId;

	@Column(name = "status_name")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private Set<Post> statusName;

	public Status() {
		super();
	}

	public Status(int statusId, Set<Post> statusName) {
		this.statusId = statusId;
		this.statusName = statusName;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public Set<Post> getStatusName() {
		return statusName;
	}

	public void setStatusName(Set<Post> statusName) {
		this.statusName = statusName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + statusId;
		result = prime * result + ((statusName == null) ? 0 : statusName.hashCode());
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
		Status other = (Status) obj;
		if (statusId != other.statusId)
			return false;
		if (statusName == null) {
			if (other.statusName != null)
				return false;
		} else if (!statusName.equals(other.statusName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", statusName=" + statusName + "]";
	}
}
