package me.zhengjie.modules.todo.service.impl;

import me.zhengjie.modules.todo.domain.Todo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.todo.repository.TodoRepository;
import me.zhengjie.modules.todo.service.TodoService;
import me.zhengjie.modules.todo.service.dto.TodoDto;
import me.zhengjie.modules.todo.service.dto.TodoQueryCriteria;
import me.zhengjie.modules.todo.service.mapper.TodoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @author vigarbuaa
* @date 2020-02-19
*/
@Service
//@CacheConfig(cacheNames = "todo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(TodoQueryCriteria criteria, Pageable pageable){
        Page<Todo> page = todoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(todoMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<TodoDto> queryAll(TodoQueryCriteria criteria){
        return todoMapper.toDto(todoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public TodoDto findById(Integer id) {
        Todo todo = todoRepository.findById(id).orElseGet(Todo::new);
        ValidationUtil.isNull(todo.getId(),"Todo","id",id);
        return todoMapper.toDto(todo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public TodoDto create(Todo resources) {
        return todoMapper.toDto(todoRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Todo resources) {
        Todo todo = todoRepository.findById(resources.getId()).orElseGet(Todo::new);
        ValidationUtil.isNull( todo.getId(),"Todo","id",resources.getId());
        todo.copy(resources);
        todoRepository.save(todo);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            todoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<TodoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TodoDto todo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("内容", todo.getContent());
            map.put("1为已完成，0为未完成", todo.getCompleted());
            map.put("创建者", todo.getCreateBy());
            map.put("创建时间", todo.getCreateDate());
            map.put("备注信息", todo.getRemarks());
            map.put("1为已删除，0为未删除", todo.getDelFlag());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}