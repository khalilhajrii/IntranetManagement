package com.IntranetManagement.IntranetManagement.Services;

import com.IntranetManagement.IntranetManagement.Entities.News;
import com.IntranetManagement.IntranetManagement.Repositories.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
    }

    public News addNews(News news) {
        news.setPublishedDate(java.time.LocalDateTime.now());
        return newsRepository.save(news);
    }

    public News updateNews(Long id, News updatedNews) {
        News existingNews = getNewsById(id);
        existingNews.setTitle(updatedNews.getTitle());
        existingNews.setContent(updatedNews.getContent());
        existingNews.setAuthor(updatedNews.getAuthor());
        return newsRepository.save(existingNews);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
