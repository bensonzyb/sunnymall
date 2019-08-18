package priv.jesse.mall.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.jesse.mall.entity.AdminUser;
import priv.jesse.mall.entity.pojo.ResultBean;
import priv.jesse.mall.service.AdminUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 璁块棶棣栭〉
     *
     * @return
     */
    @RequestMapping("/toIndex.html")
    public String toIndex() {
        return "admin/index";
    }

    /**
     * 璁块棶鐧诲綍椤甸潰
     *
     * @return
     */
    @RequestMapping("/toLogin.html")
    public String toLogin() {
        return "admin/login";
    }

    /**
     * 鐧诲綍璇锋眰
     *
     * @param username
     * @param password
     */
    //@ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/login.do")
    public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdminUser adminUser = adminUserService.checkLogin(request, username, password);
        response.sendRedirect("/admin/toIndex.html");///sunnymall
    }

    /**
     * 閫�鍑虹櫥褰�
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("login_user");
        response.sendRedirect("toLogin.html");
    }
}
