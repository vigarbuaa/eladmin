package me.zhengjie.modules.contract.repository;

import me.zhengjie.modules.contract.domain.InterconnectContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author vigarbuaa
* @date 2020-02-16
*/
public interface InterconnectContractRepository extends JpaRepository<InterconnectContract, Long>, JpaSpecificationExecutor<InterconnectContract> {
}