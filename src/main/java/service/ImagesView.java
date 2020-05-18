package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="ImagesView")
@RequestScoped

public class ImagesView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<String> images;
     
    public ImagesView(){
        images = new ArrayList<String>();
        for (int i = 1; i <= 9; i++) {
            images.add("00" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}