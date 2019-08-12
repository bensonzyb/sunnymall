package priv.jesse.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.entity.Product;



public interface AboutusDao extends JpaRepository<Aboutus, Integer> {
   
	 /**
     * 获取最近一条
     * @param pageable
     * @return
     */
    @Query(value = "SELECT  * FROM aboutus  ORDER BY id DESC limit 1 ",nativeQuery = true)
    Aboutus selectOne();
}
