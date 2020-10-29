package kr.lineus.aipalm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "portfolio_category")
public class PortfolioCategory {
	@Id
	private String id;
	@Column(name = "category_name")
	private String category_name;
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
	private Set<PortfolioDataEntity> portfolio = new HashSet<PortfolioDataEntity>();
}
