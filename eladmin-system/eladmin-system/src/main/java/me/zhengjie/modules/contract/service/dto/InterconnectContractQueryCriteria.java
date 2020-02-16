package me.zhengjie.modules.contract.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author vigarbuaa
* @date 2020-02-16
*/
@Data
public class InterconnectContractQueryCriteria{

    /** 精确 */
    @Query
    private String proposer;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String subjectType;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String subject;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    private Double money;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String supplier;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    private Timestamp contractDate;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String budget;
}