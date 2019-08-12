package priv.jesse.mall.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * 鏉冮檺鎷︽埅鍣�
 *
 * @author hfb
 * @date 2017/9/18
 */
@WebFilter
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 鏀寔璺ㄥ煙璁块棶
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,X-Custom-Header");
        response.setHeader("X-Powered-By", "SpringBoot");
        if ("option".equalsIgnoreCase(request.getMethod())) {
            responseJSON(response, new HashMap<>());
            return;
        }
        //闄や簡鎷︽埅login.html 鍏朵粬html閮芥嫤鎴�
        StringBuffer url = request.getRequestURL();
        //System.out.println(url);
        String path = url.toString();
        // 鍙嫤鎴繖浜涚被鍨嬭姹�
        if (path.endsWith(".do") || path.endsWith(".html")) {
            // 鐧诲綍锛屽浘鐗囦笉鎷︽埅
            if (path.endsWith("toLogin.html")
                    || path.endsWith("toRegister.html")
                    || path.endsWith("register.do")
                    || path.endsWith("login.do")
                    || path.endsWith("logout.do")
                    || path.endsWith("error.html")
                    || path.endsWith("checkUsername.do")
                    || path.indexOf("/sunnymall/admin/product/img/") != -1
                    || path.endsWith("index.html")
                    || path.endsWith("classification/list.do")
                    || path.endsWith("aboutus.html")
                    || path.endsWith("toAboutus.html")
                    || path.indexOf("product") != -1) {
                chain.doFilter(request, response);
            } else {
                processAccessControl(request, response, chain);
            }

        } else {
            //鍏朵粬闈欐�佽祫婧愰兘涓嶆嫤鎴�
            chain.doFilter(request, response);
        }
    }

    /**
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    private void processAccessControl(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object adminUser = request.getSession().getAttribute("login_user");
        Object user = request.getSession().getAttribute("user");
        String url = request.getRequestURL().toString();
        if (url.indexOf("admin") != -1){
            if (adminUser == null) {
                response.sendRedirect("/sunnymall/admin/toLogin.html");
            }else {
                chain.doFilter(request, response);
            }
        }else {
            if (user == null) {
                response.sendRedirect("/sunnymall/user/toLogin.html");
            }else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 杩斿洖JOSN鏁版嵁鏍煎紡
     *
     * @param response
     * @param object
     * @throws IOException
     */
    public static void responseJSON(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        if (object == null)
            return;
        String jsonStr = mapper.writeValueAsString(object);
        OutputStream out = response.getOutputStream();
        out.write(jsonStr.getBytes("UTF-8"));
        out.flush();
    }
}
