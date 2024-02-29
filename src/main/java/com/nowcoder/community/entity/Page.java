package com.nowcoder.community.entity;
/*
封装分页相关的信息
* */
public class Page {
//    当前的页码
    private int current = 1;
//    显示的上线
    private  int limit =10 ;
//    数据的总数(用于计算总页数）
    private  int rows;
//    查询路径（复用分页的连接）
    private  String path;
//做判断看数据是否合法
    public int getCurrent() {
        return current;
    }
    public void setCurrent(int current) {
        if(current >= 1){
        this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit >=1 && limit <= 100){
        this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0){
        this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
  }

//  获取当前页的其实行
    public  int  getOffest(){
        return (current - 1 )*limit;
    }

//   获取总的页数
    public int getTotal(){
        if(rows % limit ==0){
            return rows/limit;
        }
        else return rows/limit+1;
    }
//    从第几页到第几页

//    获得起始页码
    public int getFrom(){
        int from = current - 2;
        return from<1 ? 1 : from;
    }
//    获得终止页码
    public int getTo(){
        int to = current +2;
        int total = getTotal();
        return to > total ? total : to;
    }

}
