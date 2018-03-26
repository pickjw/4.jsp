package anno5;

import javax.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("homeCvn")
public class HomeController2 {

	 @Autowired
	 private Camera camera;
	 
	 @Override
	public String toString() {
		// TODO Auto-generated method stub
		return "HomeController2[camera="+camera+"]";
	}
}



