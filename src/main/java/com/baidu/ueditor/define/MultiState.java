package com.baidu.ueditor.define;

import com.baidu.ueditor.Encoder;

import java.util.*;

public class MultiState
  implements State
{
  private boolean state = false;
  private String info = null;
  private Map<String, Long> intMap = new HashMap();
  private Map<String, String> infoMap = new HashMap();
  private List<String> stateList = new ArrayList();

  public MultiState(boolean state)
  {
    this.state = state;
  }

  public MultiState(boolean state, String info)
  {
    this.state = state;
    this.info = info;
  }

  public MultiState(boolean state, int infoKey)
  {
    this.state = state;
    info = AppInfo.getStateInfo(infoKey);
  }

  public boolean isSuccess()
  {
    return state;
  }

  public void addState(State state)
  {
    stateList.add(state.toJSONString());
  }

  public void putInfo(String name, String val)
  {
    infoMap.put(name, val);
  }

  public String toJSONString()
  {
    String stateVal = isSuccess() ? AppInfo.getStateInfo(0) : info;

    StringBuilder builder = new StringBuilder();

    builder.append("{\"state\": \"" + stateVal + "\"");


    Iterator<String> iterator = intMap.keySet().iterator();
    while (iterator.hasNext())
    {
      stateVal = (String)iterator.next();

      builder.append(",\"" + stateVal + "\": " + intMap.get(stateVal));
    }
    iterator = infoMap.keySet().iterator();
    while (iterator.hasNext())
    {
      stateVal = (String)iterator.next();

      builder.append(",\"" + stateVal + "\": \"" + (String)infoMap.get(stateVal) + "\"");
    }
    builder.append(", list: [");


    iterator = stateList.iterator();
    while (iterator.hasNext()) {
      builder.append((String)iterator.next() + ",");
    }
    if (stateList.size() > 0) {
      builder.deleteCharAt(builder.length() - 1);
    }
    builder.append(" ]}");

    return Encoder.toUnicode(builder.toString());
  }

  public void putInfo(String name, long val)
  {
    intMap.put(name, Long.valueOf(val));
  }
}
