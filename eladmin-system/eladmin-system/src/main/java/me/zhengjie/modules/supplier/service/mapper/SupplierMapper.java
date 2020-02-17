package me.zhengjie.modules.supplier.service.mapper;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.supplier.domain.Supplier;
import me.zhengjie.modules.supplier.service.dto.SupplierDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author vigarbuaa
* @date 2020-02-17
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper extends BaseMapper<SupplierDto, Supplier> {

}