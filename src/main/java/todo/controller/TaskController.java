package todo.controller;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import todo.main.*;

@Controller
public class TaskController{
	
	private final Manager manager;
	
	public TaskController(Manager mng) {
		manager = mng;
	}
	
	//タスクの管理------------------------------------------------------------------
	@GetMapping("/")
	public String showMain(Model model) {
		model.addAttribute("tasks", manager.getTasks());
		model.addAttribute("types", manager.getTaskTypes());
		return "main";
	}
	
	@PostMapping("/add")
	public String makeTask(@RequestParam(value = "title", defaultValue = "none") String title,
			@RequestParam(value = "typeId") int typeId,
			@RequestParam(name = "deadline", required = false)	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	LocalDateTime deadline,
			@RequestParam(name = "memo", required = false) String memo) {
		if(! title.equals("none")) {
			manager.makeTask(title, typeId, deadline, memo);
		}
		return "redirect:/";
	}
	
	@PostMapping("/complete")
	public String completeTask(@RequestParam(value = "id") int id) {
		manager.completeTask(id);
		return "redirect:/";
	}
	
	@PostMapping("/edit")
	public String editTask(@RequestParam(value = "id") int id, 
			@RequestParam(value = "title") String title,
			@RequestParam(value = "typeId") int typeId,
			@RequestParam(name = "deadline", required = false)	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")	LocalDateTime deadline,
			@RequestParam(name = "memo", required = false) String memo) {
		manager.editTask(id, title,typeId, deadline, memo);
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	public String deleteTask(@RequestParam(value = "id") int id) {
		manager.deleteTask(id);
		return "redirect:/";
	}
	
	//種別の管理------------------------------------------------------------------
	@GetMapping("/setType")
	public String showTaskType(Model model) {
		model.addAttribute("types", manager.getTaskTypes());
		return "type";
	}
	
	@PostMapping("/addType")
	public String addType(@RequestParam(value = "name") String name) {
		manager.makeType(name);
		return "redirect:/setType";
	}
	
	@PostMapping("/deleteType")
	public String deleteType(Model model, @RequestParam(value = "id") int id) {
		boolean deleted = manager.deleteType(id);
		if (!deleted) {
	        model.addAttribute("errorMessage", "この種別はタスクで使用されているため削除できません。");
	    }
		model.addAttribute("types", manager.getTaskTypes());
		return "type";
	}
	
	@PostMapping("/editType")
	public String editType(@RequestParam(value = "id") int id, @RequestParam(value = "name") String name) {
			manager.editType(id, name);
		return "redirect:/setType";
	}
}
