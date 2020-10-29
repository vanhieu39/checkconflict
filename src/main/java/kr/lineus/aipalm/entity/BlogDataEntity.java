package kr.lineus.aipalm.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="blog")
public class BlogDataEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	private String title;
	
	private int quantityComment;
	
	private String content;
	
	@EqualsAndHashCode.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="img_id")
	private ImageEntity img;
	
	@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	@ManyToOne /* (cascade = CascadeType.ALL) */
	@JoinColumn(name="author_id", nullable = false)
	private BlogAuthorEntity author;
	
	@Column(name="created_datetime")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime createdDateTime;
	
	@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	private BlogCategoryEntity category;
	
	@EqualsAndHashCode.Exclude
	//@ToString.Exclude
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable( name = "blog_tag_join",
				joinColumns = @JoinColumn(name ="blog_id"),
				inverseJoinColumns = @JoinColumn(name ="tag_id"))
	private Set<BlogTagEntity> tags = new HashSet<BlogTagEntity>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
	private List<BlogCommentEntity> comments = new ArrayList<BlogCommentEntity>();
}
