package io.swagger.serviceTest;

import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          System.out.println("111：" +197410001/(1000*60*60*24));
          int day = 297410001/(1000*60*60*24);
          System.out.println("111：" +(297410001-day*(1000*60*60*24))/(1000*60*60));
          
          String content = "http://192.168.132.20/13551202619/topicimage/65ff8e15baad67a9c5caabc23ba34e6b.3.735-_fbff5a3104f247a7b124a0596b127c6d.jpg;http://192.168.132.20/13551202619/topicimage/thumbnail/feaf40a7e1e0c0c476104a18f4dce1b4.jpg|http://192.168.132.20/13551202619/topicimage/edf175d59a98a2fc3a75ca8ef9b8c8b2.3.735-_1dd72beda3814133bf288f8fe3df6e69.jpg;http://192.168.132.20/13551202619/topicimage/thumbnail/8018c4573d77c77852752b4bc9343912.jpg|http://192.168.132.20/13551202619/topicimage/222eac9c892a33e982a5d3418a2d3cec.3.735-_36190965f1074f48abbff98e6fb0596c.jpg;http://192.168.132.20/13551202619/topicimage/thumbnail/8db84585fbde8e19be4c7addd4c297dd.jpg";
          String[] imgs = content.split("\\|");
          System.out.println(imgs[0]);
          
          String status = "1-0-2-3-9";
		   status = status==null?"0":status;
		   status = status.lastIndexOf("-")!=-1?status.substring(status.lastIndexOf("-")+1):status;
		   System.out.println(status);
		   
		   String comment = null;
		   comment = comment==null?"None":comment;
		   comment = comment.lastIndexOf("<|>")!=-1?comment.substring(comment.lastIndexOf("<|>")+3):comment;
		   System.out.println(comment);
	}

}
