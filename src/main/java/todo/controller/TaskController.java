package todo.controller;
import todo.manager.TaskManager;
import todo.manager.TaskTypeManager;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TaskController{
	
	private final TaskManager taskManager;
	private final TaskTypeManager typeManager;
	
	public TaskController(TaskManager taskManager, TaskTypeManager typeManager) {
		this.taskManager = taskManager;
		this.typeManager = typeManager;
	}
	
	//タスクの管理------------------------------------------------------------------
	@GetMapping("/")
	public String showMain(Model model) {
		model.addAttribute("tasks", taskManager.getTasks());
		model.addAttribute("types", typeManager.getTaskTypes());
		return "main";
	}
	
	@PostMapping("/add")
	public String makeTask(Model model,
			@RequestParam(value = "title") String title,
			@RequestParam(value = "typeId") int typeId,
			@RequestParam(name = "deadline", required = false)	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	LocalDateTime deadline,
			@RequestParam(name = "memo", required = false) String memo) {
		
		if(title.isBlank()) {
			model.addAttribute("errorMessage", "空文字は使用できません。");
			return showMain(model);
		}else {
			taskManager.makeTask(title, typeManager.getType(typeId), deadline, memo);
			return "redirect:/";
		}
	}
	
	@PostMapping("/complete")
	public String completeTask(@RequestParam(value = "id") int id) {
		taskManager.completeTask(id);
		return "redirect:/";
	}
	
	@PostMapping("/edit")
	public String editTask(Model model,
			@RequestParam(value = "id") int id, 
			@RequestParam(value = "title") String title,
			@RequestParam(value = "typeId") int typeId,
			@RequestParam(name = "deadline", required = false)	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	LocalDateTime deadline,
			@RequestParam(name = "memo", required = false) String memo) {
		
		if(title.isBlank()) {
			model.addAttribute("errorMessage", "空文字は使用できません。");
			return showMain(model);
		}else {
			taskManager.editTask(id, title,typeManager.getType(typeId), deadline, memo);
			return "redirect:/";
		}	
	}
	
	@PostMapping("/delete")
	public String deleteTask(@RequestParam(value = "id") int id) {
		taskManager.deleteTask(id);
		return "redirect:/";
	}
}
