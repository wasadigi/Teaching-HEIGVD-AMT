package ch.heigvd.amt.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-08T12:26:17")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> holderName;
    public static volatile SingularAttribute<Account, Double> balance;
    public static volatile SingularAttribute<Account, Long> id;
    public static volatile SingularAttribute<Account, Integer> numberOfTransactions;
    public static volatile SingularAttribute<Account, Integer> version;

}