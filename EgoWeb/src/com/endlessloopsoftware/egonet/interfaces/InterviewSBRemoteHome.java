/*
 * Generated by XDoclet - Do not edit!
 */
package com.endlessloopsoftware.egonet.interfaces;

/**
 * Home interface for InterviewSB.
 * @copyright 2004 Endless Loop Software Inc.
 */
public interface InterviewSBRemoteHome
   extends com.endlessloopsoftware.egonet.interfaces.EgoSBRemoteHome
{
   public static final String COMP_NAME="java:comp/env/ejb/InterviewSB";
   public static final String JNDI_NAME="ejb/InterviewSB";

   public com.endlessloopsoftware.egonet.interfaces.InterviewSBRemote create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
