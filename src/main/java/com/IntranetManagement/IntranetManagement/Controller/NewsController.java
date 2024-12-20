package com.IntranetManagement.IntranetManagement.Controller;

import com.IntranetManagement.IntranetManagement.Entities.news;
import com.IntranetManagement.IntranetManagement.Services.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
    //create
    @PostMapping("/create")
    public ResponseEntity<news> createNews(@RequestBody news news,
                                           @RequestParam Long departmentId) {
        return ResponseEntity.ok(newsService.createNews(news, departmentId));
    }

    // all news
    @GetMapping("/allNews")
    public List<news> getAllNews() {
        return newsService.getAllNews();
    }

    // update
    @PutMapping("/{newsId}/update")
    public ResponseEntity<news> updateNews(@PathVariable Integer newsId,
                                           @RequestBody news updatedNews,
                                           @RequestParam Integer departmentId){
        news news = newsService.updateNews(newsId, updatedNews, departmentId);
        return ResponseEntity.ok(news);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Integer id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
    
    // highlited by dep id
    @GetMapping("/highlighted/{departmentId}")
    public ResponseEntity<List<news>> getHighlightedNews(@PathVariable Long departmentId) {
        List<news> highlightedNews = newsService.getHighlightedNews(departmentId);
        return new ResponseEntity<>(highlightedNews, HttpStatus.OK);
    }


    // news by dep id
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<news>> getAllNewsByDepartment(@PathVariable Integer departmentId) {
        List<news> allNews = newsService.getAllNewsByDepartment(departmentId);
        return new ResponseEntity<>(allNews, HttpStatus.OK);
    }
}
