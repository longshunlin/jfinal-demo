package com.baidu.ueditor.define;

import com.baidu.ueditor.Encoder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseState
  implements State
{
  private boolean state = false;
  private String info = null;
  private Map<String, String> infoMap = new HashMap();

  public BaseState()
  {
    state = true;
  }

  public BaseState(boolean state)
  {
    setState(state);
  }

  public BaseState(boolean state, String info)
  {
    setState(state);
    this.info = info;
  }

  public BaseState(boolean state, int infoCode)
  {
    setState(state);
    info = AppInfo.getStateInfo(infoCode);
  }

  public boolean isSuccess()
  {
    return state;
  }

  public void setState(boolean state)
  {
    this.state = state;
  }

  public void setInfo(String info)
  {
    this.info = info;
  }

  public void setInfo(int infoCode)
  {
    info = AppInfo.getStateInfo(infoCode);
  }

  public String toJSONString()
  {
    return toString();
  }

  public String toString()
  {
    String key = null;
    String stateVal = isSuccess() ? AppInfo.getStateInfo(0) : info;

    StringBuilder builder = new StringBuilder();

    builder.append("{\"state\": \"" + stateVal + "\"");

    Iterator<String> iterator = infoMap.keySet().iterator();
    while (iterator.hasNext())
    {
      key = (String)iterator.next();

      builder.append(",\"" + key + "\": \"" + (String)infoMap.get(key) + "\"");
    }
    builder.append("}");

    return Encoder.toUnicode(builder.toString());
  }

  public void putInfo(String name, String val)
  {
    infoMap.put(name, val);
  }

  public void putInfo(String name, long val)
  {
    putInfo(name, val);
  }
}
