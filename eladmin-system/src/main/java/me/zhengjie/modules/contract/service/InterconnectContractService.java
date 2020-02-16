package me.zhengjie.modules.contract.service;

import me.zhengjie.modules.contract.domain.InterconnectContract;
import me.zhengjie.modules.contract.service.dto.InterconnectContractDto;
import me.zhengjie.modules.contract.service.dto.InterconnectContractQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author vigarbuaa
* @date 2020-02-16
*/
public interface InterconnectContractService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String,Object> queryAll(InterconnectContractQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<InterconnectContractDto>
    */
    List<InterconnectContractDto> queryAll(InterconnectContractQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return InterconnectContractDto
     */
    InterconnectContractDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return InterconnectContractDto
    */
    InterconnectContractDto create(InterconnectContract resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(InterconnectContract resources);

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
    void download(List<InterconnectContractDto> all, HttpServletResponse response) throws IOException;
}