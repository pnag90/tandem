package tandem.model;

// default package
// Generated 2/dez/2015 23:18:39 by Hibernate Tools 4.3.1.Final

/**
 * AuthoritiesId generated by hbm2java
 */
public class AuthoritiesId implements java.io.Serializable {

	private int userFk;
	private String authority;

	public AuthoritiesId() {
	}

	public AuthoritiesId(int userFk, String authority) {
		this.userFk = userFk;
		this.authority = authority;
	}

	public int getUserFk() {
		return this.userFk;
	}

	public void setUserFk(int userFk) {
		this.userFk = userFk;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuthoritiesId))
			return false;
		AuthoritiesId castOther = (AuthoritiesId) other;

		return (this.getUserFk() == castOther.getUserFk())
				&& ((this.getAuthority() == castOther.getAuthority()) || (this.getAuthority() != null
						&& castOther.getAuthority() != null && this.getAuthority().equals(castOther.getAuthority())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUserFk();
		result = 37 * result + (getAuthority() == null ? 0 : this.getAuthority().hashCode());
		return result;
	}

}
