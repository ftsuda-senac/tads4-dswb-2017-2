/*
 * The MIT License
 *
 * Copyright 2017 fernando.tsuda.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.senac.tads4.dswb.tadsstore.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author http://smartkey.co.uk/development/implementing-flash-scope-in-java-web-applications/
 */
@WebFilter(filterName = "FlashScopeFilter", urlPatterns = {"/*"})
public class FlashScopeFilter implements Filter {

  private static final String FLASH_SESSION_KEY = "FLASH_SESSION_KEY";
  
  @Override
  @SuppressWarnings("unchecked")
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain) throws IOException, ServletException {

    //reinstate any flash scoped params from the users session 
    //and clear the session
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpSession session = httpRequest.getSession(false);
      if (session != null) {
        Map<String, Object> flashParams = (Map<String, Object>) session.getAttribute(FLASH_SESSION_KEY);
        if (flashParams != null) {
          for (Map.Entry<String, Object> flashEntry : flashParams.entrySet()) {
            request.setAttribute(flashEntry.getKey(), flashEntry.getValue());
          }
          session.removeAttribute(FLASH_SESSION_KEY);
        }
      }
    }

    //process the chain
    chain.doFilter(request, response);

    //store any flash scoped params in the user's session for the 
    //next request
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      Map<String, Object> flashParams = new HashMap();
      Enumeration e = httpRequest.getAttributeNames();
      while (e.hasMoreElements()) {
        String paramName = (String) e.nextElement();
        if (paramName.startsWith("flash.")) {
          Object value = request.getAttribute(paramName);
          paramName = paramName.substring(6, paramName.length());
          flashParams.put(paramName, value);
        }
      }
      if (flashParams.size() > 0) {
        HttpSession session = httpRequest.getSession(false);
        session.setAttribute(FLASH_SESSION_KEY, flashParams);
      }
    }
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    //no-op
  }

  @Override
  public void destroy() {
    //no-op
  }
}
