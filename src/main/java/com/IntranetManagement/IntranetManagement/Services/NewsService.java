package com.IntranetManagement.IntranetManagement.Services;

import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.News;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.NewsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final DepartmentRepository departmentRepository;

    public NewsService(NewsRepository newsRepository, DepartmentRepository departmentRepository) {
        this.newsRepository = newsRepository;
        this.departmentRepository = departmentRepository;
    }



    // Fetch
    // all highlighted by id
    public List<News> getHighlightedNews(Long departmentId) {
        return newsRepository.findAll().stream()
                .filter(doc -> doc.getIsHighlighted() == 1 && doc.getDepartment().getId().equals(departmentId) )
                .collect(Collectors.toList());
    }




    // Create
    public News createNews(News news, Long departmentId) {
        Department department = departmentRepository.findById(Math.toIntExact(departmentId))
                .orElseThrow(() -> new RuntimeException("Department not found"));

        news.setDepartment(department);
        return newsRepository.save(news);
    }

    // Update
    public News updateNews(Integer id, News updatedNews, Integer departmentId) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));

        news.setTitle(updatedNews.getTitle());
        news.setContent(updatedNews.getContent());
        news.setIsHighlighted(updatedNews.getIsHighlighted());

        if (departmentId != null) {
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new RuntimeException("Department not found."));
            news.setDepartment(department);}
        return newsRepository.save(news);
    }

    // Delete a News
    public void deleteNews(Integer id) {
        newsRepository.deleteById(id);
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public List<News> getAllNewsByDepartment(Integer departmentId) {
        return newsRepository.findByDepartmentIdAndIsHighlighted(departmentId, 1);
    }
}
