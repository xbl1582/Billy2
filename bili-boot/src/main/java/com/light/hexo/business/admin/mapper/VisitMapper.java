package com.light.hexo.business.admin.mapper;

import com.light.hexo.business.admin.model.Visit;
import com.light.hexo.common.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VisitMapper
 * @ProjectName hexo-boot
 * @Description: 访问 Mapper
 */
@Repository
public interface VisitMapper extends BaseMapper<Visit> {

    /**
     * 批量查询指定日期的访问数
     * @param start
     * @param end
     * @return
     */
    List<Map<String, Object>> selectVisitNumByDateList(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
