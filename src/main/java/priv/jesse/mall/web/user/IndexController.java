package priv.jesse.mall.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.entity.SystemNotes;
import priv.jesse.mall.service.AboutusService;
import priv.jesse.mall.service.SystemNotesService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	
	@Autowired
    private AboutusService aboutusService;
	
	@Autowired
    private SystemNotesService systemNotesService;
    /**
     * 打开首页
     * @return
     */
    @RequestMapping("/index.html")
    public String toIndex() {
        return "mall/index";
    }

    /**
     * 访问根目录转发到首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "forward:/index.html";
    }
    
    /**
     * 关于我们
     * @return  
     */
    @RequestMapping("/aboutus.html")
    public String aboutus(Map<String, Object> map) {
    	Aboutus aboutus = aboutusService.selectOne();
        map.put("about", aboutus);
        return "mall/aboutus";
    }
    
    /**
     * 系统操作简介
     * @return  
     */
    @RequestMapping("/systemNotes.html")
    public String systemNotes(Map<String, Object> map) {
    	SystemNotes systemnotes = systemNotesService.selectOne();
        map.put("systemnotes", systemnotes);
        return "mall/systemnotes";
    }

}
