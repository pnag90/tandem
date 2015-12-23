package tandem.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="users" ,catalog="tandem")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String password;
	private String firstName;
	private String midName;
	private String lastName;
	private String email;
	private String photo;
	private String token;
	private boolean active;
	private byte online;
	private Date createdAt;
	private Set<OauthProvider> oauthProviders = new HashSet<OauthProvider>(0);
	private Profile profile;
	private Set<Authorities> authoritieses = new HashSet<Authorities>(0);

	public Users() {
	}


	public Users(String username, String password, String firstName, String lastName, String email, boolean active, byte online, Date createdAt) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.active = active;
		this.online = online;
		this.createdAt = createdAt;
	}
	public Users(String username, String password, String firstName, String midName, String lastName, String email, String photo, String token, boolean active, byte online, Date createdAt, Set<OauthProvider> oauthProviders, Profile profile, Set<Authorities> authoritieses) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.email = email;
		this.photo = photo;
		this.token = token;
		this.active = active;
		this.online = online;
		this.createdAt = createdAt;
		this.oauthProviders = oauthProviders;
		this.profile = profile;
		this.authoritieses = authoritieses;
	}

	@Id @GeneratedValue(strategy=IDENTITY)


	@Column(name="user_id", unique=true, nullable=false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Column(name="username", nullable=false, length=45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Column(name="password", nullable=false, length=45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name="first_name", nullable=false, length=55)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="mid_name", length=55)
	public String getMidName() {
		return this.midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}


	@Column(name="last_name", nullable=false, length=55)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Column(name="email", nullable=false, length=55)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="photo")
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	@Column(name="token", length=25)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	@Column(name="active", nullable=false)
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	@Column(name="online", nullable=false)
	public byte getOnline() {
		return this.online;
	}

	public void setOnline(byte online) {
		this.online = online;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", nullable=false, length=19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	//@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
	@OneToMany(mappedBy="users", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<OauthProvider> getOauthProviders() {
		return this.oauthProviders;
	}

	public void setOauthProviders(Set<OauthProvider> oauthProviders) {
		this.oauthProviders = oauthProviders;
	}

	//@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
	@OneToOne(mappedBy="users", cascade=CascadeType.ALL)
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	//@OneToMany(fetch=FetchType.LAZY, mappedBy="users")
	@OneToMany(mappedBy="users", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<Authorities> getAuthoritieses() {
		return this.authoritieses;
	}

	public void setAuthoritieses(Set<Authorities> authoritieses) {
		this.authoritieses = authoritieses;
	}




}


