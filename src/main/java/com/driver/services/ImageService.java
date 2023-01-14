package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;

    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image=new Image(description,dimensions);
        image.setBlog(blog);
        List<Image> imageList=blog.getImageList();
        if(imageList==null){
            imageList=new ArrayList<>();
        }
        imageList.add(image);
        blog.setImageList(imageList);
        blogRepository.save(blog);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Image image){
        imageRepository2.deleteById(image.getId());
    }

    public Image findById(int id) {
        return imageRepository2.findById(id).get();
    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        //In case the image is null, return 0
        String[] dimensions=image.getDimensions().split("X");
        String[] str=screenDimensions.split("X");
        int num1=Integer.parseInt(str[0]);
        int num2=Integer.parseInt(str[2]);

        return num1*num2/Integer.parseInt(dimensions[0]);
    }
}
