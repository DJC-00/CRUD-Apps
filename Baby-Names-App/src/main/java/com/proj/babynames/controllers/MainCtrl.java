package com.proj.babynames.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.proj.babynames.models.Baby;
import com.proj.babynames.models.LoginUser;
import com.proj.babynames.models.User;
import com.proj.babynames.models.Vote;
import com.proj.babynames.services.BabyService;
import com.proj.babynames.services.UserService;
import com.proj.babynames.services.VoteService;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class MainCtrl {

    // Injecting out VentureService
    private final BabyService babyServ;
    private final VoteService voteServ;
    private final UserService userServ;

    public MainCtrl(VoteService teamServ, BabyService ventureServ, UserService userServ) {
        super();
        this.voteServ = teamServ;
        this.babyServ = ventureServ;
        this.userServ = userServ;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "login.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
            BindingResult result, Model model, HttpSession session) {

        userServ.register(newUser, result);
        session.removeAttribute("sessionError");

        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "login.jsp";
        }

        session.setAttribute("user_id", newUser.getId());

        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
            BindingResult result, Model model, HttpSession session) {

        User user = userServ.login(newLogin, result);
        session.removeAttribute("sessionError");
        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "login.jsp";
        }

        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/success")
    public String success(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }
        model.addAttribute("currentUser", userServ.findUserById(userId));
        return "dashboard.jsp";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("user_id");

        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }
        model.addAttribute("currentUser", userServ.findUserById(userId));
        model.addAttribute("allBabies", babyServ.allBabies());
        model.addAttribute("allVotes", voteServ.allVotes());
        model.addAttribute("userVotes", voteServ.userVotes(userId));

        return "dashboard.jsp";
    }

    @GetMapping("/babyName/new")
    public String newBabyForm(@ModelAttribute("baby") Baby baby, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        session.removeAttribute("babyDuplicate");
        if (userId == null) {

            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }
        model.addAttribute("allTeams", voteServ.allVotes());
        return "newBabyName.jsp";
    }

    @PostMapping("/processBabyName")
    public String postingBaby(@Valid @ModelAttribute("baby") Baby baby, BindingResult result, Model model,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");

        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }
        try {
            if (result.hasErrors()) {
                return "newBabyName.jsp";
            }

            else {
                baby.setUser(userServ.findUserById(userId));
                babyServ.createBaby(baby);
                return "redirect:/dashboard";
            }
        } catch (Exception e) {
            session.setAttribute("babyDuplicate", "That Baby Name Already Exists");
            return "newBabyName.jsp";
        }
    }

    @GetMapping("/oneBabyName/{id}")
    public String oneBabyName(@PathVariable("id") Long id, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }
        model.addAttribute("baby", babyServ.findBaby(id));
        model.addAttribute("votes", voteServ.babyVotes(id));
        model.addAttribute("userVotes", voteServ.userVotes(userId));

        return "oneBabyName.jsp";
    }

    @GetMapping("/oneBabyName/{id}/edit")
    public String updateBabyForm(@PathVariable("id") Long id, @ModelAttribute("baby") Baby baby, Model model,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }

        model.addAttribute("baby", babyServ.findBaby(id));
        return "updateBaby.jsp";
    }

    @PutMapping(value = "/oneBabyName/{id}")
    public String updateBabyConfirm(@Valid @ModelAttribute("baby") Baby baby, BindingResult result,
            HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "updateBaby.jsp";

        } else {
            baby.setUser(userServ.findUserById((Long) session.getAttribute("user_id")));
            babyServ.updateBaby(baby);
            return "redirect:/oneBabyName/{id}";

        }
    }

    @GetMapping("/babyName/delete/{id}")
    public String deleteBaby(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }

        babyServ.deleteBaby(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/changeVote/{id}")
    public String changeVote(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }
        Vote newVote = new Vote();

        newVote.setBaby(babyServ.findBaby(id));
        newVote.setUser(userServ.findUserById((Long) session.getAttribute("user_id")));

        if (voteServ.voteCheck(newVote.getUser().getId(), newVote.getBaby().getId())) {
            voteServ.createVote(newVote);
        } else if (!voteServ.voteCheck(newVote.getUser().getId(), newVote.getBaby().getId())) {
            voteServ.removeVote(newVote.getUser().getId(), newVote.getBaby().getId());
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/changeVoteFromBaby/{id}")
    public String changeVoteFromBaby(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("user_id");
        if (userId == null) {
            session.setAttribute("sessionError", "Must log in before entering site");
            return "redirect:/";
        }
        Vote newVote = new Vote();

        newVote.setBaby(babyServ.findBaby(id));
        newVote.setUser(userServ.findUserById((Long) session.getAttribute("user_id")));

        if (voteServ.voteCheck(newVote.getUser().getId(), newVote.getBaby().getId())) {
            voteServ.createVote(newVote);
        } else if (!voteServ.voteCheck(newVote.getUser().getId(), newVote.getBaby().getId())) {
            voteServ.removeVote(newVote.getUser().getId(), newVote.getBaby().getId());
        }
        return "redirect:/oneBabyName/{id}";
    }
}