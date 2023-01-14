package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Blog blog1=blogRepository.findById(blog.getId()).get();
        Image image=new Image(description,dimensions);
        image.setBlog(blog1);
        List<Image> imageList=blog1.getImageList();
        imageList.add(image);
        blog1.setImageList(imageList);
        blogRepository.save(blog1);
        return image;
    }

    public void deleteImage(int id){
        imageRepository2.deleteById(id);
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0

    }
}
