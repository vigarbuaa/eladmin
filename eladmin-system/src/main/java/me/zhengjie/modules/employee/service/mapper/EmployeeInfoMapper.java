package me.zhengjie.modules.employee.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.employee.domain.EmployeeInfo;
import me.zhengjie.modules.employee.service.dto.EmployeeInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author vigarbuaa
* @date 2020-02-13
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfoDto, EmployeeInfo> {

}