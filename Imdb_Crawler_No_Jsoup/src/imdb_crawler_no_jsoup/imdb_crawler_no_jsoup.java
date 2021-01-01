package imdb_crawler_no_jsoup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class imdb_crawler_no_jsoup {
	public static void main(String[] args) {
		final URL url;
		InputStream inputstream = null;
		BufferedReader br;
		String line;
		ArrayList<String> data = new ArrayList<String>();
		System.getProperty("user.home");
                System.out.println(System.getProperty("user.home"));
                String userDir=System.getProperty("user.home")+"\\desktop\\imdbDatabase.txt";
                System.out.println(userDir);
                
		try {
			url = new URL("https://www.imdb.com/chart/top/");
			inputstream = url.openStream();
			br = new BufferedReader(new InputStreamReader(inputstream, "UTF-8"));
			
			String title = null;
			String year = null;
			String director = null;
			String rating = null;
			String newline;
			while ((line = br.readLine()) != null) {
				String[] split1 = line.split(">");
				String[] split2 = line.split(",");
				String[] split3 = line.split("<");
				
                               // System.out.println("split1 "+split1[0]);
                               System.out.println("split1 "+split1[1]);
				if (line.contains("(dir.)")) {
					title = split1[1].replace("</a", "");
                                        //System.out.println(title);
					director = split2[0].replace("title=\"", "").replace(" (dir.)", "");
				}
				
				if (line.contains("user ratings"))
					rating = split1[1].replace("</strong", "");
				
				if (line.contains("secondaryInfo")) {
					year = split3[1].replace("span class=\"secondaryInfo\">(", "").replace(")", "");
					
					newline = title +" - "+ year +" - "+ director +" - "+ rating;
					data.add(newline);
					
				}
			}
			
		} catch (Exception e) {
			System.out.println("Error "+e);
		} finally {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("movie_data.txt"));
				for (String s : data)
					bw.write(s +"\n");
				bw.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			try {
				if (inputstream != null)
					inputstream.close();
			} catch (IOException e) {
				
			}
			
		}
	}

}