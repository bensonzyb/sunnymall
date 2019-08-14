package priv.jesse.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class SystemNotes implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    /**
     * 系统操作描述
     */
    private String systemDesc;
    /**
     * 附件
     */
    @Column
    private String systemImg;
    
    
    public SystemNotes() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	
    
	 public String getSystemDesc() {
		return systemDesc;
	}

	public void setSystemDesc(String systemDesc) {
		this.systemDesc = systemDesc;
	}

	public String getSystemImg() {
		return systemImg;
	}

	public void setSystemImg(String systemImg) {
		this.systemImg = systemImg;
	}

	public SystemNotes(Integer id, String companyDesc,String systemDesc,String systemImg) {
	        this.id = id;
	        this.systemDesc = systemDesc;
	        this.systemImg = systemImg;
	    }
	
   
  
}