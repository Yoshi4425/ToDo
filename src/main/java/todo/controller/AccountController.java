package todo.controller;
import todo.manager.AccountManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//アカウントのIO制御
@Controller
public class AccountController{
	
	//アカウント管理のインスタンス
	private final AccountManager accountManager;
	
	//accountManagerはSpringが生成
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	//ユーザー登録画面を表示
	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	//ログイン画面を表示
	@GetMapping("/login")
	public String showLogin() {
	    return "login";
	}
	
	//ユーザー登録リクエスト
	@PostMapping("/register")
	public String register( Model model,
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		
		//空白であればエラー
		if (username.isBlank() || password.isBlank()) {
            model.addAttribute("errorMessage", "ユーザー名とパスワードを入力してください。");
            return "register";
        }
		
		//ユーザー名が既に使用されていればエラー
        if (accountManager.checkExistUsername(username)) {
            model.addAttribute("errorMessage", "このユーザー名は既に使用されています。");
            return "register";
        }

        //問題がなければアカウント作成
        accountManager.makeAccount(username, password);
        return "redirect:/login?registered";
	}
}
