package kr.lineus.aipalm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="home_order_layout")
public class HomeOrderLayoutEntity {
	@Id
	private int id;
	private String nameorder;
	private int numorder;
}
