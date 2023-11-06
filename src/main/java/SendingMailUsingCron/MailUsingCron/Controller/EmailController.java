package SendingMailUsingCron.MailUsingCron.Controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SendingMailUsingCron.MailUsingCron.Entity.EmailDetails;
import SendingMailUsingCron.MailUsingCron.Service.MailService;

@RestController
@RequestMapping("/api")
public class EmailController {

	@Autowired
	private MailService mailService;

	@PostMapping("/mail")
	public ResponseEntity<?> sendSimpleMail(@RequestBody EmailDetails details) {
		try {
			int otp = mailService.generateOTP();
			this.OtpGenSendEmail(details.getToEmail(), otp, new Date());

			mailService.sendMail(details, otp);
			System.out.println("email sent");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("attach/mail")
	public ResponseEntity<?> sendAttchementMail(@RequestBody EmailDetails details) {
		try {

			mailService.sendEmailMessage(details);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private void OtpGenSendEmail(String email, int otp, Date expiry) {

		String otp1 = String.valueOf(otp);

		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.MINUTE, 5);

		this.mailService.entity(email, otp1, expiry);

	}

}
