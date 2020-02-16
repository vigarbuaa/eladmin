package me.zhengjie.modules.contract.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.contract.domain.InterconnectContract;
import me.zhengjie.modules.contract.service.dto.InterconnectContractDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author vigarbuaa
* @date 2020-02-16
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InterconnectContractMapper extends BaseMapper<InterconnectContractDto, InterconnectContract> {

}