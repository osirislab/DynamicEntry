/*
Copyright (c) 2013, Jeffrey Dileo
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the Information Systems and Internet Security
(ISIS) Laboratory.

*/

package com.isis.dynamicentry;

import java.lang.instrument.*;
import javassist.*;

public class Player implements ClassFileTransformer {
	
	public static void premain(String agentArgument, Instrumentation instrumentation) {
		System.out.println("Instrumentation Begins!");
		System.out.println("Agent Args: \"" + agentArgument + "\"");
	
		instrumentation.addTransformer(new Player());
	}


	public byte[] transform(ClassLoader loader, String className, Class<?> clazz, java.security.ProtectionDomain domain, byte[] bytes) {

		ClassPool pool = ClassPool.getDefault();
		boolean t = false;
		try{
			
			pool.makeClass(new java.io.ByteArrayInputStream(bytes));
			if ( className.equals("org/apache/catalina/connector/RequestFacade") ) {
			  return modifyRequestClass(className, clazz, bytes);
			}
		}catch(Exception e){
			System.out.println("Oh noes");
			
		}
			
		if(!t){
			return bytes;
		}
			
		return doClass(className, clazz, bytes);
	}

	private byte[] modifyRequestClass(String name, Class<?> clazz, byte[] b) {
		ClassPool pool = ClassPool.getDefault();
		CtClass cl;
		cl = null;
		
		try {
			cl = pool.makeClass(new java.io.ByteArrayInputStream(b));
		
			CtMethod m = CtNewMethod.make("public String jEscape(String stuff){\n" +
					"\tif(stuff == null)\n" +
					"\t\treturn null;\n" +
					"\tString ret = \"\";\n" +
					"\tString c = \"\";\n" +
					"\tint strlen = stuff.length();\n" +
					"\tfor(int i=0; i<strlen; i++){\n" +
					"\t\tc = stuff.substring(i, i+1);\n" +
					"\t\tif(c.equals(\"<\"))\n" +
					"\t\t\tret += \"&#060;\";\n" +
					"\t\telse if(c.equals(\">\"))\n" +
					"\t\t\tret += \"&#062;\";\n" +
					"\t\telse if(c.equals(\"&\"))\n" +
					"\t\t\tret += \"&#038;\";\n" +
					"\t\telse if(c.equals(\"\\\"\"))\n" +
					"\t\t\tret += \"&#034;\";\n" +
					"\t\telse if(c.equals(\"\\t\"))\n" +
					"\t\t\tret += \"&#009;\";\n" +
					"\t\telse if(c.equals(\"!\"))\n" +
					"\t\t\tret += \"&#033;\";\n" +
					"\t\telse if(c.equals(\"#\"))\n" +
					"\t\t\tret += \"&#035;\";\n" +
					"\t\telse if(c.equals(\"$\"))\n" +
					"\t\t\tret += \"&#036;\";\n" +
					"\t\telse if(c.equals(\"%\"))\n" +
					"\t\t\tret += \"&#037;\";\n" +
					"\t\telse if(c.equals(\"'\"))\n" +
					"\t\t\tret += \"&#039;\";\n" +
					"\t\telse if(c.equals(\"(\"))\n" +
					"\t\t\tret += \"&#040;\";\n" +
					"\t\telse if(c.equals(\")\"))\n" +
					"\t\t\tret += \"&#041;\";\n" +
					"\t\telse if(c.equals(\"*\"))\n" +
					"\t\t\tret += \"&#042;\";\n" +
					"\t\telse if(c.equals(\"+\"))\n" +
					"\t\t\tret += \"&#043;\";\n" +
					"\t\telse if(c.equals(\",\"))\n" +
					"\t\t\tret += \"&#044;\";\n" +
					"\t\telse if(c.equals(\"-\"))\n" +
					"\t\t\tret += \"&#045;\";\n" +
					"\t\telse if(c.equals(\".\"))\n" +
					"\t\t\tret += \"&#046;\";\n" +
					"\t\telse if(c.equals(\"/\"))\n" +
					"\t\t\tret += \"&#047;\";\n" +
					"\t\telse if(c.equals(\":\"))\n" +
					"\t\t\tret += \"&#058;\";\n" +
					"\t\telse if(c.equals(\";\"))\n" +
					"\t\t\tret += \"&#059;\";\n" +
					"\t\telse if(c.equals(\"=\"))\n" +
					"\t\t\tret += \"&#061;\";\n" +
					"\t\telse if(c.equals(\"?\"))\n" +
					"\t\t\tret += \"&#063;\";\n" +
					"\t\telse if(c.equals(\"@\"))\n" +
					"\t\t\tret += \"&#064;\";\n" +
					"\t\telse if(c.equals(\"[\"))\n" +
					"\t\t\tret += \"&#091;\";\n" +
					"\t\telse if(c.equals(\"\\\\\"))\n" +
					"\t\t\tret += \"&#092;\";\n" +
					"\t\telse if(c.equals(\"]\"))\n" +
					"\t\t\tret += \"&#093;\";\n" +
					"\t\telse if(c.equals(\"^\"))\n" +
					"\t\t\tret += \"&#094;\";\n" +
					"\t\telse if(c.equals(\"_\"))\n" +
					"\t\t\tret += \"&#095;\";\n" +
					"\t\telse if(c.equals(\"`\"))\n" +
					"\t\t\tret += \"&#096;\";\n" +
					"\t\telse if(c.equals(\"{\"))\n" +
					"\t\t\tret += \"&#123;\";\n" +
					"\t\telse if(c.equals(\"|\"))\n" +
					"\t\t\tret += \"&#124;\";\n" +
					"\t\telse if(c.equals(\"}\"))\n" +
					"\t\t\tret += \"&#125;\";\n" +
					"\t\telse if(c.equals(\"~\"))\n" +
					"\t\t\tret += \"&#126;\";\n" +
					"\t\telse if(c.equals(\" \"))\n" +
					"\t\t\tret += \"&#160;\";\n" +
					"\t\telse if(c.equals(\" \"))\n" +
					"\t\t\tret += \"&#032;\";\n" +
					"\t\telse\n" +
					"\t\t\tret += c;\n" +
					"\t}\n" +
					"\treturn ret;\n" +
					"}\n", cl);
		
			cl.addMethod(m);
			CtBehavior[] methods = cl.getDeclaredBehaviors();
			for (int i = 0; i < methods.length; i++) {
			  modifyRequestClassMethods(methods[i]);
			}
		

			b = cl.toBytecode();
		} catch (Exception e) {
			System.err.println("Could not instrument	" + name
						 + ",	exception : " + e.getMessage());
		} finally {
			if (cl != null) {
				cl.detach();
			}
		}
		return b;
	}

	private void modifyRequestClassMethods(CtBehavior method) throws NotFoundException, CannotCompileException {
		
		method.instrument(new javassist.expr.ExprEditor(){			
			public void edit(final javassist.expr.MethodCall m) throws CannotCompileException {
				if ("org.apache.catalina.connector.Request".equals(m.getClassName())){
					if("getParameter".equals(m.getMethodName())) {
						m.replace("{ $_ = jEscape($proceed($$)); } ");
					}
				}
			}
		});
	}

}

