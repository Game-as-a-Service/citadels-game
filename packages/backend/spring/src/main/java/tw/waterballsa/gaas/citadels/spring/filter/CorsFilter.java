package tw.waterballsa.gaas.citadels.spring.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain Chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3001"); // 允許的來源

        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");  // 允許的 HTTP 方法
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");  // 允許的標頭
        httpResponse.setHeader("Access-Control-Expose-Headers", "Content-Disposition"); // 允許的檔頭書寫
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");  // 是否允許使用憑證（例如跨網域的 AJAX 請求中的 cookie）

        // 處理Preflight request
        if (httpRequest.getMethod().equals("OPTIONS")) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        Chain.doFilter(request, response);
    }
}
