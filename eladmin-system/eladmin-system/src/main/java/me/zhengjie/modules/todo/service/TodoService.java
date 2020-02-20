package me.zhengjie.modules.todo.service;

import me.zhengjie.modules.todo.domain.Todo;
import me.zhengjie.modules.todo.service.dto.TodoDto;
import me.zhengjie.modules.todo.service.dto.TodoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author vigarbuaa
* @date 2020-02-19
*/
public interface TodoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(TodoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<TodoDto>
    */
    List<TodoDto> queryAll(TodoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return TodoDto
     */
    TodoDto findById(Integer id);

    /**
    * 创建
    * @param resources /
    * @return TodoDto
    */
    TodoDto create(Todo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Todo resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Integer[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<TodoDto> all, HttpServletResponse response) throws IOException;
}