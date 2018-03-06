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

public class Role {
	@Id
	@Column(name = "role_id")
	@SequenceGenerator(name = "101_role_id_seq", sequenceName = "101_role_id_seq")
	@GeneratedValue(generator = "101_role_id_seq", strategy = GenerationType.AUTO)
	private int roleId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Role role;
}
