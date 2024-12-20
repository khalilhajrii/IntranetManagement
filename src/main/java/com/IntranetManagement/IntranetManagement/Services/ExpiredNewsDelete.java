package com.IntranetManagement.IntranetManagement.Services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpiredNewsDelete {

    private final NewsService newsService;

    public ExpiredNewsDelete(NewsService newsService) {
        this.newsService = newsService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanExpiredNews() {
        newsService.deleteExpiredNews();
        System.out.println("Expired news deleted.");
    }
}