package tandem.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "profile", catalog = "tandem")
public class Profile implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer profileId;
	private byte privacy;
	private Byte rating;
	private String nameNepali;
	private Date dob;
	private String aboutMe;
	private String relationship;
	private String phone;
	private String interests;
	private String education;
	private String hobbies;
	private String facebookProfile;
	private String googleProfile;
	private String twitterProfile;
	private String locationGps;
	private Byte langFk;
	private String everythingElse;
	private Date createdAt;
	
	private Users user;
	private int userFk;

	public Profile() {
	}

	public Profile(/*Users user,*/ byte privacy) {
		//this.user = user;
		this.privacy = privacy;
	}

	public Profile(/*Users user,*/ byte privacy, Byte rating, String nameNepali, Date dob, String aboutMe,
			String relationship, String phone, String interests, String education, String hobbies,
			String facebookProfile, String googleProfile, String twitterProfile, String locationGps, Byte langFk,
			String everythingElse, Date createdAt) {
		//this.user = user;
		this.privacy = privacy;
		this.rating = rating;
		this.nameNepali = nameNepali;
		this.dob = dob;
		this.aboutMe = aboutMe;
		this.relationship = relationship;
		this.phone = phone;
		this.interests = interests;
		this.education = education;
		this.hobbies = hobbies;
		this.facebookProfile = facebookProfile;
		this.googleProfile = googleProfile;
		this.twitterProfile = twitterProfile;
		this.locationGps = locationGps;
		this.langFk = langFk;
		this.everythingElse = everythingElse;
		this.createdAt = createdAt;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "profile_id", unique = true, nullable = false)
	public Integer getProfileId() {
		return this.profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	//@ManyToOne(fetch = FetchType.LAZY)
	@OneToOne
	@JoinColumn(name = "user_fk", nullable = false)
	public Users getUsers() {
		return this.user;
	}

	public void setUsers(Users user) {
		this.user = user;
	}

	@Column(name = "privacy", nullable = false)
	public byte getPrivacy() {
		return this.privacy;
	}

	public void setPrivacy(byte privacy) {
		this.privacy = privacy;
	}

	@Column(name = "rating")
	public Byte getRating() {
		return this.rating;
	}

	public void setRating(Byte rating) {
		this.rating = rating;
	}

	@Column(name = "name_nepali")
	public String getNameNepali() {
		return this.nameNepali;
	}

	public void setNameNepali(String nameNepali) {
		this.nameNepali = nameNepali;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dob", length = 19)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(name = "about_me", length = 160)
	public String getAboutMe() {
		return this.aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	@Column(name = "relationship", length = 45)
	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "interests")
	public String getInterests() {
		return this.interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	@Column(name = "education")
	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "hobbies")
	public String getHobbies() {
		return this.hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	@Column(name = "facebook_profile")
	public String getFacebookProfile() {
		return this.facebookProfile;
	}

	public void setFacebookProfile(String facebookProfile) {
		this.facebookProfile = facebookProfile;
	}

	@Column(name = "google_profile")
	public String getGoogleProfile() {
		return this.googleProfile;
	}

	public void setGoogleProfile(String googleProfile) {
		this.googleProfile = googleProfile;
	}

	@Column(name = "twitter_profile")
	public String getTwitterProfile() {
		return this.twitterProfile;
	}

	public void setTwitterProfile(String twitterProfile) {
		this.twitterProfile = twitterProfile;
	}

	@Column(name = "location_gps")
	public String getLocationGps() {
		return this.locationGps;
	}

	public void setLocationGps(String locationGps) {
		this.locationGps = locationGps;
	}

	@Column(name = "lang_fk")
	public Byte getLangFk() {
		return this.langFk;
	}

	public void setLangFk(Byte langFk) {
		this.langFk = langFk;
	}

	@Column(name = "everything_else")
	public String getEverythingElse() {
		return this.everythingElse;
	}

	public void setEverythingElse(String everythingElse) {
		this.everythingElse = everythingElse;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
