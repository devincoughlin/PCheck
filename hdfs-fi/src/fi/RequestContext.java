/**
 * Copyright (c) 2012,
 * Pallavi Joshi  <pallavi@cs.berkeley.edu>
 * All rights reserved.
 * <p/>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 * <p/>
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * <p/>
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * <p/>
 * 3. The names of the contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * <p/>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.fi;

import java.util.*;
import java.io.*;


public class RequestContext {
	String nodeId;
	String othrNodeId;

	String packet;

	public RequestContext(String nodeId, String othrNodeId, String packet){
		this.nodeId = nodeId;
		this.othrNodeId = othrNodeId;
		this.packet = packet;
	}

	public String toString(){
		String s = "nodeId = " + nodeId + "\n";
		s += "othrNodeId = " + othrNodeId + "\n";
		s += "packet = " + packet + "\n";
		return s;
	}
	
	public String toStringWONode(){
		String s = "othrNodeId = " + othrNodeId + "\n";
		s += "packet = " + packet + "\n";
		return s;
	}
	
	public int hashCode(){
                String s = toStringWONode();
		int hash = s.hashCode();
		if(hash == 0){
			Util.MESSAGE("Hash of RequestContext is 0.");
		}
		return hash;
	}

	public boolean equals(Object o){
		if((o == null) || !(o instanceof RequestContext))
			return false;
		RequestContext otherRC = (RequestContext)o;

		String n1 = nodeId;
		String oN1 = othrNodeId;
		String p1 = packet;
		String n2 = otherRC.nodeId;
		String oN2 = otherRC.othrNodeId;
		String p2 = otherRC.packet;


		boolean eqN = areNodesEq(n1, n2);
		boolean eqOthrN = areNodesEq(oN1, oN2);
		boolean eqP = (((p1 == null) && (p2 == null)) || ((p1 != null) && (p2 != null) && p1.equals(p2)));

		return eqN && eqOthrN && eqP;	

		//String otherDesc = otherRC.toString();
		//String desc = toString();
		//return (desc.equals(otherDesc));
	}


	private boolean areNodesEq(String n1, String n2){
		if((n1 == null) && (n2 == null)){
			return true;
		}

		if((n1 == null) || (n2 == null)){
			return false;
		}

		if(n1.contains("Unknown") || n2.contains("Unknown")){
			return true;
		}

		return n1.equals(n2);
	}
}


class RequestComparator implements Comparator<RequestContext>{
	
	public int compare(RequestContext r1, RequestContext r2){
               if((r1 == null) && (r2 != null)) return -1;
	       if((r2 == null) && (r1 != null)) return 1; 
	       if(((r1 == null) && (r2 == null)) 
			       || r1.equals(r2)){
			return 0;
	       }	      

	       String n1 = r1.nodeId;
	       String n2 = r2.nodeId;

	       String othrN1 = r1.othrNodeId;
	       String othrN2 = r2.othrNodeId;

	       if((n1 == null) || (othrN1 == null)){
			return -1;
	       }

	       if(!n1.equals(n2)){
			return n1.compareTo(n2);
	       }

	       return othrN1.compareTo(othrN2);

	}

}
