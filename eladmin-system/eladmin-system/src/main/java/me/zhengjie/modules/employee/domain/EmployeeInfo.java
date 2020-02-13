package me.zhengjie.modules.employee.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author vigarbuaa
* @date 2020-02-13
*/
@Entity
@Data
@Table(name="employee_info")
public class EmployeeInfo implements Serializable {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 手机号码 */
    @Column(name = "phone")
    private String phone;

    /** 身份证号 */
    @Column(name = "id_card")
    private String idCard;

    /** 位址 */
    @Column(name = "location")
    private String location;

    /** 创建日期 */
    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    /** 性别 */
    @Column(name = "sex")
    private String sex;

    @Column(name = "name")
    private String name;

    /** 公司 */
    @Column(name = "company")
    private String company;

    /** 岗位名称 */
    @Column(name = "zhiwei")
    private String zhiwei;

    /** 备注 */
    @Column(name = "remarks")
    private String remarks;

    /** 家乡 */
    @Column(name = "hometown")
    private String hometown;

    /** 住址 */
    @Column(name = "address")
    private String address;

    public void copy(EmployeeInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}