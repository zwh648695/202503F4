package com.course.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.dto.TodoDto;
import com.course.entity.TodoEntity;
import com.course.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@Operation(summary = "查詢 全部 待辦事項")
	@GetMapping("/todos")
	public List<TodoEntity> getAllTodoList() {
		List<TodoEntity> all = todoService.getAllTodo();
		return all;
	} 
	
	@Operation(summary = "新增 單筆 待辦事項")
	@PostMapping("/add")
	public TodoEntity addTodo(@RequestBody TodoEntity entity) { // @RequestBody -> 接收 json 格式資料
		System.out.println(entity);
		
		TodoEntity todo = todoService.addTodo(entity);
		return todo;
	}
	
	@Operation(summary = "刪除 全部 待辦事項，一筆一筆刪")
	@DeleteMapping("/todos")
	public String deleteAllTodo() {
		todoService.deleteAllTodo();
		return "OK";
	}
	
	@Operation(summary = "刪除 全部 待辦事項，一次全部刪")
	@DeleteMapping("/todos/batch")
	public String deleteAllInBatch() {
		todoService.deleteAllInBatch();
		return "OK";
	}
	
	@Operation(summary = "根據 Id 更新 待辦事項的 Title 資料")
	@PatchMapping("/todo") // PatchingMapping -> 更新部分
	public TodoEntity updateTodo(@RequestBody TodoEntity entity) {
		TodoEntity todo = todoService.updateTodo(entity);
		return todo;
	}
	
	@Operation(summary = "根據 Title 查詢 待辦事項")
	@GetMapping("/todo/title/{title}")
	public List<TodoEntity> getByTitle(@PathVariable String title) {
		List<TodoEntity> allTitle = todoService.getByTitle(title);
		return allTitle;
	}
	
	@Operation(summary = "查詢未完成的 待辦事項, 0:未完成, 1:已完成")
	@GetMapping("/todoUnComplete")
	public List<TodoEntity> getUnCompletedTodos(@RequestParam String title, @RequestParam Integer status) {
		List<TodoEntity> list = todoService.getByTitleUnCompleted(title, status);
		return list;
	}
	
	@Operation(summary = "查詢 大於到期日(dueDate)的 待辦事項")
	@GetMapping("/todoGreaterThanDueDate")
	public List<TodoEntity> findByDueDateGreaterThan(Date dueDate) {
		return todoService.getByDueDateGT(dueDate);
	}
	
	
	
	
	
	
	@Operation(summary = "查詢未完成的 待辦事項, 0:未完成, 1:已完成", tags = "@Query")
	@GetMapping("/todo/condition")
	public List<TodoEntity> findByCondition(String title, Integer status) {
		return todoService.findByCondition(title, status);
	}
	
	@Operation(summary = "取得所有商品並分頁", tags = "page")
	@GetMapping("/todo/page/")
	public Page<TodoEntity> getAllWithPage(Integer pageNum, Integer size) {
		return todoService.getAllWithPage(pageNum, size);
	}
	
	@Operation(summary = "取得使用者", tags = "@QueryMethod")
	@GetMapping("/todo/user")
	public List<TodoDto> findUser() {
		return todoService.findUser();
	}

}
