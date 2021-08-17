package com.work.task.sbjpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotInsertedException extends RuntimeException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public RecordNotInsertedException(String exception) {
		super(exception);
	}

}
