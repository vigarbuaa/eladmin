package me.zhengjie.modules.supplier.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author vigarbuaa
* @date 2020-02-17
*/
@Entity
@Data
@Table(name="supplier")
public class Supplier implements Serializable {

    @Column(name = "buscode")
    private String buscode;

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 公司名称 */
    @Column(name = "companyname")
    private String companyname;

    /** 名称缩写 */
    @Column(name = "shortname")
    private String shortname;

    /** 成立日期 */
    @Column(name = "founddate")
    private Timestamp founddate;

    /** 企业类型 */
    @Column(name = "orgtype")
    private String orgtype;

    /** 法人 */
    @Column(name = "chairman")
    private String chairman;

    /** 注册资本 */
    @Column(name = "regcapital")
    private Double regcapital;

    /** 负责人 */
    @Column(name = "manager")
    private String manager;

    /** 注册地 */
    @Column(name = "regaddr")
    private String regaddr;

    /** 办公地 */
    @Column(name = "officeaddr")
    private String officeaddr;

    /** 邮编 */
    @Column(name = "officezipcode")
    private String officezipcode;

    /** 公司电话 */
    @Column(name = "comptel")
    private String comptel;

    /** 公司简介 */
    @Column(name = "compintro")
    private String compintro;

    /** 业务范围 */
    @Column(name = "bizscope")
    private String bizscope;

    /** 省份 */
    @Column(name = "province_name")
    private String provinceName;

    /** 城市名称 */
    @Column(name = "city_name")
    private String cityName;

    /** 入档日期 */
    @Column(name = "apply_date")
    private Timestamp applyDate;

    /** 申请人 */
    @Column(name = "proposer")
    private String proposer;

    public void copy(Supplier source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}