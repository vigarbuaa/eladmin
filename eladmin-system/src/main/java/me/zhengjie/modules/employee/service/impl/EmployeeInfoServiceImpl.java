package me.zhengjie.modules.employee.service.impl;

import me.zhengjie.modules.employee.domain.EmployeeInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.modules.employee.repository.EmployeeInfoRepository;
import me.zhengjie.modules.employee.service.EmployeeInfoService;
import me.zhengjie.modules.employee.service.dto.EmployeeInfoDto;
import me.zhengjie.modules.employee.service.dto.EmployeeInfoQueryCriteria;
import me.zhengjie.modules.employee.service.mapper.EmployeeInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.core.util.IdcardUtil;

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
 * @date 2020-02-13
 */
@Service
// @CacheConfig(cacheNames = "employeeInfo")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class EmployeeInfoServiceImpl implements EmployeeInfoService {

	private final EmployeeInfoRepository employeeInfoRepository;

	private final EmployeeInfoMapper employeeInfoMapper;

	public EmployeeInfoServiceImpl(EmployeeInfoRepository employeeInfoRepository,
			EmployeeInfoMapper employeeInfoMapper) {
		this.employeeInfoRepository = employeeInfoRepository;
		this.employeeInfoMapper = employeeInfoMapper;
	}

	@Override
	// @Cacheable
	public Map<String, Object> queryAll(EmployeeInfoQueryCriteria criteria, Pageable pageable) {
		Page<EmployeeInfo> page = employeeInfoRepository.findAll(
				(root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder),
				pageable);
		return PageUtil.toPage(page.map(employeeInfoMapper::toDto));
	}

	@Override
	// @Cacheable
	public List<EmployeeInfoDto> queryAll(EmployeeInfoQueryCriteria criteria) {
		return employeeInfoMapper.toDto(employeeInfoRepository.findAll(
				(root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
	}

	@Override
	// @Cacheable(key = "#p0")
	public EmployeeInfoDto findById(Long id) {
		EmployeeInfo employeeInfo = employeeInfoRepository.findById(id).orElseGet(EmployeeInfo::new);
		ValidationUtil.isNull(employeeInfo.getId(), "EmployeeInfo", "id", id);
		return employeeInfoMapper.toDto(employeeInfo);
	}

	@Override
	// @CacheEvict(allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public EmployeeInfoDto create(EmployeeInfo resources) {
		return employeeInfoMapper.toDto(employeeInfoRepository.save(resources));
	}

	@Override
	// @CacheEvict(allEntries = true)
	@Transactional(rollbackFor = Exception.class)
	public void update(EmployeeInfo resources) {
		EmployeeInfo employeeInfo = employeeInfoRepository.findById(resources.getId()).orElseGet(EmployeeInfo::new);
		ValidationUtil.isNull(employeeInfo.getId(), "EmployeeInfo", "id", resources.getId());
		employeeInfo.copy(resources);
		employeeInfoRepository.save(employeeInfo);
	}

	@Override
	// @CacheEvict(allEntries = true)
	public void deleteAll(Long[] ids) {
		for (Long id : ids) {
			employeeInfoRepository.deleteById(id);
		}
	}

	@Override
	public void download(List<EmployeeInfoDto> all, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = new ArrayList<>();
		// 增加年龄、出生地、生日
		for (EmployeeInfoDto employeeInfo : all) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("年龄", "");
			map.put("生日", "");
			map.put("出生省份", "");

			String id_card = employeeInfo.getIdCard();
			if (null != id_card && !id_card.equalsIgnoreCase("")) {
				if (IdcardUtil.isValidCard(id_card)) {
					int age = IdcardUtil.getAgeByIdCard(id_card);
					String birth = IdcardUtil.getBirthByIdCard(id_card);
					String province = IdcardUtil.getProvinceByIdCard(id_card);
					map.put("年龄", age);
					map.put("生日", birth);
					map.put("出生省份", province);
				} else {
					System.out.println("[error]: invoide idcard" + id_card);
				}
			}

			map.put("邮箱", employeeInfo.getEmail());
			map.put("手机号码", employeeInfo.getPhone());
			map.put("身份证号", employeeInfo.getIdCard());
			map.put("位址", employeeInfo.getLocation());
			map.put("创建日期", employeeInfo.getCreateTime());
			map.put("性别", employeeInfo.getSex());
			map.put("姓名", employeeInfo.getName());
			map.put("公司", employeeInfo.getCompany());
			map.put("岗位名称", employeeInfo.getZhiwei());
			map.put("备注", employeeInfo.getRemarks());
			map.put("家乡", employeeInfo.getHometown());
			list.add(map);
		}
		FileUtil.downloadExcel(list, response);
	}
}