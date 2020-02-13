package me.zhengjie.modules.employee.service;

import me.zhengjie.modules.employee.domain.EmployeeInfo;
import me.zhengjie.modules.employee.service.dto.EmployeeInfoDto;
import me.zhengjie.modules.employee.service.dto.EmployeeInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author vigarbuaa
* @date 2020-02-13
*/
public interface EmployeeInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(EmployeeInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<EmployeeInfoDto>
    */
    List<EmployeeInfoDto> queryAll(EmployeeInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return EmployeeInfoDto
     */
    EmployeeInfoDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return EmployeeInfoDto
    */
    EmployeeInfoDto create(EmployeeInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(EmployeeInfo resources);

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
    void download(List<EmployeeInfoDto> all, HttpServletResponse response) throws IOException;
}