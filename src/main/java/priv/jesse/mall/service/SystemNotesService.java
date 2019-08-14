package priv.jesse.mall.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import priv.jesse.mall.entity.SystemNotes;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.SystemNotes;

import java.util.List;

public interface SystemNotesService {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
	SystemNotes findById(int id);

    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<SystemNotes> findAll(Pageable pageable);



    /**
     * 根据一级分类查找商品
     * @param cid
     * @param pageable
     * @return
     */
    List<SystemNotes> findByCid(int cid,Pageable pageable);


    /**
     * 更新
     *
     * @param product
     * @return
     */
    void update(SystemNotes systemNotes);

    /**
     * 创建
     *
     * @param product
     * @return
     */
    int create(SystemNotes systemNotes);

    /**
     * 根据Id删除
     *
     * @param id
     * @return
     */
    void delById(int id);
    
    
    /**
     * 获取最近编辑的记录
     * @return
     */
    SystemNotes selectOne();

}
