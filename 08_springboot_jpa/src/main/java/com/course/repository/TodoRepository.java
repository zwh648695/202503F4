package com.course.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.course.dto.TodoDto;
import com.course.entity.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

	// SQL -> select * from todo where title = ?
	List<TodoEntity> findByTitle(String title);

	// SQL -> select * from todo where title = ? and status = ?
	List<TodoEntity> findByTitleAndStatus(String title, Integer status);

	// SQL -> select * from todo where due_date > ?
	List<TodoEntity> findByDueDateGreaterThan(Date dueDate);

	// SQL -> select * from todo where due_date between ? and ?
	List<TodoEntity> findByDueDateBetween(Date startDate, Date endDate);

	// SQL -> select * from todo where title like ?
	List<TodoEntity> findByTitleLike(String title);

	// SQL -> select * from todo where id in (?, ?, ?);
	List<TodoEntity> findByIdIn(List<Long> ids);

	// SQL -> select * from todo order by title;
	List<TodoEntity> findAllByOrderByTitle();
	
//	@Query(value = "select t from TodoEntity t where t.title = :title and t.status = :status")
//	@Query(value = "select t from TodoEntity t where t.title = ?1 and t.status = ?2")
	@Query(nativeQuery = true, value = "select * from todo t where t.title = :title and t.status = :status")
	List<TodoEntity> findByCondition(@Param("title") String t1, Integer status);
	
	@Modifying
	@Query("update TodoEntity t set t.title = ?2 where t.id = ?1") // 注意參數的前後順序
	Integer updateTodo(Long id, String title);
	
	public Page<TodoEntity> findAll(Pageable pageable);
	
	@Query("select new com.course.dto.TodoDto(t.title, u.username) from TodoEntity t join UserEntity u on u.id = t.userId")
	List<TodoDto> getTodoDtoList();

}
