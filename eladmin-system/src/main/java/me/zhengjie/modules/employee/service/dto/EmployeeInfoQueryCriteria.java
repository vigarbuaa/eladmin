package me.zhengjie.modules.employee.service.dto;

import lombok.Data;
import java.util.List;
import me.zhengjie.annotation.Query;

/**
* @author vigarbuaa
* @date 2020-02-13
*/
@Data
public class EmployeeInfoQueryCriteria{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String email;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String phone;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String idCard;

    /** 精确 */
    @Query
    private String location;

    /** 精确 */
    @Query
    private String sex;

    /** 精确 */
    @Query
    private String name;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String address;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String company;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String zhiwei;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String remarks;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String hometown;
}