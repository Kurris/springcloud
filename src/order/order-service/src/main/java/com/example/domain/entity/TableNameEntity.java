package com.example.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.example.common.database.dto.BaseEntity;
import com.example.domain.dto.AInputDto;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author ligy
 * @since 2023-01-28
 */
@Data
@TableName("table_name")
public class TableNameEntity extends BaseEntity {

    private String address;

    private String name;

    private Boolean isMe;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<AInputDto> jsonCol;
}
