package priv.jesse.mall.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priv.jesse.mall.entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    /**
     * 通过二级分类查找商品列表
     *
     * @param csid
     * @param pageable
     * @return
     */
    List<Product> findByCsid(int csid, Pageable pageable);

    List<Product> findByIsShowAndCsidIn(int isShow,List<Integer> csids,Pageable pageable);

    /**
     * 通过标题搜索商品
     *
     * @param keyword
     * @param pageable
     * @return
     */
    List<Product> findByTitleIsLike(String keyword, Pageable pageable);

    /**
     * 查找某个日期之后上架的商品
     * @param date
     * @param pageable
     * @return
     */
    List<Product> findByPdateAfter(Date date, Pageable pageable);

    /**
     * 查找热门商品
     * @param isHot
     * @param pageable
     * @return
     */
    @Query(value = "SELECT  * FROM product WHERE is_show=1 and is_hot=1  ORDER BY pdate ASC ",nativeQuery = true)
    List<Product> findHotProduct();

    /**
     * 查询最新商品，最近上新的24个商品
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM (SELECT  * FROM product WHERE is_show=1  ORDER BY pdate DESC limit 0,24) a /*#pageable*/",nativeQuery = true)
    List<Product> findNew(Pageable pageable);
    
    /**
     * 分页查询所有
     *
     * @param pageable
     * @return
     */
    
    Page<Product> findAllByOrderByPdateDesc(Pageable pageable);
}
