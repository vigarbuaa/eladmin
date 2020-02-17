package me.zhengjie.modules.supplier.service.impl;

import me.zhengjie.modules.supplier.domain.Supplier;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.supplier.repository.SupplierRepository;
import me.zhengjie.modules.supplier.service.SupplierService;
import me.zhengjie.modules.supplier.service.dto.SupplierDto;
import me.zhengjie.modules.supplier.service.dto.SupplierQueryCriteria;
import me.zhengjie.modules.supplier.service.mapper.SupplierMapper;
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
* @date 2020-02-17
*/
@Service
//@CacheConfig(cacheNames = "supplier")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    //@Cacheable
    public Map<String,Object> queryAll(SupplierQueryCriteria criteria, Pageable pageable){
        Page<Supplier> page = supplierRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(supplierMapper::toDto));
    }

    @Override
    //@Cacheable
    public List<SupplierDto> queryAll(SupplierQueryCriteria criteria){
        return supplierMapper.toDto(supplierRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    //@Cacheable(key = "#p0")
    public SupplierDto findById(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseGet(Supplier::new);
        ValidationUtil.isNull(supplier.getId(),"Supplier","id",id);
        return supplierMapper.toDto(supplier);
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public SupplierDto create(Supplier resources) {
        return supplierMapper.toDto(supplierRepository.save(resources));
    }

    @Override
    //@CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void update(Supplier resources) {
        Supplier supplier = supplierRepository.findById(resources.getId()).orElseGet(Supplier::new);
        ValidationUtil.isNull( supplier.getId(),"Supplier","id",resources.getId());
        supplier.copy(resources);
        supplierRepository.save(supplier);
    }

    @Override
    //@CacheEvict(allEntries = true)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            supplierRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<SupplierDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (SupplierDto supplier : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" buscode",  supplier.getBuscode());
            map.put("公司名称", supplier.getCompanyname());
            map.put("名称缩写", supplier.getShortname());
            map.put("成立日期", supplier.getFounddate());
            map.put("企业类型", supplier.getOrgtype());
            map.put("法人", supplier.getChairman());
            map.put("注册资本", supplier.getRegcapital());
            map.put("负责人", supplier.getManager());
            map.put("注册地", supplier.getRegaddr());
            map.put("办公地", supplier.getOfficeaddr());
            map.put("邮编", supplier.getOfficezipcode());
            map.put("公司电话", supplier.getComptel());
            map.put("公司简介", supplier.getCompintro());
            map.put("业务范围", supplier.getBizscope());
            map.put("省份", supplier.getProvinceName());
            map.put("城市名称", supplier.getCityName());
            map.put("入档日期", supplier.getApplyDate());
            map.put("申请人", supplier.getProposer());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}