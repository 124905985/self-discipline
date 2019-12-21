package cn.suvue.discipline.core.tools;

import cn.suvue.discipline.core.consts.SysConst;

import javax.servlet.http.HttpServletRequest;

/**
 * http自定义工具类
 *
 * @author suvue
 * @date 2019/12/21 10:49
 */
public class HttpTool {
    /**
     * 获取请求的绝对路径
     *
     * @author suvue
     * @date 2019/12/21 11:02
     */
    public static String getAbsolutePath(HttpServletRequest request, String sendPath) {
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        String queryString = request.getQueryString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http").append("://").append(serverName);
        if (port != SysConst.HTTP_DEFAULT_PORT && port != SysConst.HTTPS_DEFAULT_PORT) {
            stringBuilder.append(":").append(port);
        }
        if (contextPath != null) {
            stringBuilder.append(contextPath);
        }
        stringBuilder.append(sendPath);
        if (queryString != null) {
            stringBuilder.append(queryString);
        }
        return stringBuilder.toString();
    }


}
