package com.IntranetManagement.IntranetManagement.Services;

import com.IntranetManagement.IntranetManagement.Entities.Department;
import com.IntranetManagement.IntranetManagement.Entities.news;
import com.IntranetManagement.IntranetManagement.repositories.DepartmentRepository;
import com.IntranetManagement.IntranetManagement.repositories.NewsRepository;
import org.springframework.stereotype.Service;

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
    public List<news> getHighlightedNews(Long departmentId) {
        return newsRepository.findAll().stream()
                .filter(doc -> doc.getIsHighlighted() == 1 && doc.getDepartment().getId().equals(departmentId) )
                .collect(Collectors.toList());
    }




    // Create
    public news createNews(news news, Long departmentId) {
        Department department = departmentRepository.findById(Math.toIntExact(departmentId))
                .orElseThrow(() -> new RuntimeException("Department not found"));

        news.setDepartment(department);
        return newsRepository.save(news);
    }

    // Update
    public news updateNews(Integer id, news updatedNews, Integer departmentId) {
        news news = newsRepository.findById(id)
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

    public List<news> getAllNews() {
        return newsRepository.findAll();
    }

    public List<news> getAllNewsByDepartment(Integer departmentId) {
        return newsRepository.findByDepartmentIdAndIsHighlighted(departmentId, 1);
    }
}
