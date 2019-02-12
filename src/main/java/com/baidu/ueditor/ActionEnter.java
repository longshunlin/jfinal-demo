package com.baidu.ueditor;

import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ActionEnter
{
  private HttpServletRequest request = null;
  private String rootPath = null;
  private String contextPath = null;
  private String actionType = null;
  private ConfigManager configManager = null;

  public ActionEnter(HttpServletRequest request, String rootPath)
  {
    this.request = request;
    this.rootPath = rootPath;
    actionType = request.getParameter("action");
    contextPath = request.getContextPath();
    configManager = ConfigManager.getInstance(this.rootPath, contextPath, request.getRequestURI());
  }

  public String exec()
  {
    String callbackName = request.getParameter("callback");
    if (callbackName != null)
    {
      if (!validCallbackName(callbackName)) {
        return new BaseState(false, 401).toJSONString();
      }
      return callbackName + "(" + invoke() + ");";
    }
    return invoke();
  }

  public String invoke()
  {
    if ((actionType == null) || (!ActionMap.mapping.containsKey(actionType))) {
      return new BaseState(false, 101).toJSONString();
    }
    if ((configManager == null) || (!configManager.valid())) {
      return new BaseState(false, 102).toJSONString();
    }
    State state = null;

    int actionCode = ActionMap.getType(actionType);

    Map<String, Object> conf = null;
    switch (actionCode)
    {
    case 0:
      return configManager.getAllConfig().toString();
    case 1:
    case 2:
    case 3:
    case 4:
      conf = configManager.getConfig(actionCode);
      state = new Uploader(request, conf).doExec();
      break;
    case 5:
      conf = configManager.getConfig(actionCode);
      String[] list = request.getParameterValues((String)conf.get("fieldName"));
      state = new ImageHunter(conf).capture(list);
      break;
    case 6:
    case 7:
      conf = configManager.getConfig(actionCode);
      int start = getStartIndex();
      state = new FileManager(conf).listFile(start);
    }
    return state.toJSONString();
  }

  public int getStartIndex()
  {
    String start = request.getParameter("start");
    try
    {
      return Integer.parseInt(start);
    }
    catch (Exception e) {}
    return 0;
  }

  public boolean validCallbackName(String name)
  {
    if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
      return true;
    }
    return false;
  }
}
