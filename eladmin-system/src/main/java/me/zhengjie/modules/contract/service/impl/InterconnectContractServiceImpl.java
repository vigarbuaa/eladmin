package me.zhengjie.modules.contract.service.impl;

import me.zhengjie.modules.contract.domain.InterconnectContract;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.contract.repository.InterconnectContractRepository;
import me.zhengjie.modules.contract.service.InterconnectContractService;
import me.zhengjie.modules.contract.service.dto.InterconnectContractDto;
import me.zhengjie.modules.contract.service.dto.InterconnectContractQueryCriteria;
import me.zhengjie.modules.contract.service.mapper.InterconnectContractMapper;
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
* @date 2020-02-16
*/
@Service
//@CacheConfig(cacheNames = "interconnectContract")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class InterconnectContractServiceImpl implements InterconnectContractService {

    private final InterconnectContractRepository interconnectContractRepository;

    private final InterconnectContractMapper interconnectContractMapper;

    public InterconnectContractServiceImpl(InterconnectContractRepository interconnectContractRepository, InterconnectContractMapper interconnectContractMapper) {
        this.interconnectContractRepository = interconnectContractRepository;
        this.interconnectContractMapper = interconnectContractMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(InterconnectContractQueryCriteria criteria, Pageable pageable){
        Page<InterconnectContract> page = interconnectContractRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(interconnectContractMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<InterconnectContractDto> queryAll(InterconnectContractQueryCriteria criteria){
        return interconnectContractMapper.toDto(interconnectContractRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public InterconnectContractDto findById(Long id) {
        InterconnectContract interconnectContract = interconnectContractRepository.findById(id).orElseGet(InterconnectContract::new);
        ValidationUtil.isNull(interconnectContract.getId(),"InterconnectContract","id",id);
        return interconnectContractMapper.toDto(interconnectContract);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public InterconnectContractDto create(InterconnectContract resources) {
        return interconnectContractMapper.toDto(interconnectContractRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(InterconnectContract resources) {
        InterconnectContract interconnectContract = interconnectContractRepository.findById(resources.getId()).orElseGet(InterconnectContract::new);
        ValidationUtil.isNull( interconnectContract.getId(),"InterconnectContract","id",resources.getId());
        interconnectContract.copy(resources);
        interconnectContractRepository.save(interconnectContract);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            interconnectContractRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<InterconnectContractDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (InterconnectContractDto interconnectContract : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("申请人", interconnectContract.getProposer());
            map.put("标的物类型", interconnectContract.getSubjectType());
            map.put("标的物", interconnectContract.getSubject());
            map.put("数量", interconnectContract.getNumber());
            map.put("金额", interconnectContract.getMoney());
            map.put("供应商", interconnectContract.getSupplier());
            map.put("合同日期", interconnectContract.getContractDate());
            map.put("预算号", interconnectContract.getBudget());
            map.put(" remarks",  interconnectContract.getRemarks());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}