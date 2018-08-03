package com.hung.download;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadAllFile implements Runnable{
	
	private int start;
	private int end;
	private String url;
	private String threadName;
		
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public DownloadAllFile() {
		super();
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=start; i<=end; i++) {
			 Document doc = null;
				try {
					doc = Jsoup.connect(url+i).get();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        Elements links = doc.select("a[href]");
		        int k=1;
		        for (Element link : links) {
		            String linkUrl = link.attr("abs:href");
		            if(linkUrl.indexOf("post")!=-1) {
		            	try {
							String urlImg = GetLinkImage.getUrlImage(linkUrl)+"";
							if(urlImg.equals("-1")) continue; 
								//String[] arrStr = urlImg.split("/");
								String nameFile = "Image/img"+i+"."+k+".jpg";
								k++;
								DownloadFile.Download(urlImg, nameFile);
								System.out.println("Thread "+threadName+": Downloaded " + nameFile+" ("+i+"/" + end+")");
							//}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            	
		            }
		        }
		}
	}

}
