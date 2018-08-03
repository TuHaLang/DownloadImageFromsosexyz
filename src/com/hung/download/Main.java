package com.hung.download;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] start = new int[20];
		int[] end = new int[20];
		for(int i=0; i<20; i++) {
			start[i]=(2473/20)*i+1;
			if(i==19) end[i]=2473;
			else end[i]=(2473/20)*(i+1);
		}
		File folder = new File("Image");
		folder.mkdir();
		DownloadAllFile[] runDownload = new DownloadAllFile[20];
		for(int i=0; i<20; i++) {
			runDownload[i] = new DownloadAllFile();
			runDownload[i].setStart(start[i]);
			runDownload[i].setEnd(end[i]);
			runDownload[i].setUrl("https://sose.xyz/page/");
			runDownload[i].setThreadName(i+"");
		}
		Thread[] thread = new Thread[20];
		for(int i=0; i<20; i++) {
			thread[i] = new Thread(runDownload[i]);
			thread[i].start();
		}
	}

}
