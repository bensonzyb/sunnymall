package priv.jesse.mall.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.ClassificationDto;

import java.util.List;

public interface ClassificationDao extends JpaRepository<Classification, Integer> {
	
    @Query(value = "select new priv.jesse.mall.entity.ClassificationDto(c1.id,c1.parentId,c1.cname,c1.type,c1.cname as oneName) " +
            "FROM Classification c1 "
            + " where  c1.type=?1  order by c1.id asc "
            )
    List<ClassificationDto> findByType(int type);

    //Page<Classification> findByType(int type, Pageable pageable);

    List<Classification> findByParentId(int cid);
    
    
    //二级分类连表整改
    @Query(value = "select new priv.jesse.mall.entity.ClassificationDto(c1.id,c1.parentId,c1.cname,c1.type,c2.cname as oneName) " +
            "FROM Classification c1, Classification c2 "
            + " where   c2.id = c1.parentId and c1.type=?1  order by c1.parentId desc ",
            countQuery = "select count(*)" +
	                "from Classification c1  where   c1.type=?1 ")
    Page<ClassificationDto> findByType(int type,Pageable pageable);

}
