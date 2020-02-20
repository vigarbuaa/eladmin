package me.zhengjie.modules.todo.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author vigarbuaa
* @date 2020-02-19
*/
@Entity
@Data
@Table(name="todo")
public class Todo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** 内容 */
    @Column(name = "content")
    private String content;

    /** 1为已完成，0为未完成 */
    @Column(name = "completed")
    private Integer completed;

    /** 创建者 */
    @Column(name = "create_by")
    private String createBy;

    /** 创建时间 */
    @Column(name = "create_date")
    private Timestamp createDate;

    /** 备注信息 */
    @Column(name = "remarks")
    private String remarks;

    /** 1为已删除，0为未删除 */
    @Column(name = "del_flag")
    private Integer delFlag;

    public void copy(Todo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}