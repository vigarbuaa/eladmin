package me.zhengjie.modules.todo.repository;

import me.zhengjie.modules.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
* @author vigarbuaa
* @date 2020-02-19
*/
public interface TodoRepository extends JpaRepository<Todo, Integer>, JpaSpecificationExecutor<Todo> {
    /**
     * 修改completed状态
     * @param id 
     * @param pass completed
     */
	@Transactional
    @Modifying
    @Query(value = "update todo set completed = ?1  where id = ?2",nativeQuery = true)
    void updateState(Integer completed , Integer id);
}