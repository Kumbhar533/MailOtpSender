package SendingMailUsingCron.MailUsingCron.Entity;

public class EmailDetails {

	private String toEmail;
	private String subject;
	private String attachment;
	private String body;

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public EmailDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailDetails(String toEmail, String subject, String attachment, String body) {
		super();
		this.toEmail = toEmail;
		this.subject = subject;
		this.attachment = attachment;
		this.body = body;
	}

}
