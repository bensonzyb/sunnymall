package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import priv.jesse.mall.dao.AboutusDao;
import priv.jesse.mall.dao.ClassificationDao;
import priv.jesse.mall.dao.ProductDao;
import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.service.AboutusService;
import priv.jesse.mall.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AboutusServiceImpl implements AboutusService {
    @Autowired
    private AboutusDao aboutusDao;

    @Override
    public Aboutus findById(int id) {
        return aboutusDao.getOne(id);
    }
    @Override
    public Page<Aboutus> findAll(Pageable pageable) {
        return aboutusDao.findAll(pageable);
    }

    @Override
    public void update(Aboutus aboutus) {
    	aboutusDao.save(aboutus);
    }

    @Override
    public int create(Aboutus aboutus) {
        return aboutusDao.save(aboutus).getId();
    }

	@Override
	public List<Aboutus> findByCid(int cid, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delById(int id) {
		aboutusDao.delete(id);
		
	}
	@Override
	public Aboutus selectOne() {
		return aboutusDao.selectOne();
	}

}
