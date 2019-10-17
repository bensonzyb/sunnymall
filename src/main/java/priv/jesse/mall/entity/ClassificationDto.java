package priv.jesse.mall.entity;

import java.io.Serializable;

/**
 * 分类DTO
 */
public class ClassificationDto  {
	
	private static final long serialVersionUID = 1L;
   
    private Integer id;
    /**
     * 上级分类Id
     */
    private Integer parentId;
    /**
     * 分类名称
     */
    private String cname;
    /**
     * 类型 1一级分类 2二级分类
     */
    private Integer type;
    
    //一级分类名称
    private String oneName;
    


    public ClassificationDto(Integer id, Integer parentId, String cname, Integer type,String oneName ) {
        this.id = id;
        this.parentId = parentId;
        this.cname = cname;
        this.type = type;
        this.oneName=oneName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    
    
    public String getOneName() {
		return oneName;
	}

	public void setOneName(String oneName) {
		this.oneName = oneName;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", cname=").append(cname);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}