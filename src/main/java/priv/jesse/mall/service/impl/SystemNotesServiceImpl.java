package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import priv.jesse.mall.dao.SystemNotesDao;
import priv.jesse.mall.entity.SystemNotes;
import priv.jesse.mall.service.SystemNotesService;

import java.util.List;

@Service
public class SystemNotesServiceImpl implements SystemNotesService {
    @Autowired
    private SystemNotesDao systemNotesDao;

    @Override
    public SystemNotes findById(int id) {
        return systemNotesDao.getOne(id);
    }
    @Override
    public Page<SystemNotes> findAll(Pageable pageable) {
        return systemNotesDao.findAll(pageable);
    }

    @Override
    public void update(SystemNotes systemNotes) {
    	systemNotesDao.save(systemNotes);
    }

    @Override
    public int create(SystemNotes systemNotes) {
        return systemNotesDao.save(systemNotes).getId();
    }

	@Override
	public List<SystemNotes> findByCid(int cid, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delById(int id) {
		systemNotesDao.delete(id);
		
	}
	@Override
	public SystemNotes selectOne() {
		return systemNotesDao.selectOne();
	}

}
