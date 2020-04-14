package org.omg.PortableServer;


/**
* org/omg/PortableServer/CurrentOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/re/workspace/8-2-build-windows-amd64-cygwin/jdk8u152/9280.nc/corba/src/share/classes/org/omg/PortableServer/poa.idl
* Tuesday, June 20, 2017 4:13:57 PM PDT
*/


/**
	 * The PortableServer::Current interface, derived from 
	 * CORBA::Current, provides method implementations with 
	 * access to the identity of the object on which the 
	 * method was invoked. The Current interface is provided 
	 * to support servants that implement multiple objects, 
	 * but can be used within the context of POA-dispatched 
	 * method invocations on any servant. To provide location 
	 * transparency, ORBs are required to support use of 
	 * Current in the context of both locally and remotely 
	 * invoked operations. An instance of Current can be 
	 * obtained by the application by issuing the 
	 * CORBA::ORB::resolve_initial_references("POACurrent") 
	 * operation. Thereafter, it can be used within the 
	 * context of a method dispatched by the POA to obtain 
	 * the POA and ObjectId that identify the object on 
	 * which that operation was invoked.
	 */
public interface CurrentOperations  extends org.omg.CORBA.CurrentOperations
{

  /**
	 * Returns reference to the POA implementing the 
	 * object in whose context it is called. 
	 *
	 * @return The poa implementing the object
	 * 
	 * @exception NoContext is raised when the operation is
	 *            outside the context of a POA-dispatched 
	 *            operation
	 */
  org.omg.PortableServer.POA get_POA () throws org.omg.PortableServer.CurrentPackage.NoContext;

  /** 
	 * Returns the ObjectId identifying the object in 
	 * whose context it is called. 
	 *
	 * @return the ObjectId of the object
	 *
	 * @exception NoContext is raised when the operation
	 * is called outside the context of a POA-dispatched 
	 * operation.
	 */
  byte[] get_object_id () throws org.omg.PortableServer.CurrentPackage.NoContext;
} // interface CurrentOperations
