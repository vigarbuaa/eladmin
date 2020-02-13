package me.zhengjie.modules.employee.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.employee.domain.EmployeeInfo;
import me.zhengjie.modules.employee.service.EmployeeInfoService;
import me.zhengjie.modules.employee.service.dto.EmployeeInfoQueryCriteria;
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
* @date 2020-02-13
*/
@Api(tags = "本部门人员信息管理")
@RestController
@RequestMapping("/api/employeeInfo")
public class EmployeeInfoController {

    private final EmployeeInfoService employeeInfoService;

    public EmployeeInfoController(EmployeeInfoService employeeInfoService) {
        this.employeeInfoService = employeeInfoService;
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('employeeInfo:list')")
    public void download(HttpServletResponse response, EmployeeInfoQueryCriteria criteria) throws IOException {
        employeeInfoService.download(employeeInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询本部门人员信息")
    @ApiOperation("查询本部门人员信息")
    @PreAuthorize("@el.check('employeeInfo:list')")
    public ResponseEntity<Object> getEmployeeInfos(EmployeeInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(employeeInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增本部门人员信息")
    @ApiOperation("新增本部门人员信息")
    @PreAuthorize("@el.check('employeeInfo:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody EmployeeInfo resources){
        return new ResponseEntity<>(employeeInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改本部门人员信息")
    @ApiOperation("修改本部门人员信息")
    @PreAuthorize("@el.check('employeeInfo:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody EmployeeInfo resources){
        employeeInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除本部门人员信息")
    @ApiOperation("删除本部门人员信息")
    @PreAuthorize("@el.check('employeeInfo:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Long[] ids) {
        employeeInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}