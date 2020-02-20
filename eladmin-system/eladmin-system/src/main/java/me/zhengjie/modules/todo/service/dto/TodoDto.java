package me.zhengjie.modules.todo.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author vigarbuaa
* @date 2020-02-19
*/
@Data
public class TodoDto implements Serializable {

    private Integer id;

    /** 内容 */
    private String content;

    /** 1为已完成，0为未完成 */
    private Integer completed;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Timestamp createDate;

    /** 备注信息 */
    private String remarks;

    /** 1为已删除，0为未删除 */
    private Integer delFlag;
}