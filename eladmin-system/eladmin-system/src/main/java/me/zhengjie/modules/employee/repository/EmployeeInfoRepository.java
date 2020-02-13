package me.zhengjie.modules.employee.repository;

import me.zhengjie.modules.employee.domain.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author vigarbuaa
* @date 2020-02-13
*/
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long>, JpaSpecificationExecutor<EmployeeInfo> {
}