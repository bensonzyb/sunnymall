package priv.jesse.mall.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.service.AboutusService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	
	@Autowired
    private AboutusService aboutusService;
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
    public String toAboutus(Map<String, Object> map) {
    	Aboutus aboutus = aboutusService.selectOne();
        map.put("about", aboutus);
        return "mall/aboutus";
    }

}
