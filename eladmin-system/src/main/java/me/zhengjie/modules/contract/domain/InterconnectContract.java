package me.zhengjie.modules.contract.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author vigarbuaa
* @date 2020-02-16
*/
@Entity
@Data
@Table(name="interconnect_contract")
public class InterconnectContract implements Serializable {

    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /** 申请人 */
    @Column(name = "proposer")
    private String proposer;

    /** 标的物类型 */
    @Column(name = "subject_type")
    private String subjectType;

    /** 标的物 */
    @Column(name = "subject")
    private String subject;

    /** 数量 */
    @Column(name = "number")
    private Long number;

    /** 金额 */
    @Column(name = "money")
    private Double money;

    /** 供应商 */
    @Column(name = "supplier")
    private String supplier;

    /** 合同日期 */
    @Column(name = "contract_date")
    private Timestamp contractDate;

    /** 预算号 */
    @Column(name = "budget")
    private String budget;

    @Column(name = "remarks")
    private String remarks;

    public void copy(InterconnectContract source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}