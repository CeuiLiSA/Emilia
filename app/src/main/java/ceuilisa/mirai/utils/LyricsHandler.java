package ceuilisa.mirai.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LyricsHandler
{
    private static LyricsHandler instance = null;

    private LinkedHashMap<String, String> lhm;

    private LyricsHandler(){}

    public static LyricsHandler getInstance()
    {
        if(instance == null)
            return new LyricsHandler();
        else
            return instance;
    }

    public LyricsHandler process(String str)
    {
        //正则表达式处理
        lhm = new LinkedHashMap<>();
        String pattern = "(\\[\\d\\d:\\d\\d.\\d*\\])(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        while(m.find())
        {
            //m.group(0)返回时间加文本
            //m.group(1)返回时间
            //m.group(2)返回文本
            if(!lhm.containsKey(m.group(1)))
                lhm.put(m.group(1), m.group(2));
            else
                lhm.put(m.group(1), lhm.get(m.group(1)) + "\n" + m.group(2));
        }

        return this;
    }

    public String toString()
    {
        String str = "";
        //迭代器遍历map
        Iterator<Map.Entry<String, String>> entries = lhm.entrySet().iterator();
        while(entries.hasNext())
        {
            Map.Entry<String, String> entry = entries.next();
            str += entry.getKey() + entry.getValue() + "\n";
        }
        return str;
    }
}
