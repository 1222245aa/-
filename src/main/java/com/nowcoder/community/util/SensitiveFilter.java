package com.nowcoder.community.util;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

//    敏感词替换的符号
    private static final String REPLACEMENT = "***";
//    初始化根节点
    private TrieNode rootNode = new TrieNode();

//    初始化树，这个注解表示这是个初始化方法，当这个类被实例化后就自动初始化，
    @PostConstruct
    public void init() {
//        获取敏感词文本，通过类加载器来加载
        try (
//            再try后面加上小括号里面的代码会自动加上finally，并释放资源
//                在类加载其中加载文件 这里this表示任意类，过的他的类加载器，然后获得类加载器加载的文本
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
//                用字节读取汉字不方便，转换为字符流
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                )
        {
            String Keyword;
            while ((Keyword = reader.readLine()) != null) {
//                将读取到的敏感词添加到前缀树里面
                this.addKeyword(Keyword);

            }
        }
        catch(IOException e){
            logger.error("加载敏感词文件失败：" + e.getMessage());
        }
    }
//    将一个铭感词添加到前缀树里面去
    private void addKeyword (String keyword) {
        TrieNode tempNode = rootNode;
//        遍历铭感词
        for (int i = 0; i < keyword.length(); i ++ ) {
            char c = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(c);
//            如果没有收录这个铭感词就初始化子节点
            if (subNode == null) {
//                初始化子节点，将子节点添加到根节点下面
                subNode = new TrieNode();
                tempNode.addSubNode(c,subNode);
            }
//            指向子节点，进入下一轮循环
            tempNode = subNode;

            if (i == keyword.length() - 1) {
                tempNode.setKeywordEnd(true);
            }
        }
    }
    /*
    * 过滤铭感词
    * 1.传过来的的是待过滤的敏感词
    * 2.出来的是已过滤的敏感词
    *
    * */
    public String filter (String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
//        前缀树的指针
        TrieNode tempNode = rootNode;
//        指向敏感词的第一个指针
        int begin = 0;
//        第二个指针
        int position = 0;
//        结果
        StringBuffer sb = new StringBuffer();

        while (position < text.length()) {
            char c = text.charAt(position);
            //        跳过敏感词之间可能含有的符号
            if (isSymbol(c)) {
//                若指针处于根节点,将此符号计入结果,让指针2向下走一步
                if (tempNode == rootNode) {
                    sb.append(c);
                    begin++;
                }
//            无论开头还是中间,指针3都向下走一步
                position++;
                continue;
            }
//            检查下级节点
            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
//                以begin开头的字符不是铭感词
                sb.append(text.charAt(begin));
//                进入下一个位置
                position = ++begin;
//                重新指向根节点
                tempNode = rootNode;

            } else if (tempNode.isKeywordEnd()) {
                sb.append(REPLACEMENT);
                begin = ++position;
                tempNode = rootNode;
            } else {
//            检查以下各字符
                position++;
            }

        }
//        将最后一批字符计入结果
        sb.append(text.substring(begin));
        return sb.toString();
    }

//    判断是否为符号
    private boolean isSymbol (Character c) {
//        0x2E80~0x9FFF是属于东亚文字的范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 && c > 0x9FFF);
    }



    //构造前缀树
    private class TrieNode {
//        关键词结束的标识
        private boolean isKeywordEnd = false;

//        子节点（key是下级字符，value是下级节点）
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeywordEnd() {
            return isKeywordEnd;
        }

        public void setKeywordEnd(boolean keywordEnd) {
            isKeywordEnd = keywordEnd;
        }

//        添加子节点
        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

//        获取子节点
        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }


    }

}
