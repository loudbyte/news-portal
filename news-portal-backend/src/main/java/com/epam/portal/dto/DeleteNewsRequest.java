package com.epam.portal.dto;

import java.util.List;

public class DeleteNewsRequest {
    private List<Long> newsIdList;

    public DeleteNewsRequest() {
    }

    public DeleteNewsRequest(List<Long> newsIdList) {
        this.newsIdList = newsIdList;
    }

    public List<Long> getNewsIdList() {
        return newsIdList;
    }

    public void setNewsIdList(List<Long> newsIdList) {
        this.newsIdList = newsIdList;
    }
}
