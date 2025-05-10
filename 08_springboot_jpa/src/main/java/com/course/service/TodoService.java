package com.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.course.dto.TodoDto;
import com.course.entity.TodoEntity;
import com.course.repository.TodoRepository;

import jakarta.transaction.Transactional;

@Service
public class TodoService {

	@Autowired
	TodoRepository todoRepository;

	// 查詢全部
	public List<TodoEntity> getAllTodo() {
		List<TodoEntity> list = todoRepository.findAll();
		return list;
	}
	
	// 新增單筆
	public TodoEntity addTodo(TodoEntity entity) {
		TodoEntity todo = todoRepository.save(entity);
		return todo;
	}
	
	// 刪除全部(一筆一筆)
	public void deleteAllTodo() {
		todoRepository.deleteAll();
	}
	
	// 刪除全部(一次性)
	public void deleteAllInBatch() {
		todoRepository.deleteAllInBatch();
	}
	
	// 更新單筆(條件:id 資料:title)
	public TodoEntity updateTodo(TodoEntity entity) {
		TodoEntity todo = null;
		
		Long id = entity.getId();
//		Optional<TodoEntity> optional = todoRepository.findById(id);
//		
//		if(optional.isPresent()) {
//			todo = optional.get();
//			todo.setTitle(entity.getTitle());
//			todoRepository.save(todo);
//		}
		
		todo = todoRepository.findById(id).orElse(null);
		todo.setTitle(entity.getTitle());
		todoRepository.save(todo);
		
		return todo;
	}
	
	// 查詢全部(篩選:Title)
	public List<TodoEntity> getByTitle(String title) {
		List<TodoEntity> titleList = todoRepository.findByTitle(title);
		return titleList;
	}
	
	// 查詢單筆(根據Title和Status)
	public List<TodoEntity> getByTitleUnCompleted(String title, Integer status) {
		List<TodoEntity> listUnCompleted = todoRepository.findByTitleAndStatus(title, status);
		return listUnCompleted;
	}
	
	// 查詢全部(篩選:大於到期日dueDate)
	public List<TodoEntity> getByDueDateGT(Date dueDate){
		System.out.println(dueDate);
		
		List<TodoEntity> list = todoRepository.findByDueDateGreaterThan(dueDate);
		return list;
	}
	
	// 
	
	
	
	
	
	
	
	
	

	// 查詢單筆(根據Title和Status，使用 @Query 查詢)
	public List<TodoEntity> findByCondition(String title, Integer status) {
		List<TodoEntity> list = todoRepository.findByCondition(title, status);
		return list;
	}
	
	// 更新單筆(條件:id 資料:title，使用 @Query 更新)
	@Transactional
	public Integer updateQuery(Long id, String title) {
		Integer result = todoRepository.updateTodo(id, title);
		return result;
	}
	
	// 分頁
	public Page<TodoEntity> getAllWithPage(Integer pageNum, Integer size) {
		Pageable pageable = PageRequest.of(pageNum, size);
		return todoRepository.findAll(pageable);
	}
	
	public List<TodoDto> findUser() {
		List<TodoDto> dtoList = todoRepository.getTodoDtoList();
		return dtoList;
	}
	
}
