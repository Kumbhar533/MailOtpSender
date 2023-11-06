package SendingMailUsingCron.MailUsingCron.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import SendingMailUsingCron.MailUsingCron.Entity.EmailDetails;
import SendingMailUsingCron.MailUsingCron.Entity.OtpEntity;
import SendingMailUsingCron.MailUsingCron.Reposistory.OtpReposistory;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	@Autowired
	private OtpReposistory otprepo;

	public void sendEmailMessage(EmailDetails emailDetails) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

		messageHelper.setFrom(sender);
		messageHelper.setTo(emailDetails.getToEmail());
		messageHelper.setSubject(emailDetails.getSubject());
		messageHelper.setText(emailDetails.getBody());
		emailDetails.setAttachment("C:\\attachment/Employees.csv");
		FileSystemResource fileSystemResource = new FileSystemResource(emailDetails.getAttachment());
		messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
		javaMailSender.send(message);

	}

	public String sendMail(EmailDetails emailDetails, int otp) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(emailDetails.getToEmail());
		message.setSubject(emailDetails.getSubject());
		String otp1 = String.valueOf(otp);
		message.setText(otp1);

		javaMailSender.send(message);
		return "email sent";
	}

//	@Scheduled(cron = "0 */1 * * * *")
//	public void sendEmailAfter1min() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(sender);
//		message.setTo("nileshkumbhar@nimapinfotech.com");
//		message.setSubject("subject");
//		message.setText("This is body");
//		javaMailSender.send(message);
//		System.out.println(new Date());
//	}

	public int generateOTP() {

		int min = 100000;
		int max = 999999;

		int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return randomInt;

	}

	public OtpEntity entity(String email, String otp, Date Expiry) {

		OtpEntity otpen = otprepo.findByEmailIgnoreCase(email);
		if (otpen != null) {

			otprepo.delete(otpen);

		}

		OtpEntity entities = new OtpEntity();
		entities.setEmail(email);
		entities.setOtp(otp);
		entities.setExpiredAt(Expiry);
		OtpEntity save = otprepo.save(entities);

		return entities;

	}

}
