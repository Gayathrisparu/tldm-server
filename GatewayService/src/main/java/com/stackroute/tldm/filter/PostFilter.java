package com.stackroute.tldm.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		System.out.println("inside post");
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "Post";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
