package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    ImageService imageService;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> blogList;
        blogList=blogRepository1.findAll();
        return blogList;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Date currentTime=new Date();
        User user=userRepository1.findById(userId).get();

        Blog blog=new Blog(title,content,currentTime);
        //updating the blog details
        blog.setUser(user);
        List<Blog> currentbBlogsList=user.getBlogList();
        currentbBlogsList.add(blog);
        user.setBlogList(currentbBlogsList);
        //Updating the userInformation and changing its blogs
        userRepository1.save(user);
    }

    public Blog findBlogById(int blogId){
           //find a blog
        Blog blog=null;
        try{
            blog=blogRepository1.findById(blogId).get();
        }catch (Exception e){
            System.out.println(e);
        }
        return blog;
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Blog blog=blogRepository1.findById(blogId).get();
        Image image=imageService.createAndReturn(blog,description,dimensions);

        List<Image> imageList=blog.getImageList();

        image.setBlog(blog);

        imageList.add(image);

        blog.setImageList(imageList);

        blogRepository1.save(blog);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        try{
            if(blogRepository1.findById(blogId).get()==null){
                return;
            }
            blogRepository1.deleteById(blogId);
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
