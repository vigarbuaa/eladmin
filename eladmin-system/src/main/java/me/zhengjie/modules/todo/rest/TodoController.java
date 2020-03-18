package me.zhengjie.modules.todo.rest;

import me.zhengjie.annotation.AnonymousAccess;
import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.todo.domain.CompletedVo;
import me.zhengjie.modules.todo.domain.Todo;
import me.zhengjie.modules.todo.service.TodoService;
import me.zhengjie.modules.todo.service.dto.TodoQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author vigarbuaa
* @date 2020-02-19
*/
@Api(tags = "待办事宜管理")
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('todo:list')")
    public void download(HttpServletResponse response, TodoQueryCriteria criteria) throws IOException {
        todoService.download(todoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询待办事宜")
    @ApiOperation("查询待办事宜")
    @PreAuthorize("@el.check('todo:list')")
    public ResponseEntity<Object> getTodos(TodoQueryCriteria criteria, Pageable pageable){
//        return new ResponseEntity<>(todoService.queryAll(criteria,pageable),HttpStatus.OK);
      return new ResponseEntity<>(todoService.queryAll(criteria),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增待办事宜")
    @ApiOperation("新增待办事宜")
    @PreAuthorize("@el.check('todo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Todo resources){
        return new ResponseEntity<>(todoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改待办事宜")
    @ApiOperation("修改待办事宜")
    @PreAuthorize("@el.check('todo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Todo resources){
        todoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除待办事宜")
    @ApiOperation("删除待办事宜")
    @PreAuthorize("@el.check('todo:del')")
    @PostMapping("/del")
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        todoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping("/udpateStatus")
    @Log("更新待办事宜状态")
    @ApiOperation("修改待办事宜")
    @PreAuthorize("@el.check('todo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody CompletedVo vo){
        todoService.updateStatus(vo.getCompleted(), vo.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}