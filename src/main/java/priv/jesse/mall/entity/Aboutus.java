package priv.jesse.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Aboutus implements Serializable {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    /**
     * 关于我们简介
     */
    @Column(name = "`companyDesc`")
    private String companyDesc;
    /**
     * 附件
     */
    @Column
    private String companyImg;
    /**
     * 联系方式
     */
    @Column
    private String companyContact;
    
    public Aboutus() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	public String getCompanyDesc() {
		return companyDesc;
	}
	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}
	public String getCompanyImg() {
		return companyImg;
	}
	public void setCompanyImg(String companyImg) {
		this.companyImg = companyImg;
	}
	public String getCompanyContact() {
		return companyContact;
	}
	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}
    
	 public Aboutus(Integer id, String companyDesc,String companyImg,String companyContact) {
	        this.id = id;
	        this.companyDesc = companyDesc;
	        this.companyImg = companyImg;
	        this.companyContact = companyContact;
	       
	    }
	 @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append(getClass().getSimpleName());
	        sb.append(" [");
	        sb.append("id=").append(id);
	        sb.append(", desc=").append(companyDesc);
	        sb.append("]");
	        return sb.toString();
	    }
   
  
}