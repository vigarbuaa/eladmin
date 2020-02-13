package me.zhengjie.modules.employee.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author vigarbuaa
* @date 2020-02-13
*/
@Data
public class EmployeeInfoDto implements Serializable {

    /** ID */
    private Long id;

    /** 邮箱 */
    private String email;

    /** 手机号码 */
    private String phone;

    /** 身份证号 */
    private String idCard;

    /** 位址 */
    private String location;

    /** 创建日期 */
    private Timestamp createTime;

    /** 性别 */
    private String sex;

    private String name;

    /** 公司 */
    private String company;

    /** 岗位名称 */
    private String zhiwei;

    /** 备注 */
    private String remarks;

    /** 家乡 */
    private String hometown;

    /** 住址 */
    private String address;
}