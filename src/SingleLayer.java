import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class SingleLayer {
		static int error=20;
		static int j=0;
		static double[][] maximum = null;
		static int n=0;
		static double x[]=new double[2];
		static double y[]=new double[1];
		static double w[] =new double[2]; 
		static double learningRate = 0.1;
		static double  t = -7+(Math.random()*7);

		public static double[] singleLayer() throws IOException{
			w[0]= -.5+(Math.random()*.5);
			w[1]= -.5+(Math.random()*.5);
			double O=0,current_error=0;
			while(error>0 ||  j==3000){
				j++;
				error =0;
				FileInputStream in =new FileInputStream("file.txt");
				read(in);
				for(int i=0;i<5;i++){
					t = -7+(Math.random()*7);
					O = evaluate(x,w, t );
					current_error = y[0] - O;
					if (current_error != 0){
						for(int j=0;j<w.length;j++)
							w[j] += learningRate * current_error * x[j];
						t +=  learningRate * current_error;
						error+=Math.abs(current_error);
							 
					}
				}
				 
			}
			return w;
		}

	public static int evaluate(double arrX[],double arrW[] , double t){
		int out = 0;
		for(int i=0;i<arrX.length;i++)
			out+=arrX[i]*arrW[i];
			if(out>=t)
				return 1;
			else 
				return -1;
	}
		
	public static String read(FileInputStream in) throws IOException{
		String s="",result="";
		 try (BufferedReader reader = new BufferedReader(
				 new InputStreamReader(in, Charset.defaultCharset()))) {
		         int c=0;
		         if ((s = reader.readLine()) != null) {
		        	 n = Integer.parseInt(s);
		         }
		         while ((s = reader.readLine()) != null) {
		        	 result+=s;
		             String parts[] = result.split(" ");
		             int i=0;
			         for(int j=0;j<3;j++){
						if(j<2){
							x[j] = Double.parseDouble(parts[j]);
						}
						else{
							 y[0]=Double.parseDouble(parts[j]);
						
						 }
			         }
			         result ="";
			         i++;
		         }   
		         return (result);
		 }
	}

	public static void main(String[]args) throws IOException{
		double weight [] = singleLayer() ;
		FileInputStream in =new FileInputStream("test.txt");
		read(in);
		
		for (int i = 0 ; i < n ; i++){
			System.out.println(evaluate(x, weight , t));
		}
		
	}
}
