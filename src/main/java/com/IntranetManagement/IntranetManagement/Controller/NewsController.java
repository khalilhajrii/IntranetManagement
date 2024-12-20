package com.IntranetManagement.IntranetManagement.Controller;



import com.IntranetManagement.IntranetManagement.Entities.News;
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
    public ResponseEntity<News> createNews(@RequestBody News news,
                                           @RequestParam Long departmentId) {
        return ResponseEntity.ok(newsService.createNews(news, departmentId));
    }

    // all news
    @GetMapping("/allNews")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    // update
    @PutMapping("/{newsId}/update")
    public ResponseEntity<News> updateNews(@PathVariable Integer newsId,
                                           @RequestBody News updatedNews,
                                           @RequestParam Integer departmentId){
        News news = newsService.updateNews(newsId, updatedNews, departmentId);
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
    public ResponseEntity<List<News>> getHighlightedNews(@PathVariable Long departmentId) {
        List<News> highlightedNews = newsService.getHighlightedNews(departmentId);
        return new ResponseEntity<>(highlightedNews, HttpStatus.OK);
    }


    // news by dep id
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<News>> getAllNewsByDepartment(@PathVariable Integer departmentId) {
        List<News> allNews = newsService.getAllNewsByDepartment(departmentId);
        return new ResponseEntity<>(allNews, HttpStatus.OK);
    }
}

