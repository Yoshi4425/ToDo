package todo.controller;
import todo.entity.Account;
import todo.manager.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

//種別関連のIO制御
@Controller
public class TaskTypeController{
	
	//各データ管理のインスタンス
	private final TaskManager taskManager;
	private final TaskTypeManager typeManager;
	private final AccountManager accountManager;
	
	//各ManagerはSpringが生成
	public TaskTypeController(TaskManager taskManager, TaskTypeManager typeManager, AccountManager accountManager) {
		this.taskManager = taskManager;
		this.typeManager = typeManager;
		this.accountManager = accountManager;
	}
	
	//種別の管理画面を表示
	@GetMapping("/setType")
	public String showTaskType(Model model, Authentication authentication) {
		model.addAttribute("username", getUser(authentication).getUsername());
		model.addAttribute("types", typeManager.getTaskTypes(getUser(authentication)));
		return "type";
	}
	
	//種別追加のリクエスト
	@PostMapping("/addType")
	public String addType(Model model, Authentication authentication, @RequestParam(value = "name") String name) {
		
		//空白ならエラーを付与して画面表示
		if (name.isBlank()) {
			model.addAttribute("errorMessage", "空文字は使用できません。");
			return showTaskType(model, authentication);
		}else {
			//問題なければ種別作成
			typeManager.makeType(getUser(authentication), name);
			return "redirect:/setType";
		}
	}
	
	//種別削除のリクエスト
	@PostMapping("/deleteType")
	public String deleteType(Model model, Authentication authentication, @RequestParam(value = "id") int id) {
		
		//対象の種別が使用されていた場合削除できない
		if (taskManager.checkUsedTaskType(getUser(authentication), id)) {
			model.addAttribute("errorMessage", "この種別はタスクで使用されているため削除できません。");
			return showTaskType(model, authentication);
		}else {
			//問題なければ種別削除
			typeManager.deleteType(getUser(authentication), id);
			return "redirect:/setType";
		}
	}	
	
	//種別の編集リクエスト
	@PostMapping("/editType")
	public String editType(Authentication authentication,
			@RequestParam(value = "id") int id, @RequestParam(value = "name") String name) {
			typeManager.editType(getUser(authentication), id, name);
		return "redirect:/setType";
	}
	
	//アカウント名取得の記述簡略化
	private Account getUser(Authentication authentication) {
		return accountManager.getAccount(authentication.getName());
	}
}
