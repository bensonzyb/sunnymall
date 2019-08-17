package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import priv.jesse.mall.dao.AboutusDao;
import priv.jesse.mall.dao.ClassificationDao;
import priv.jesse.mall.dao.ProductDao;
import priv.jesse.mall.dao.SmsRecordDao;
import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.SmsRecord;
import priv.jesse.mall.service.AboutusService;
import priv.jesse.mall.service.ProductService;
import priv.jesse.mall.service.SmsRecordService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmsRecordServiceImpl implements SmsRecordService {
    @Autowired
    private SmsRecordDao smsRecordDao;

	@Override
	public Page<SmsRecord> findAll(Pageable pageable) {
		return smsRecordDao.findAll(pageable);
	}

	@Override
	public int create(SmsRecord smsRecord) {
		return smsRecordDao.save(smsRecord).getId();
	}

	@Override
	public int  findBySmsRecord(SmsRecord smsRecord) {
		List<String> list=smsRecordDao.findBySmsRecord(smsRecord);
		return list.size();
	}

}
