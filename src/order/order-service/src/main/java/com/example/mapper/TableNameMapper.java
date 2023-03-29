package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.dto.AInputDto;
import com.example.domain.entity.TableNameEntity;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ligy
 * @since 2023-01-28
 */

public interface TableNameMapper extends BaseMapper<TableNameEntity> {

    String getName(@Param("id") Integer id);

    AInputDto getJson(@Param("id") Integer id);
}
