package com.light.hexo.business.admin.service.impl;

import com.aliyun.oss.ServiceException;
import com.light.hexo.business.admin.mapper.VisitMapper;
import com.light.hexo.business.admin.model.Visit;
import com.light.hexo.business.admin.model.event.VisitEvent;
import com.light.hexo.business.admin.service.VisitService;
import com.light.hexo.common.base.BaseMapper;
import com.light.hexo.common.base.BaseRequest;
import com.light.hexo.common.base.BaseServiceImpl;
import com.light.hexo.common.component.event.BaseEvent;
import com.light.hexo.common.component.event.EventEnum;
import com.light.hexo.common.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: VisitServiceImpl
 * @ProjectName hexo-boot
 * @Description: 访问 Service 实现
 */
@Service
@Slf4j
public class VisitServiceImpl extends BaseServiceImpl<Visit> implements VisitService {

    @Autowired
    private VisitMapper visitMapper;

    @Override
    public BaseMapper<Visit> getBaseMapper() {
        return this.visitMapper;
    }

    @Override
    protected Example getExample(BaseRequest request) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listVisitByDates(LocalDate start, LocalDate end) throws ServiceException {
        return this.visitMapper.selectVisitNumByDateList(start, end);
    }

    @Override
    public Integer getTodayVisitNum() throws GlobalException {
        LocalDate now = LocalDate.now();
        Example example = new Example(Visit.class);
        example.createCriteria().andBetween("createTime", LocalDateTime.of(now, LocalTime.MIN), LocalDateTime.of(now, LocalTime.MAX));
        return this.getBaseMapper().selectCountByExample(example);
    }

    @Override
    public EventEnum getEventType() {
        return EventEnum.VISIT;
    }

    @Override
    public void dealWithEvent(BaseEvent event) {

         VisitEvent visitEvent = (VisitEvent) event;
         String ipAddress = visitEvent.getIpAddress();
         String browser = visitEvent.getBrowser();
         LocalDate date = LocalDate.now();
         Example example = new Example(Visit.class);
         Example.Criteria criteria = example.createCriteria();
         criteria.andEqualTo("ipAddress", ipAddress);
         criteria.andEqualTo("visitDate", date);

         Visit visit = getBaseMapper().selectOneByExample(example);
         if (visit != null) {
             return;
         }

         Visit record = new Visit();
         record.setIpAddress(ipAddress)
               .setBrowser(browser)
               .setVisitDate(date)
               .setCreateTime(LocalDateTime.now())
               .setUpdateTime(record.getCreateTime());
         super.saveModel(record);
    }

}
