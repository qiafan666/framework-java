package com.ning.web.jotato.core.xss;

import com.alibaba.fastjson.JSON;
import com.ning.web.jotato.base.enums.CharsetEnum;
import com.ning.web.jotato.base.model.IResult;
import com.ning.web.jotato.base.model.result.BaseResult;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * Created by ning 2023年5月21日15:59:55.
 */
@Slf4j
public class XSSFilter implements Filter {

    /**
     * 特殊字符匹配
     */
    private Pattern xssPattern;


    @Override
    public void init(FilterConfig fConfig) {
        StringBuilder tempStr = new StringBuilder("^");
        tempStr.append(".*[A|a][L|l][E|e][R|r][T|t]\\s*\\(.*\\).*");
        tempStr.append("|");
        tempStr.append(".*[W|w][I|i][N|n][D|d][O|o][W|w]\\.[L|l][O|o][C|c][A|a][T|t][I|i][O|o][N|n]\\s*=.*");
        tempStr.append("|");
        tempStr.append(".*[S|s][T|t][Y|y][L|l][E|e]\\s*=.*[X|x]:[E|e][X|x].*[P|p][R|r][E|e][S|s]{1,2}[I|i][O|o][N|n]\\s*\\(.*\\).*");
        tempStr.append("|");
        tempStr.append(".*[D|d][O|o][C|c][U|u][M|m][E|e][N|n][T|t]\\.[C|c][O|o]{2}[K|k][I|i][E|e].*");
        tempStr.append("|");
        tempStr.append(".*[E|e][V|v][A|a][L|l]\\s*\\(.*\\).*");
        tempStr.append("|");
        tempStr.append(".*[E|e][X|x][E|e][C|c][S|s][C|c][R|r][I|i][P|p][T|t]\\s*\\(.*\\).*");
        tempStr.append("|");
        tempStr.append(".*[M|m][S|s][G|g][B|b][O|o][X|x]\\s*\\(.*\\).*");
        tempStr.append("|");
        tempStr.append(".*[C|c][O|o][N|n][F|f][I|i][R|r][M|m]\\s*\\(.*\\).*");
        tempStr.append("|");
        tempStr.append(".*[P|p][R|r][O|o][M|m][P|p][T|t]\\s*\\(.*\\).*");
        tempStr.append("|");
        tempStr.append(".*<[S|s][C|c][R|r][I|i][P|p][T|t]>.*</[S|s][C|c][R|r][I|i][P|p][T|t]>.*");
        tempStr.append("|");

        tempStr.append(".*<[S|s][C|c][R|r][I|i][P|p][T|t]\\s*[S|s][R|r][C|c]\\s*=.*>.*</[S|s][C|c][R|r][I|i][P|p][T|t]>.*");
        tempStr.append("|");

        tempStr.append(".*<[S|s][C|c][R|r][I|i][P|p][T|t]\\s*[T|t][Y|y][P|p][E|e]\\s*=.*>.*</[S|s][C|c][R|r][I|i][P|p][T|t]>.*");
        tempStr.append("|");
        // <img src=
        tempStr.append(".*<[I|i][M|m][G|g]\\s*[S|s][R|r][C|c]\\s*=.*");
        tempStr.append("|");
        // <img style
        tempStr.append(".*<[I|i][M|m][G|g]\\s*[S|s][T|t][Y|y][L|l][E|e]\\s*=.*");
        tempStr.append("|");
        // <a href=
        tempStr.append(".*<[A|a]\\s*[H|h][R|r][E|e][F|f]\\s*=.*");
        tempStr.append("|");
        // <input
        tempStr.append(".*<[I|i][N|n][P|p][U|u][T|t]\\s*.*");
        tempStr.append("|");
        // <input
        tempStr.append(".*<[F|f][O|o][R|r][M|M]\\s*.*");
        tempStr.append("|");
        // <iframe
        tempStr.append(".*<[I|i][F|f][R|r][A|a][M|m][E|e]\\s*[S|s][R|r][C|c]\\s*=.*");
        tempStr.append("|");

        // accesskey=
        tempStr.append(".*[A|a][C|c][C|c][E|e][S|s][S|s][K|k][E|e][Y|y]\\s*=.*");
        tempStr.append("|");
        // <svg
        tempStr.append(".*<[S|s][V|v][G|g].*");
        tempStr.append("|");

        tempStr.append("[.&[^\"]]*\"[.&[^\"]]*");
        tempStr.append("|");
        tempStr.append("[.&[^']]*'[.&[^']]*");
        tempStr.append("|");
        tempStr.append("[[.&[^a]]|[|a|\n|\r\n|\r|\u0085|\u2028|\u2029]]*<[S|s][C|c][R|r][I|i][P|p][T|t]>.*</[S|s][C|c][R|r][I|i][P|p][T|t]>[[.&[^a]]|[|a|\n|\r\n|\r|\u0085|\u2028|\u2029]]*");
        tempStr.append("|");
        xssPattern = Pattern.compile(tempStr.substring(0, tempStr.length() - 1));
        log.info("安全组件启动，过滤规则：" + tempStr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        BaseResult responseDTO = new BaseResult();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // xss 拒绝
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);
        // url非法校验
        // 对request信息进行封装并进行校验工作，若校验失败（含非法字符），根据配置信息进行日志记录和请求中断处理
        if (xssRequest.validateParameter(xssPattern) || xssPattern.matcher(URLDecoder.decode(request.getRequestURL().toString(), CharsetEnum.UTF_8.getCode())).matches()) {
            response.setStatus(400);
            response.setContentType("text/plain;charset=utf-8");
            responseDTO.setSuccess(false);
            responseDTO.setMsg(IResult.ILLEGAL_ARGUMENT.getMessage());
            responseDTO.setCode(IResult.ILLEGAL_ARGUMENT.getCode());
            response.getWriter().print(JSON.toJSONString(responseDTO));
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // document why this method is empty
    }

}
