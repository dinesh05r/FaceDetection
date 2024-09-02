package javaface;

//Importing required classes
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


public class facedetection {
	
	public static void main(String[] args) {
		
		

        // For proper execution of native libraries
        // Core.NATIVE_LIBRARY_NAME must be loaded before
        // calling any of the OPENCV methods
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//Reading the Image from the file
		String imgFile = "images/1.jpg";
		
		//storing the image in to a Matrix object
		Mat src = Imgcodecs.imread(imgFile);
		
		 // Instantiating the CascadeClassifier
		String xmlFile="xml/lbpcascade_frontalface.xml";
		CascadeClassifier cc = new CascadeClassifier(xmlFile);
		
		// Detecting the face in the image
		MatOfRect faceDetection = new MatOfRect();
		cc.detectMultiScale(src, faceDetection);
		System.out.println(String.format("Detected Faces : %d", faceDetection.toArray().length));
		
		//Creating a rectangular box which represents for faces detected
		for(Rect rect: faceDetection.toArray()) {
			Imgproc.rectangle(src,   //where to draw the box
					new Point(rect.x ,rect.y),  //bottom left
					new Point(rect.x + rect.width, rect.y + rect.height),//top right
					new Scalar(0,0, 255),3); //RGB colour
			
		}
		// Saving the output image
		Imgcodecs.imwrite("images/1_out.jpg", src);
		 
		// Display message for successful execution of program
		System.out.println("Face Detection Finished.");
			
		
	}
}