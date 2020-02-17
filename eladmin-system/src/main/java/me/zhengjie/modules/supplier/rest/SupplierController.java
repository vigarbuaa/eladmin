package me.zhengjie.modules.supplier.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.supplier.domain.Supplier;
import me.zhengjie.modules.supplier.service.SupplierService;
import me.zhengjie.modules.supplier.service.dto.SupplierQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @author vigarbuaa
* @date 2020-02-17
*/
@Api(tags = "供方单位详细信息管理")
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('supplier:list')")
    public void download(HttpServletResponse response, SupplierQueryCriteria criteria) throws IOException {
        supplierService.download(supplierService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询供方单位详细信息")
    @ApiOperation("查询供方单位详细信息")
    @PreAuthorize("@el.check('supplier:list')")
    public ResponseEntity<Object> getSuppliers(SupplierQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(supplierService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增供方单位详细信息")
    @ApiOperation("新增供方单位详细信息")
    @PreAuthorize("@el.check('supplier:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Supplier resources){
        return new ResponseEntity<>(supplierService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改供方单位详细信息")
    @ApiOperation("修改供方单位详细信息")
    @PreAuthorize("@el.check('supplier:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Supplier resources){
        supplierService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除供方单位详细信息")
    @ApiOperation("删除供方单位详细信息")
    @PreAuthorize("@el.check('supplier:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        supplierService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}