package tandem.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OauthProviderId generated by hbm2java
 */
@Embeddable
public class OauthProviderId implements java.io.Serializable {

	private String oauthUid;
	private String oauthProvider;

	public OauthProviderId() {
	}

	public OauthProviderId(String oauthUid, String oauthProvider) {
		this.oauthUid = oauthUid;
		this.oauthProvider = oauthProvider;
	}

	@Column(name = "oauth_uid", nullable = false)
	public String getOauthUid() {
		return this.oauthUid;
	}

	public void setOauthUid(String oauthUid) {
		this.oauthUid = oauthUid;
	}

	@Column(name = "oauth_provider", nullable = false, length = 10)
	public String getOauthProvider() {
		return this.oauthProvider;
	}

	public void setOauthProvider(String oauthProvider) {
		this.oauthProvider = oauthProvider;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OauthProviderId))
			return false;
		OauthProviderId castOther = (OauthProviderId) other;

		return (this.getOauthUid() == castOther.getOauthUid())
				&& ((this.getOauthProvider() == castOther.getOauthProvider())
						|| (this.getOauthProvider() != null && castOther.getOauthProvider() != null
								&& this.getOauthProvider().equals(castOther.getOauthProvider())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		//result = 37 * result + (int) this.getOauthUid();
		result = 37 * result + (getOauthProvider() == null ? 0 : this.getOauthProvider().hashCode());
		return result;
	}

}
