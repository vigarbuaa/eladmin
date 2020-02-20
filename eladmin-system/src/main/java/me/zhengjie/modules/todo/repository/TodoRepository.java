package me.zhengjie.modules.todo.repository;

import me.zhengjie.modules.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author vigarbuaa
* @date 2020-02-19
*/
public interface TodoRepository extends JpaRepository<Todo, Integer>, JpaSpecificationExecutor<Todo> {
}