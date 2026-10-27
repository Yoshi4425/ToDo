package todo.controller;
import todo.manager.TaskManager;
import todo.manager.TaskTypeManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class TaskTypeController{
	
	private final TaskManager taskManager;
	private final TaskTypeManager typeManager;
	
	public TaskTypeController(TaskManager taskManager, TaskTypeManager typeManager) {
		this.taskManager = taskManager;
		this.typeManager = typeManager;
	}
	
	//種別の管理------------------------------------------------------------------
	@GetMapping("/setType")
	public String showTaskType(Model model) {
		model.addAttribute("types", typeManager.getTaskTypes());
		return "type";
	}
	
	@PostMapping("/addType")
	public String addType(Model model, @RequestParam(value = "name") String name) {
		if (name.isBlank()) {
			model.addAttribute("errorMessage", "空文字は使用できません。");
			return showTaskType(model);
		}else {
			typeManager.makeType(name);
			return "redirect:/setType";
		}
	}
	
	@PostMapping("/deleteType")
	public String deleteType(Model model, @RequestParam(value = "id") int id) {
		if (taskManager.checkUsedTaskType(id)) {
			model.addAttribute("errorMessage", "この種別はタスクで使用されているため削除できません。");
			return showTaskType(model);
		}else {
			typeManager.deleteType(id);
			return "redirect:/setType";
		}
	}	
	@PostMapping("/editType")
	public String editType(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name) {
			typeManager.editType(id, name);
		return "redirect:/setType";
	}
}
