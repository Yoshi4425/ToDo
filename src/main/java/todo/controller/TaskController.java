package todo.controller;
import todo.manager.*;
import todo.entity.*;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

@Controller
public class TaskController{
	
	private final TaskManager taskManager;
	private final TaskTypeManager typeManager;
	private final AccountManager accountManager;
	
	public TaskController(TaskManager taskManager, TaskTypeManager typeManager, AccountManager accountManager) {
		this.taskManager = taskManager;
		this.typeManager = typeManager;
		this.accountManager = accountManager;
	}
	
	//タスクの管理------------------------------------------------------------------
	@GetMapping("/")
	public String showMain(Model model, Authentication authentication) {
		model.addAttribute("username", getUser(authentication).getUsername());
		model.addAttribute("tasks", taskManager.getTasks(getUser(authentication)));
		model.addAttribute("types", typeManager.getTaskTypes(getUser(authentication)));
		return "main";
	}
	
	@PostMapping("/add")
	public String makeTask(Model model, Authentication authentication,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "typeId") int typeId,
			@RequestParam(name = "deadline", required = false)	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	LocalDateTime deadline,
			@RequestParam(name = "memo", required = false) String memo) {
		
		if(title.isBlank()) {
			model.addAttribute("errorMessage", "空文字は使用できません。");
			return showMain(model, authentication);
		}else {
			TaskType type = typeManager.getType(getUser(authentication), typeId);
			if(type == null) {
				model.addAttribute("errorMessage", "種別にエラーがあります。");
				return showMain(model, authentication);
			}
			taskManager.makeTask(getUser(authentication), title, type, deadline, memo);
			return "redirect:/";
		}
	}
	
	@PostMapping("/complete")
	public String completeTask(Authentication authentication, @RequestParam(value = "id") int id) {
		taskManager.completeTask(getUser(authentication), id);
		return "redirect:/";
	}
	
	@PostMapping("/edit")
	public String editTask(Model model, Authentication authentication,
			@RequestParam(value = "id") int id, 
			@RequestParam(value = "title") String title,
			@RequestParam(value = "typeId") int typeId,
			@RequestParam(name = "deadline", required = false)	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	LocalDateTime deadline,
			@RequestParam(name = "memo", required = false) String memo) {

		if(title.isBlank()) {
			model.addAttribute("errorMessage", "空文字は使用できません。");
			return showMain(model, authentication);
		}else {
			TaskType type = typeManager.getType(getUser(authentication), typeId);
			if(type == null) {
				model.addAttribute("errorMessage", "種別にエラーがあります。");
				return showMain(model, authentication);
			}
			taskManager.editTask(getUser(authentication), id, title, type, deadline, memo);
			return "redirect:/";
		}	
	}
	
	@PostMapping("/delete")
	public String deleteTask(Authentication authentication, @RequestParam(value = "id") int id) {
		taskManager.deleteTask(getUser(authentication), id);
		return "redirect:/";
	}
	
	private Account getUser(Authentication authentication) {
		return accountManager.getAccount(authentication.getName());
	}
}
