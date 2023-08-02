package com.bookshop01.board.vo;

import org.springframework.stereotype.Component;

@Component("replyVO")
public class ReplyVO {

    private int articleNO;
    private String content;
    private String member_id;

    public int getArticleNO() {
        System.out.println("get articleNO : "+articleNO);
        return articleNO;
    }

    public void setArticleNO(int articleNO) {
        System.out.println("set"+articleNO);
        this.articleNO = articleNO;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
