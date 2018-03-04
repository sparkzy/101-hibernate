package com.revature.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

public class Status {
	@Id
	@Column(name = "status_id")
	@SequenceGenerator(name = "101_status_id_seq", sequenceName = "101_status_id_seq")
	@GeneratedValue(generator = "101_status_id_seq", strategy = GenerationType.AUTO)
	private int statusId;

	@Column(name = "status_name")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private Post statusName;
}
