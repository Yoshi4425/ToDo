package todo.controller;
import todo.entity.Account;
import todo.manager.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TaskTypeController{
	
	private final TaskManager taskManager;
	private final TaskTypeManager typeManager;
	private final AccountManager accountManager;
	
	public TaskTypeController(TaskManager taskManager, TaskTypeManager typeManager, AccountManager accountManager) {
		this.taskManager = taskManager;
		this.typeManager = typeManager;
		this.accountManager = accountManager;
	}
	
	//種別の管理------------------------------------------------------------------
	@GetMapping("/setType")
	public String showTaskType(Model model, Authentication authentication) {
		model.addAttribute("username", getUser(authentication).getUsername());
		model.addAttribute("types", typeManager.getTaskTypes(getUser(authentication)));
		return "type";
	}
	
	@PostMapping("/addType")
	public String addType(Model model, Authentication authentication, @RequestParam(value = "name") String name) {
		if (name.isBlank()) {
			model.addAttribute("errorMessage", "空文字は使用できません。");
			return showTaskType(model, authentication);
		}else {
			typeManager.makeType(getUser(authentication), name);
			return "redirect:/setType";
		}
	}
	
	@PostMapping("/deleteType")
	public String deleteType(Model model, Authentication authentication, @RequestParam(value = "id") int id) {
		if (taskManager.checkUsedTaskType(getUser(authentication), id)) {
			model.addAttribute("errorMessage", "この種別はタスクで使用されているため削除できません。");
			return showTaskType(model, authentication);
		}else {
			typeManager.deleteType(getUser(authentication), id);
			return "redirect:/setType";
		}
	}	
	@PostMapping("/editType")
	public String editType(Authentication authentication,
			@RequestParam(value = "id") int id, @RequestParam(value = "name") String name) {
			typeManager.editType(getUser(authentication), id, name);
		return "redirect:/setType";
	}
	
	private Account getUser(Authentication authentication) {
		return accountManager.getAccount(authentication.getName());
	}
}
