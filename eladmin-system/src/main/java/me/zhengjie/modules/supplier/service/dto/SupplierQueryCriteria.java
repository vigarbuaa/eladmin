package me.zhengjie.modules.supplier.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author vigarbuaa
* @date 2020-02-17
*/
@Data
public class SupplierQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String buscode;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String companyname;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String shortname;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String orgtype;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String chairman;

    /** 大于等于 */
    @Query(type = Query.Type.GREATER_THAN)
    private Double regcapital;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String bizscope;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String provinceName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String cityName;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String proposer;
    /** BETWEEN */
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}