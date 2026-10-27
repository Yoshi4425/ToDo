package todo.controller;
import todo.manager.AccountManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class AccountController{
	
	private final AccountManager accountManager;
	
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	//アカウントの管理------------------------------------------------------------------
	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register( Model model,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		
		if (username.isBlank() || password.isBlank()) {
            model.addAttribute("errorMessage", "ユーザー名とパスワードを入力してください。");
            return "register";
        }

        if (accountManager.checkExistUsername(username)) {
            model.addAttribute("errorMessage", "このユーザー名は既に使用されています。");
            return "register";
        }

        accountManager.makeAccount(username, password);
        return "redirect:/login?registered";
	}
	
	@GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}
