package me.zhengjie.modules.supplier.service;

import me.zhengjie.modules.supplier.domain.Supplier;
import me.zhengjie.modules.supplier.service.dto.SupplierDto;
import me.zhengjie.modules.supplier.service.dto.SupplierQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author vigarbuaa
* @date 2020-02-17
*/
public interface SupplierService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(SupplierQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<SupplierDto>
    */
    List<SupplierDto> queryAll(SupplierQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return SupplierDto
     */
    SupplierDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return SupplierDto
    */
    SupplierDto create(Supplier resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Supplier resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<SupplierDto> all, HttpServletResponse response) throws IOException;
}