package me.zhengjie.modules.supplier.repository;

import me.zhengjie.modules.supplier.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author vigarbuaa
* @date 2020-02-17
*/
public interface SupplierRepository extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {
}