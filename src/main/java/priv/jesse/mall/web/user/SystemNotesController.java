package priv.jesse.mall.web.user;

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
@RequestMapping("/systemnotes")
public class SystemNotesController {
	@Autowired
    private SystemNotesService systemNotesService;
 
    @RequestMapping("/toSystemNotes.html")
    @ResponseBody
    public ResultBean<SystemNotes> toEdit() {
    	SystemNotes systemNotes = systemNotesService.selectOne();
        return new ResultBean<>(systemNotes);
    }

   

}
