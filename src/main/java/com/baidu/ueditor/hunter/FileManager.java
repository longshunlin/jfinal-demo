package com.baidu.ueditor.hunter;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class FileManager
{
  private String dir = null;
  private String rootPath = null;
  private String[] allowFiles = null;
  private int count = 0;

  public FileManager(Map<String, Object> conf)
  {
    rootPath = ((String)conf.get("rootPath"));
    dir = (rootPath + (String)conf.get("dir"));
    allowFiles = getAllowFiles(conf.get("allowFiles"));
    count = ((Integer)conf.get("count")).intValue();
  }

  public State listFile(int index)
  {
    File dir = new File(this.dir);
    State state = null;
    if (!dir.exists()) {
      return new BaseState(false, 302);
    }
    if (!dir.isDirectory()) {
      return new BaseState(false, 301);
    }
    Collection<File> list = FileUtils.listFiles(dir, allowFiles, true);
    if ((index < 0) || (index > list.size()))
    {
      state = new MultiState(true);
    }
    else
    {
      Object[] fileList = Arrays.copyOfRange(list.toArray(), index, index + count);
      state = getState(fileList);
    }
    state.putInfo("start", index);
    state.putInfo("total", list.size());

    return state;
  }

  private State getState(Object[] files)
  {
    MultiState state = new MultiState(true);
    BaseState fileState = null;

    File file = null;
    for (Object obj : files)
    {
      if (obj == null) {
        break;
      }
      file = (File)obj;
      fileState = new BaseState(true);
      fileState.putInfo("url", PathFormat.format(getPath(file)));
      state.addState(fileState);
    }
    return state;
  }

  private String getPath(File file)
  {
    String path = file.getAbsolutePath();

    return path.replace(rootPath, "/");
  }

  private String[] getAllowFiles(Object fileExt)
  {
    String[] exts = (String[])null;
    String ext = null;
    if (fileExt == null) {
      return new String[0];
    }
    exts = (String[])fileExt;

    int i = 0;
    for (int len = exts.length; i < len; i++)
    {
      ext = exts[i];
      exts[i] = ext.replace(".", "");
    }
    return exts;
  }
}
