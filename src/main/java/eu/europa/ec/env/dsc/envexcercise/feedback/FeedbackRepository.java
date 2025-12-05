package eu.europa.ec.env.dsc.envexcercise.feedback;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByContactType(ContactType contactType, Sort sort);
}
