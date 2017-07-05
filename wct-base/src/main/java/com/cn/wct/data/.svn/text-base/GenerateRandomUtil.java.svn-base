package com.cn.wct.data;
/**
 * 随机生成字符串
 * @author wct
 *
 */
public class GenerateRandomUtil{
	/**
	 * 
	 * @param cd 生成长度
	 * @param flag true加入特殊字符
	 * @return
	 */
	public static String getPswd(int cd,boolean flag) {
		StringBuffer buf = new StringBuffer("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z");
		buf.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
		if(flag){
			buf.append(",~,@,#,$,%,^,&,*,(,),_,+,|,.");
		}
		buf.append(",1,2,3,4,5,6,7,8,9,0");
		String[] arr = buf.toString().split(",");
		
		StringBuffer b = new StringBuffer();
		java.util.Random r;
		int k;
		for (int i = 0; i <cd; i++) {
			r = new java.util.Random();
			k = r.nextInt();
			b.append(String.valueOf(arr[Math.abs(k % arr.length)]));
		}

		return b.toString();
	}
}