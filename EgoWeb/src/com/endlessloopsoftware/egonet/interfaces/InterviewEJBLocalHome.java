/*
 * Generated by XDoclet - Do not edit!
 */
package com.endlessloopsoftware.egonet.interfaces;

/**
 * Local home interface for InterviewEJB.
 */
public interface InterviewEJBLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/InterviewEJBLocal";
   public static final String JNDI_NAME="InterviewEJBLocalHome";

   public com.endlessloopsoftware.egonet.interfaces.InterviewEJBLocal create(com.endlessloopsoftware.egonet.util.InterviewDataValue data)
      throws javax.ejb.CreateException;

   public com.endlessloopsoftware.egonet.interfaces.InterviewEJBLocal findByPrimaryKey(com.endlessloopsoftware.egonet.interfaces.InterviewEJBPK pk)
      throws javax.ejb.FinderException;

   public com.endlessloopsoftware.egonet.interfaces.InterviewEJBLocal findByStudyAndEgo(com.endlessloopsoftware.egonet.interfaces.StudyEJBLocal study, java.lang.String firstName, java.lang.String lastName)
      throws javax.ejb.FinderException;

}
