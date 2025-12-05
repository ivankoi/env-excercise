package eu.europa.ec.env.dsc.envexcercise.web;

import eu.europa.ec.env.dsc.envexcercise.feedback.ContactType;
import eu.europa.ec.env.dsc.envexcercise.feedback.Feedback;
import eu.europa.ec.env.dsc.envexcercise.feedback.FeedbackRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/feedback")
public class AdminFeedbackController {

    private final FeedbackRepository feedbackRepository;

    public AdminFeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @GetMapping
    public String list(
            @RequestParam(value = "contactType", required = false) ContactType contactType,
            @RequestParam(value = "sort", defaultValue = "desc") String sortDir,
            Model model) {

        Sort sort = Sort.by("createdAt");
        sort = "asc".equalsIgnoreCase(sortDir) ? sort.ascending() : sort.descending();

        List<Feedback> items;
        if (contactType != null) {
            items = feedbackRepository.findByContactType(contactType, sort);
        } else {
            items = feedbackRepository.findAll(sort);
        }

        model.addAttribute("items", items);
        model.addAttribute("contactTypes", ContactType.values());
        model.addAttribute("selectedContactType", contactType);
        model.addAttribute("sort", sortDir);
        return "admin_feedback_list";
    }
}
