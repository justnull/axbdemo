package com.live.demo.args;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 *
 */
public class ArgsParserTest {
    private ArgsParser argsParser = new ArgsParser();
    @Before
    public void setUp() {

    }
    /**
     * 测试1获取的字符串已经是符号语法的
     * <p>
     * 2。判断flag是否在定义的范围内
     */
    @Test
    public void testcontainedKeys(){
        String[] keys = {"p", "d", "l"};
        for (String key:
             keys) {
                if (!argsParser.isContain(key)){
                    fail("flag值不合法");
            }
        }
    }

    /**
     * 测试应该包含的所有key
     * keysSet.containsAll(keys)
     * String[] curKeys = keysSet.toArray(new String[0]);
     */
    @Test
    public void testSetEqual(){
        String[] expectedKeys = {"p", "l", "d"};
        HashSet<String> expectedSet = new HashSet<>(Arrays.asList(expectedKeys));
        Map<String, ArgSchema> allKeysMap = argsParser.getAllKeysMap();
        Set<String> keysSet = allKeysMap.keySet();
        boolean setEqual = expectedSet.containsAll(keysSet) && keysSet.containsAll(expectedSet);
        assertTrue("没有按照规范定义所有的flag",  setEqual);
    }

    /**
     *  外部的键等于内部ArgSchema的键
     */
    @Test
    public void testMapkeyEqualKeysInArgSchema(){
        Map<String, ArgSchema> allKeysMap = argsParser.getAllKeysMap();
        Set<String> keysSet = allKeysMap.keySet();
        HashSet<String> keysInArgSchema = new HashSet<>();
        for (Map.Entry<String,ArgSchema> entry:
            allKeysMap.entrySet()) {
            ArgSchema argSchema = entry.getValue();
            keysInArgSchema.add(argSchema.getFlag());
        }
        boolean setEqual = keysInArgSchema.containsAll(keysSet) && keysSet.containsAll(keysInArgSchema);
        assertTrue("外部的键等于内部ArgSchema的键",setEqual);
    }
}
