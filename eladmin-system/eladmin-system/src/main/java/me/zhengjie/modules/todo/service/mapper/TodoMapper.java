package me.zhengjie.modules.todo.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.todo.domain.Todo;
import me.zhengjie.modules.todo.service.dto.TodoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author vigarbuaa
* @date 2020-02-19
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper extends BaseMapper<TodoDto, Todo> {

}