/*
 * Generated by XDoclet - Do not edit!
 */
package com.endlessloopsoftware.egonet.data;

/**
 * CMP layer for ConfigurationEJB.
 */
public abstract class ConfigurationEJBCMP
   extends com.endlessloopsoftware.egonet.ejb.ConfigurationEJB
   implements javax.ejb.EntityBean
{

   public void ejbLoad() throws javax.ejb.EJBException
   {
      super.ejbLoad();
   }

   public void ejbStore() throws javax.ejb.EJBException
   {
         super.ejbStore();
   }

   public void ejbActivate() throws javax.ejb.EJBException
   {
      super.ejbActivate();
   }

   public void ejbPassivate() throws javax.ejb.EJBException
   {
      super.ejbPassivate();

   }

   public void setEntityContext(javax.ejb.EntityContext ctx) throws javax.ejb.EJBException
   {
      super.setEntityContext(ctx);
   }

   public void unsetEntityContext() throws javax.ejb.EJBException
   {
      super.unsetEntityContext();
   }

   public void ejbRemove() throws javax.ejb.EJBException, javax.ejb.RemoveException
   {
      super.ejbRemove();

   }

 /* Value Objects BEGIN */

/* Value Objects END */

   public abstract java.lang.Byte getId() ;

   public abstract void setId( java.lang.Byte id ) ;

   public abstract java.lang.String getServerPassword() ;

   public abstract void setServerPassword( java.lang.String serverPassword ) ;

}
