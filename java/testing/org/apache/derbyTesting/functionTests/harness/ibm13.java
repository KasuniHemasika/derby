/*

   Licensed Materials - Property of IBM
   Cloudscape - Package org.apache.derbyTesting.functionTests.harness
   (C) Copyright IBM Corp. 2001, 2004. All Rights Reserved.
   US Government Users Restricted Rights - Use, duplication or
   disclosure restricted by GSA ADP Schedule Contract with IBM Corp.

 */

package org.apache.derbyTesting.functionTests.harness;

import java.util.Vector;
import java.util.StringTokenizer;

/**
  <p>This class is for IBM's jdk 1.3.

  @author ames
 */
public class ibm13 extends jvm {

	public String getName(){return "ibm13";}
    public ibm13(boolean noasyncgc, boolean verbosegc, boolean noclassgc,
    long ss, long oss, long ms, long mx, String classpath, String prof,
    boolean verify, boolean noverify, boolean nojit, Vector D) {
        super(noasyncgc,verbosegc,noclassgc,ss,oss,ms,mx,classpath,prof,
		verify,noverify,nojit,D);
    }
    // more typical use:
    public ibm13(String classpath, Vector D) {
        super(classpath,D);
    }
    // more typical use:
    public ibm13(long ms, long mx, String classpath, Vector D) {
        super(ms,mx,classpath,D);
    }
    // actual use
    public ibm13() { }

    // return the command line to invoke this VM.  The caller then adds
    // the class and program arguments.
    public Vector getCommandLine() 
    {
        StringBuffer sb = new StringBuffer();
        Vector v = super.getCommandLine();
        appendOtherFlags(sb);
        String s = sb.toString();
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens())
        {
            v.addElement(st.nextToken());
        }
        return v;
	}

	public void appendOtherFlags(StringBuffer sb)
	{
        if (noasyncgc) warn("ibm13 does not support noasyncgc");
        if (verbosegc) sb.append(" -verbose:gc");
        if (noclassgc) sb.append(" -Xnoclassgc");
        if (ss>=0) warn("ibm13 does not support ss");
        if (oss>=0) warn("ibm13 does not support oss");
        if (ms>=0) {
          sb.append(" -ms");
          sb.append(ms);
        }
        if (mx>=0) {
          sb.append(" -mx");
          sb.append(mx);
        }
        if (classpath!=null) {
          sb.append(" -classpath ");
          sb.append(classpath);
        }
        if (prof!=null) warn("ibm13 does not support prof");
        if (verify) warn("ibm13 does not support verify");
        if (noverify) warn("ibm13 does not support noverify");
        if (nojit) sb.append(" -Djava.compiler=NONE");
        if (D != null)
          for (int i=0; i<D.size();i++) {
	        sb.append(" -D");
	        sb.append((String)(D.elementAt(i)));
          }
    }
	public String getDintro() { return "-D"; }
}
