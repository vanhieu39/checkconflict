package kr.lineus.aipalm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blog_author")
public class BlogAuthorEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	private String name;
	private String sns;
	private String intro;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy="author", fetch = FetchType.LAZY)
	private Set<BlogDataEntity> blogs = new HashSet<BlogDataEntity>();
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name="img_id")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	private UserEntity user;
}
