package com.light.hexo.business.portal.web.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName: HtmlResponseWrapper
 * @ProjectName hexo-boot
 * @Description: 重写响应
 */
public class HtmlResponseWrapper extends HttpServletResponseWrapper {

    private final CharArrayWriter output;

    public HtmlResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new CharArrayWriter();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output);
    }

    @Override
    public String toString() {
        return output.toString();
    }
}
