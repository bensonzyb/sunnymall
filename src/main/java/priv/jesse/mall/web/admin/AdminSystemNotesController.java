package priv.jesse.mall.web.admin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import priv.jesse.mall.entity.Aboutus;
import priv.jesse.mall.entity.Classification;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.SystemNotes;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.AboutusService;
import priv.jesse.mall.service.ClassificationService;
import priv.jesse.mall.service.ProductService;
import priv.jesse.mall.service.ShopCartService;
import priv.jesse.mall.service.SystemNotesService;
import priv.jesse.mall.utils.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/systemnotes")
public class AdminSystemNotesController {
	@Autowired
    private SystemNotesService systemNotesService;
 

    @RequestMapping("/toList.html")
    public String toList() {
        return "admin/system/systemnotes/list";
    }
    
    
    @RequestMapping("/toAdd.html")
    public String toAdd() {
        return "admin/system/systemnotes/add";
    }

    @RequestMapping("/toEdit.html")
    public String toEdit(int id, Map<String, Object> map) {
    	SystemNotes systemNotes = systemNotesService.findById(id);
        map.put("systemnotes", systemNotes);
        return "admin/system/systemnotes/edit";
    }

    @ResponseBody
    @RequestMapping("/list.do")
    public ResultBean<List<SystemNotes>> listAboutus(int pageindex,
                                                 @RequestParam(value = "pageSize", defaultValue = "15") int pageSize) {
        Pageable pageable = new PageRequest(pageindex, pageSize, null);
        List<SystemNotes> list = systemNotesService.findAll(pageable).getContent();
        return new ResultBean<>(list);
    }

    @ResponseBody
    @RequestMapping("/getTotal")
    public ResultBean<Integer> getTotal() {
        Pageable pageable = new PageRequest(1, 15, null);
        int total = (int) systemNotesService.findAll(pageable).getTotalElements();
        return new ResultBean<>(total);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    public ResultBean<Boolean> del(int id) {
    	systemNotesService.delById(id);
        return new ResultBean<>(true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add.do")
    public void add(String systemDesc,
                    HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
    	SystemNotes systemnotes = new SystemNotes();
    	systemnotes.setSystemDesc(systemDesc);
        int id = systemNotesService.create(systemnotes);
        if (id <= 0) {
            request.setAttribute("message", "添加失败！");
            request.getRequestDispatcher("toAdd.html").forward(request, response);
        } else {
            request.getRequestDispatcher("toEdit.html?id=" + id).forward(request, response);
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/update.do")
    public void update(int id,
                       String systemDesc,
                       HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
    	SystemNotes aboutus = systemNotesService.findById(id);
    	aboutus.setId(id);
    	aboutus.setSystemDesc(systemDesc);
        boolean flag = false;
        try {
        	systemNotesService.update(aboutus);
            flag = true;
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (!flag) {
            request.setAttribute("message", "更新失败！");
        }
        response.sendRedirect("toList.html");
    }

    
    /***
     * 描述富文本图片保存
     * @param image
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveDescImage.do")
    @ResponseBody
    public  Object   saveDescImage(@RequestParam(value="file", required=false)  MultipartFile file) throws Exception {
        String imgUrl = FileUtil.saveFile(file);
        Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map2 = new HashMap<String,Object>();
		map.put("code", 0);	//0表示上传成功
		map.put("msg","上传成功"); //提示消息
		map2.put("src", imgUrl);
		map2.put("title", "");
		map.put("data", map2);
		return map;

    }

}
