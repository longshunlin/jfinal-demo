package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.State;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Uploader
{
  private HttpServletRequest request = null;
  private Map<String, Object> conf = null;

  public Uploader(HttpServletRequest request, Map<String, Object> conf)
  {
    this.request = request;
    this.conf = conf;
  }

  public final State doExec()
  {
    String filedName = (String)conf.get("fieldName");
    State state = null;
    if ("true".equals(conf.get("isBase64"))) {
      state = Base64Uploader.save(request.getParameter(filedName),
        conf);
    } else {
      state = BinaryUploader.save(request, conf);
    }
    return state;
  }
}
