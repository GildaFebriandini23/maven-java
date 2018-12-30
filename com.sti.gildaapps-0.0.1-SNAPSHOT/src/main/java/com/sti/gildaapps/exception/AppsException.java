package com.sti.gildaapps.exception;

public class AppsException extends Exception
{
	private static final long serialVersionUID = 8889474864160495352L;

	public AppsException() {
    }

	public AppsException(String message)
      {
         super(message);
      }
 }