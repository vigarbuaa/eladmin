package me.zhengjie.modules.contract.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.contract.domain.InterconnectContract;
import me.zhengjie.modules.contract.service.InterconnectContractService;
import me.zhengjie.modules.contract.service.dto.InterconnectContractQueryCriteria;
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
* @date 2020-02-16
*/
@Api(tags = "物联相关合同管理")
@RestController
@RequestMapping("/api/interconnectContract")
public class InterconnectContractController {

    private final InterconnectContractService interconnectContractService;

    public InterconnectContractController(InterconnectContractService interconnectContractService) {
        this.interconnectContractService = interconnectContractService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('interconnectContract:list')")
    public void download(HttpServletResponse response, InterconnectContractQueryCriteria criteria) throws IOException {
        interconnectContractService.download(interconnectContractService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询物联相关合同")
    @ApiOperation("查询物联相关合同")
    @PreAuthorize("@el.check('interconnectContract:list')")
    public ResponseEntity<Object> getInterconnectContracts(InterconnectContractQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(interconnectContractService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增物联相关合同")
    @ApiOperation("新增物联相关合同")
    @PreAuthorize("@el.check('interconnectContract:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody InterconnectContract resources){
        return new ResponseEntity<>(interconnectContractService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改物联相关合同")
    @ApiOperation("修改物联相关合同")
    @PreAuthorize("@el.check('interconnectContract:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody InterconnectContract resources){
        interconnectContractService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除物联相关合同")
    @ApiOperation("删除物联相关合同")
    @PreAuthorize("@el.check('interconnectContract:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        interconnectContractService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}