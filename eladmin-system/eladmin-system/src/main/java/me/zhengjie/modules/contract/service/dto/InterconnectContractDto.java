package me.zhengjie.modules.contract.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author vigarbuaa
* @date 2020-02-16
*/
@Data
public class InterconnectContractDto implements Serializable {

    /** ID */
    private Long id;

    /** 申请人 */
    private String proposer;

    /** 标的物类型 */
    private String subjectType;

    /** 标的物 */
    private String subject;

    /** 数量 */
    private Long number;

    /** 金额 */
    private Double money;

    /** 供应商 */
    private String supplier;

    /** 合同日期 */
    private Timestamp contractDate;

    /** 预算号 */
    private String budget;

    private String remarks;
}