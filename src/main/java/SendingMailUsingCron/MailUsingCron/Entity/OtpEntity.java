package SendingMailUsingCron.MailUsingCron.Entity;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class OtpEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 10, min = 10)
	@Column(name = "otp")
	private String otp;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "expired_At")
	private Date ExpiredAt;

	@CreationTimestamp
	@Column(name = "created_At")
	private Date CreatedAt;

	@UpdateTimestamp
	@Column(name = "updated_At")
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExpiredAt() {
		return ExpiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		ExpiredAt = expiredAt;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public OtpEntity(Long id, @Size(max = 10, min = 10) String otp, String email, Date expiredAt, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.otp = otp;
		this.email = email;
		ExpiredAt = expiredAt;
		CreatedAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public OtpEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
