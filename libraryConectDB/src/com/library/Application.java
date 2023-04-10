package com.library;

import com.library.controller.LibraryController;
import com.library.controller.MemberVo;

public class Application {

	public static void main(String[] args) {

		LibraryController libraryController = new LibraryController();
		
		libraryController.start();
	}
	
	public MemberVo login() {
		return null;
		
	}
}
