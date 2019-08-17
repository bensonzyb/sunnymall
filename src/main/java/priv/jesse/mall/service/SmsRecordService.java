package priv.jesse.mall.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.SmsRecord;

import java.util.List;

public interface SmsRecordService {

    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<SmsRecord> findAll(Pageable pageable);




    /**
     * 创建
     *
     * @param smsRecord
     * @return
     */
    int create(SmsRecord smsRecord);
    
    
    /**
     * 根据发送记录查询
     * @param smsRecord
     * @return
     */
    int findBySmsRecord(@Param("smsRecord") SmsRecord smsRecord);



}
