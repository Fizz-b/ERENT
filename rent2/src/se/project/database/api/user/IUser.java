package se.project.database.api.user;

import se.project.model.user.Customer;

public interface IUser {

   public Customer getUser(String cust);
   public Customer getUserById(String id);
   public String getNameById(String id);
} 
