package me.zhengjie.modules.supplier.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author vigarbuaa
* @date 2020-02-17
*/
@Data
public class SupplierDto implements Serializable {

    private String buscode;

    /** ID */
    private Long id;

    /** 公司名称 */
    private String companyname;

    /** 名称缩写 */
    private String shortname;

    /** 成立日期 */
    private Timestamp founddate;

    /** 企业类型 */
    private String orgtype;

    /** 法人 */
    private String chairman;

    /** 注册资本 */
    private Double regcapital;

    /** 负责人 */
    private String manager;

    /** 注册地 */
    private String regaddr;

    /** 办公地 */
    private String officeaddr;

    /** 邮编 */
    private String officezipcode;

    /** 公司电话 */
    private String comptel;

    /** 公司简介 */
    private String compintro;

    /** 业务范围 */
    private String bizscope;

    /** 省份 */
    private String provinceName;

    /** 城市名称 */
    private String cityName;

    /** 入档日期 */
    private Timestamp applyDate;

    /** 申请人 */
    private String proposer;
}