package priv.jesse.mall.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.entity.Product;
import java.util.List;

public interface AboutusService {
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
	Aboutus findById(int id);

    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    Page<Aboutus> findAll(Pageable pageable);



    /**
     * 根据一级分类查找商品
     * @param cid
     * @param pageable
     * @return
     */
    List<Aboutus> findByCid(int cid,Pageable pageable);


    /**
     * 更新
     *
     * @param product
     * @return
     */
    void update(Aboutus aboutus);

    /**
     * 创建
     *
     * @param product
     * @return
     */
    int create(Aboutus aboutus);

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
    Aboutus selectOne();

}
