package eu.europa.ec.env.dsc.envexcercise.web;

import eu.europa.ec.env.dsc.envexcercise.feedback.ContactType;
import eu.europa.ec.env.dsc.envexcercise.feedback.Feedback;
import eu.europa.ec.env.dsc.envexcercise.feedback.FeedbackRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @ModelAttribute("contactTypes")
    public ContactType[] contactTypes() {
        return ContactType.values();
    }

    @GetMapping({"/", "/feedback"})
    public String showForm(Model model) {
        if (!model.containsAttribute("feedback")) {
            model.addAttribute("feedback", new Feedback());
        }
        return "feedback_form";
    }

    @PostMapping("/feedback")
    public String submitForm(@Valid @ModelAttribute("feedback") Feedback feedback, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "feedback_form";
        }
        feedbackRepository.save(feedback);
        model.addAttribute("submittedId", feedback.getId());
        return "feedback_success";
    }
}
