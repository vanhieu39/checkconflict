package kr.lineus.aipalm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio_image")
public class PortfolioImageEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	@Column(name = "file_name")
	private String fileName;
	@Column(name = "file_type")
	private String fileType;
	@Column(name = "data",columnDefinition = "BLOB")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@Lob
	private byte[] data;
	@EqualsAndHashCode.Exclude
	@ManyToOne
	@JoinColumn(name = "portfolio_id",nullable = false)
	private PortfolioDataEntity portfolio;
}
