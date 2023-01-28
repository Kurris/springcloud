package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.entity.TableNameEntity;
import com.example.mapper.TableNameMapper;
import com.example.service.TableNameService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ligy
 * @since 2023-01-28
 */
@Service
public class TableNameServiceImpl extends ServiceImpl<TableNameMapper, TableNameEntity> implements TableNameService {

}
