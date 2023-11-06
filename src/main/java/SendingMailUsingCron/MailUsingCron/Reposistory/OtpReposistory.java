package SendingMailUsingCron.MailUsingCron.Reposistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import SendingMailUsingCron.MailUsingCron.Entity.OtpEntity;

@Repository
public interface OtpReposistory extends JpaRepository<OtpEntity, Long> {

	OtpEntity findByEmailIgnoreCase(String email);

	@Query(value = "delete from otp_entity o where o.email=:email", nativeQuery = true)
	public String deleteAllByEmail(@Param("email") String email);

}
