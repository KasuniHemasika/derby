/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derbyTesting.functionTests.util
   (C) Copyright IBM Corp. 1998, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */

package org.apache.derbyTesting.functionTests.util;

public interface ExtendingInterface extends Runnable {

	public static int INTERFACE_FIELD = 7541;

	/**
		Force an ambigutity with Object's wait.
	*/
	public void wait(int a, long b);

	public Object eimethod(Object a);
}
