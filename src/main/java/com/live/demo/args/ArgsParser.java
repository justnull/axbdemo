package com.live.demo.args;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @Author zhouyuhao
 * @Date 2019/6/4 1:03 PM
 */
@Data
public class ArgsParser {
    private final Map<String, ArgSchema> allKeysMap = new HashMap<>();

    public ArgsParser() {
        //是否记录日志 logging
        Predicate<String> pboolean = (String i) -> {
            return Boolean.parseBoolean(i);
        };
        allKeysMap.put("l", new ArgSchema("l", true, pboolean));
        // p（port，端口号）标记的值是整数型
        Predicate<String> pInteger = (String i) -> {
            try {
                //把字符串强制转换为数字
                int num = Integer.valueOf(i);
                //如果是数字，返回True
                return true;
            } catch (Exception e) {
                //如果抛出异常，返回False
                return false;
            }
        };
        allKeysMap.put("p", new ArgSchema("p", 0, pInteger));
        Predicate<String> pStr = (String i) -> {
            return true;
        };
        allKeysMap.put("d", new ArgSchema("d", "", pStr));

    }

    /**
     * 确定获取的flag 是否合法
     *
     * @param flag
     * @return
     */
    public boolean isContain(String flag) {
        return allKeysMap.containsKey(flag);
    }

    /**
     * 获取最终的合法键值对
     *
     * @param args
     * @return
     */
    public Args getValid(Args args) {
        Map<String, Arg> argMapResult = new HashMap<>();
        Map<String, Arg> argMap = args.getArgMap();
        for (Map.Entry<String, Arg> item : argMap.entrySet()) {
            Arg arg = item.getValue();
            String flag = arg.getFlag();
            String valueStr = (String) arg.getValue();
            if (isContain(flag)) {
                ArgSchema argSchema = allKeysMap.get(flag);
                if (StringUtils.isEmpty(valueStr)) {
                    argMapResult.put(flag, new Arg(flag, valueStr));
                } else {
                    Predicate predicate = argSchema.getPredicate();
                    if (predicate.test(valueStr)) {
                        argMapResult.put(flag, new Arg(flag, valueStr));
                    } else {
                        throw new IllegalArgumentException("当前flag:" + flag + "value格式错误");
                    }
                }
            } else {
                throw new IllegalArgumentException("当前flag不存在，格式错误");
            }
        }
        return new Args(argMapResult);
    }

    /**
     * 返回args还没有校验flag，是否在指定flag集合中，以及value类型是否服务
     *
     * @param inStr
     * @return
     */
    public Args parse(String inStr) {
        if (inStr == null) {
            throw new IllegalArgumentException("null字符串不需要解析");
        } else {
            int firstSepa = inStr.indexOf('-');
            if (firstSepa == -1) {
                throw new IllegalArgumentException("不存在'-'指定flag，格式错误");
            }
            Map<String, Arg> argMap = new HashMap<>();
            inStr = inStr.substring(firstSepa);
            String preproccessStr;
            preproccessStr = " " + inStr;
            String[] singleArgStr = StringUtils.split(preproccessStr, " -");

            Arg arg;
            for (int i = 0; i < singleArgStr.length; i++) {
                if (i != 0) {
                    // 最前面获取的是空的,忽略第一个
                    String instr = singleArgStr[i];
                    if (StringUtils.isEmpty(inStr)) {
                        throw new IllegalArgumentException("-没有和flag一起写，格式错误");
                    } else {
                        arg = parseSinge(instr);
                        argMap.put(arg.getFlag(), arg);
                    }
                }
            }
            return new Args(argMap);
        }

    }

    public Arg parseSinge(@NotNull String inStr) {
        inStr = StringUtils.trim(inStr);
        //  用空格做分隔符号
        int firstSeqa = inStr.indexOf(' ');
        if (firstSeqa == -1) {
            if (!StringUtils.isEmpty(inStr)) {
                // 存在flag ,但是value为空
                return new Arg(inStr, "");
            } else {
                // 不存在flag，value也为空
                throw new IllegalArgumentException("-没有和flag一起写，格式错误");
            }
        } else {
            // 存在分隔符
            String[] flagAndValue = StringUtils.split(inStr, " ");
            if (flagAndValue.length > 2) {
                throw new IllegalArgumentException("value之间存在空格，格式错误");
            } else {
                return new Arg(flagAndValue[0], flagAndValue[1]);
            }
        }
    }
}
