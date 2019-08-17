package priv.jesse.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.SmsRecord;



public interface SmsRecordDao extends JpaRepository<SmsRecord, Integer> {
   
	
    @Query(value = "SELECT  * FROM sms_record  ORDER BY id DESC limit 1 ",nativeQuery = true)
    SmsRecord selectOne();
    
    
 
    /**
	 * 根据用户id查询所有租户角色id
	 * @param userId
	 * @return
	 * 
	 * @Query( " UPDATE Role SET "+
			"delFalg =  :#{#role.delFalg} ," +
			"remark =  :#{#role.remark} ," +
			"status =  :#{#role.status} ," +
			"tenantId =  :#{#role.tenantId} ," +
			"creator =  :#{#role.creator} ," +
			"createTime =  :#{#role.createTime} ," +
			"updater =  :#{#role.updater} ," +
			"updateTime =  :#{#role.updateTime} ," +
			"name =  :#{#role.name} " +
			" WHERE id = :#{#role.id}"
			)
	 * 
	 */
	@Query("select id from SmsRecord  where  sendDate =:#{#smsRecord.sendDate} "
			+ "and hostAddress=:#{#smsRecord.hostAddress} "
			+ " and  hostName=:#{#smsRecord.hostName}")
	List<String> findBySmsRecord(@Param("smsRecord") SmsRecord smsRecord);
}
