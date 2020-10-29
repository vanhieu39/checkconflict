package kr.lineus.aipalm.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="portfolio")
public class PortfolioDataEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	private String projectName;
	private String clientName;
	private String projectDate;
	private String projectUrl;
	private String content;
	@Column(name = "date_created")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime dateCreated;
	private String user_created_id;
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name ="category_id",nullable = false)
	private PortfolioCategory category;
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "portfolio",fetch = FetchType.LAZY)
	private Set<PortfolioImageEntity> image = new HashSet<PortfolioImageEntity>();
}
